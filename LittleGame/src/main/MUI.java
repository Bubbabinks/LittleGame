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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int width, height;
	private Color BackgroundColor = new Color(135, 206, 235);
	private JComboBox<String> generationComboBox = new JComboBox<String>(WorldGenerator.GENERATION_TYPES);
	private JComboBox<String> worldSizeComboBox = new JComboBox<String>(WorldGenerator.WORLDSIZE_TYPES);
	
	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MUI(JFrame frame, int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setBackground(BackgroundColor);
		setLayout(new GridBagLayout());
		
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
		
		Button playButton = new Button(300, 50, "Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(title);
				remove(playButton);
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
		GC = new GridBagConstraints();
		playButton.setBackground(new Color(181,143,76));
		playButton.setForeground(Color.WHITE);
		GC.gridy = 1;
		GC.insets = new Insets(10,0,10,0);
		add(playButton, GC);
		
	}
	
	@SuppressWarnings("unused")
	private void endMenu(String worldGeneratorType, String worldSize) {
		for (ActionListener actionListener: actionListeners) {
			actionListener.actionPerformed(new ActionEvent(this, 0, worldGeneratorType+","+worldSize));
		}
	}
	
	@SuppressWarnings("unused")
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
