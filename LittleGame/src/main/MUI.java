package main;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int width, height;
	
	public MUI(JFrame frame, int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor("yes");
		g.fillRect(0, 0, width, height);
	}
	
}
