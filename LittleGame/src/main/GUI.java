package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel implements KeyHandled{
	
	private final int width = 725, height = 850, fps = 60;
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	JFrame frame = new JFrame("Little Game");
	Player player;
	
	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(width,height));
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		KeyHandler keyHandler = new KeyHandler(this);
		frame.addKeyListener(keyHandler);
		
		player = new Player(10, 10);
		
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.drawPlayer(g);
	}
	
	public void keyEvent(int keyCode) {
		
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
