package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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
		
		mainTimer.start();
		
		player = new Player(10, 10);
		
		frame.setVisible(true);
	}
	
	Timer mainTimer = new Timer((int)(1000d/(double)fps), new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	});
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.drawPlayer(g);
	}
	
	public void keyEvent(int keyCode) {
		if (keyCode == KeyEvent.VK_W) {
			player.setY(player.getY()-1);
		}
		if (keyCode == KeyEvent.VK_S) {
			player.setY(player.getY()+1);
		}
		if (keyCode == KeyEvent.VK_D) {
			player.setX(player.getX()+1);
		}
		if (keyCode == KeyEvent.VK_A) {
			player.setX(player.getX()-1);
		}
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
