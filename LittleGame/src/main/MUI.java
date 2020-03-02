package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Color BackgroundColor = new Color(135, 206, 235);
	private JComboBox<String> generationComboBox = new JComboBox<String>(WorldGenerator.GENERATION_TYPES);
	private JComboBox<String> worldSizeComboBox = new JComboBox<String>(WorldGenerator.WORLDSIZE_TYPES);
	
	private Button singleButton;
	private Button multiButton;
	private Button createButton;
	private Button loadButton;
	
	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MUI(JFrame frame, int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setBackground(BackgroundColor);
		setLayout(new GridBagLayout());
		
		World.setInventory(new Inventory(width));
		
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
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = 200;
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		add(title, GC);
		
		World.setNewWorld(true);
		
		singleButton = new Button(300, 50, "Singleplayer");
		singleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(title);
				remove(singleButton);
				remove(multiButton);
				repaint();
				
				GridBagConstraints GC = new GridBagConstraints();
				GC.insets = new Insets(10, 0, 10, 0);
				createButton = new Button(300,50,"Create New World");
				createButton.setBackground(new Color(181,143,76));
				createButton.setForeground(Color.WHITE);
				createButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						remove(createButton);
						remove(loadButton);
						repaint();
						JLabel generationLabel = new JLabel("Generation Type: ");
						generationLabel.setHorizontalAlignment(SwingConstants.CENTER);
						GridBagConstraints GC = new GridBagConstraints();
						add(generationLabel, GC);
						
						GC = new GridBagConstraints();
						GC.gridx = 1;
						add(generationComboBox, GC);
						
						JLabel worldSizeLabel = new JLabel("World Size: ");
						worldSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
						GC = new GridBagConstraints();
						GC.gridy = 1;
						add(worldSizeLabel, GC);
						
						worldSizeComboBox.setSelectedIndex(1);
						GC = new GridBagConstraints();
						GC.gridx = 1;
						GC.gridy = 1;
						add(worldSizeComboBox, GC);
						
						Button button = new Button(200, 30, "Generate World");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if (generationComboBox.getSelectedItem().equals("INFINITE_G")) {
									endMenu((String)generationComboBox.getSelectedItem(), (String)worldSizeComboBox.getSelectedItem(), 1);
								}else {
									endMenu((String)generationComboBox.getSelectedItem(), (String)worldSizeComboBox.getSelectedItem());
								}
							}
						});
						GC.gridy = 2;
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
				loadButton = new Button(300,50,"Load World");
				loadButton.setBackground(new Color(181,143,76));
				loadButton.setForeground(Color.WHITE);
				loadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						World.setNewWorld(false);
						FileManager.worldLoad();
						endMenu("normal_g", "small_w");
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
