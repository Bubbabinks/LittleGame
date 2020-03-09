package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class MUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> generationComboBox = new JComboBox<String>(WorldGenerator.GENERATION_TYPES);
	private JComboBox<String> worldSizeComboBox = new JComboBox<String>(WorldGenerator.WORLDSIZE_TYPES);
	
	private int width, height;
	
	private Button singleButton;
	private Button multiButton;
	private Button createButton;
	private Button loadButton;
	private JTextField worldTextArea;
	
	private JList<String> worldList;
	
	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MUI(JFrame frame, int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setBackground(World.SKY);
		setLayout(new GridBagLayout());
		
		Color[] slots = World.inventory.getSlots();
		for (int i=0;i<slots.length;i++) {
			slots[i] = Color.BLACK;
		}
		
		JLabel title = new JLabel("Little Game");
		title.setPreferredSize(new Dimension(500,200));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints GC = new GridBagConstraints();
		Font labelFont = title.getFont();
		String labelText = title.getText();
		int stringWidth = title.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = 500;
		double widthRatio = (double)componentWidth / (double)stringWidth;
		int newFontSize = (int)(labelFont.getSize() * widthRatio)-5;
		int componentHeight = 200;
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		add(title, GC);
		
		World.setNewWorld(true);
		
		singleButton = new Button(300, 50, "Singleplayer");
		singleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				repaint();
				
				GridBagConstraints GC = new GridBagConstraints();
				worldList = new JList<String>(FileManager.getWorldsNames());
				worldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JScrollPane worldScrollPane = new JScrollPane(worldList);
				worldScrollPane.setPreferredSize(new Dimension(width*2/3,height-200));
				GC.anchor = GridBagConstraints.CENTER;
				GC.gridwidth = 3;
				add(worldScrollPane,GC);
				
				GC = new GridBagConstraints();
				GC.insets = new Insets(75, 20, 10, 20);
				GC.gridy=1;
				GC.gridx=1;
				GC.gridwidth = 1;
				repaint();
				createButton = new Button(300,50,"Create New World");
				createButton.setBackground(new Color(181,143,76));
				createButton.setForeground(Color.WHITE);
				createButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeAll();
						repaint();
						
						JLabel worldNameLabel = new JLabel("World Name: ");
						worldNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
						GridBagConstraints GC = new GridBagConstraints();
						GC.insets = new Insets(0,0,5,0);
						add(worldNameLabel, GC);
						
						worldTextArea = new JTextField("New World");
						worldTextArea.setPreferredSize(new Dimension(100,20));
						worldTextArea.setHorizontalAlignment(SwingConstants.CENTER);
						GC = new GridBagConstraints();
						GC.gridx = 1;
						add(worldTextArea, GC);
						
						JLabel generationLabel = new JLabel("Generation Type: ");
						generationLabel.setHorizontalAlignment(SwingConstants.CENTER);
						GC = new GridBagConstraints();
						GC.gridy = 1;
						add(generationLabel, GC);
						
						GC = new GridBagConstraints();
						GC.gridy = 1;
						GC.gridx = 1;
						add(generationComboBox, GC);
						
						JLabel worldSizeLabel = new JLabel("World Size: ");
						worldSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
						GC = new GridBagConstraints();
						GC.gridy = 2;
						add(worldSizeLabel, GC);
						
						worldSizeComboBox.setSelectedIndex(1);
						GC = new GridBagConstraints();
						GC.gridx = 1;
						GC.gridy = 2;
						add(worldSizeComboBox, GC);
						
						Button button = new Button(200, 30, "Generate World");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								World.setWorldName(worldTextArea.getText());
								if (generationComboBox.getSelectedItem().equals("INFINITE_G")) {
									endMenu((String)generationComboBox.getSelectedItem(), (String)worldSizeComboBox.getSelectedItem(), 1);
								}else {
									endMenu((String)generationComboBox.getSelectedItem(), (String)worldSizeComboBox.getSelectedItem());
								}
							}
						});
						GC.gridy = 3;
						GC.gridx = 0;
						GC.gridwidth = 2;
						GC.insets = new Insets(10, 0, 0, 0);
						add(button, GC);
						revalidate();
					}
				});
				add(createButton,GC);
				
				GC = new GridBagConstraints();
				GC.gridy = 1;
				GC.insets = new Insets(75, 20, 10, 20);
				loadButton = new Button(300,50,"Load World");
				loadButton.setBackground(new Color(181,143,76));
				loadButton.setForeground(Color.WHITE);
				loadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!worldList.isSelectionEmpty()) {
							World.setOverWrite(false);
							World.setNewWorld(false);
							World.setWorldName(worldList.getSelectedValue());
							FileManager.worldLoad(worldList.getSelectedValue());
							endMenu("normal_g", "small_w");
						}
					}
					
				});
				add(loadButton,GC);
				
				revalidate();
			}
		});
		
		GC = new GridBagConstraints();
		singleButton.setBackground(new Color(181,143,76));
		singleButton.setForeground(Color.WHITE);
		GC.gridy = 1;
		GC.insets = new Insets(10,0,10,0);
		add(singleButton, GC);
		
		multiButton = new Button(300, 50, "Multiplayer");
		multiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(title);
				remove(multiButton);
				remove(singleButton);
				repaint();
				
				JLabel generationLabel = new JLabel("COMING SOON");
				generationLabel.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints GC = new GridBagConstraints();
				add(generationLabel, GC);
				
				revalidate();
			}
		});
		GC = new GridBagConstraints();
		multiButton.setBackground(new Color(181,143,76));
		multiButton.setForeground(Color.WHITE);
		GC.gridy = 2;
		GC.insets = new Insets(10,0,10,0);
		add(multiButton, GC);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
	
	private void endMenu(String worldGeneratorType, String worldSize) {
		for (ActionListener actionListener: actionListeners) {
			actionListener.actionPerformed(new ActionEvent(this, 0, worldGeneratorType+","+worldSize));
		}
	}
	
	private void endMenu(String worldGeneratorType, String worldSize, int needsPlayer) {
		for (ActionListener actionListener: actionListeners) {
			actionListener.actionPerformed(new ActionEvent(this, 0, worldGeneratorType+","+worldSize+","+needsPlayer));
		}
	}
	
	public void addActionListener(ActionListener actionListener) {
		actionListeners.add(actionListener);
	}
	
	public ArrayList<ActionListener> getActionListener() {
		return actionListeners;
	}
	
}
