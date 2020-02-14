package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int width, height;
	private Color BackgroundColor = new Color(135, 206, 235);
	
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
		
		JButton playButton = new JButton("Play");
		playButton.setPreferredSize(new Dimension(300,50));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endMenu("normal_g","small_w");
			}
		});
		GC = new GridBagConstraints();
		playButton.setBackground(new Color(181,143,76));
		playButton.setForeground(Color.WHITE);
		GC.gridy = 1;
		GC.insets = new Insets(10,0,10,0);
		add(playButton, GC);
	}
	
	private void endMenu(String worldGeneratorType, String worldSize) {
		for (ActionListener actionListener: actionListeners) {
			actionListener.actionPerformed(new ActionEvent(this, 0, worldGeneratorType+","+worldSize));
		}
	}
	
	public void addActionListener(ActionListener actionListener) {
		actionListeners.add(actionListener);
	}
	
	public ArrayList<ActionListener> getActionListener() {
		return actionListeners;
	}
	
}
