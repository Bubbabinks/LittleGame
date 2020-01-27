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

@SuppressWarnings("serial")
public class GUI extends JPanel implements KeyHandled{
	
	private final int width = 725, height = 850, fps = 60;
	
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private JFrame frame = new JFrame("Little Game");
	private Player player;
	
	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(width,height));
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		PlayerController playerController = new PlayerController(this);
		playerController.setGravity(true);
		frame.addKeyListener(playerController);
		
		mainTimer.start();
		
		int blockGenerationRange = 100;
		int blockWidth = 100;
		double randomRange = 100;
		int baseHeight = 150;
		
		for (int i=-blockGenerationRange;i<blockGenerationRange;i++) {
			Block block = new Block(blockWidth,(int)(Math.random()*randomRange)+baseHeight);
			block.setY(height-block.getHeight());
			block.setX(i*blockWidth);
			blocks.add(block);
		}
		
		player = new Player(10, 10);
		player.setX(width/2-5);
		player.setY(height/2+5);
		
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
		for (Block i: blocks) {
			i.drawBlock(g);
		}
	}
	
	public void keyEvent(int keyCode) {
		if (keyCode == KeyEvent.VK_W) {
			player.setY(player.getY()-1);
		}
		if (keyCode == KeyEvent.VK_S) {
			for (Block i: blocks) {
				if (player.collideWith(i) == 0) {
					return;
				}
			}
			player.setY(player.getY()+1);
		}
		if (keyCode == KeyEvent.VK_D) {
			for (Block i: blocks) {
				i.setX(i.getX()-1);
			}
		}
		if (keyCode == KeyEvent.VK_A) {
			for (Block i: blocks) {
				i.setX(i.getX()+1);
			}
		}
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
