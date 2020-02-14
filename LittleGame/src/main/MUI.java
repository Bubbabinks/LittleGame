package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int width, height;
	private Color BackgroundColor = new Color(135, 206, 235);
	
	public MUI(JFrame frame, int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(BackgroundColor);
		g.fillRect(0, 0, width, height);
	}
	
}
