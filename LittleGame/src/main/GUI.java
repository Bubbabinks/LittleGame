package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GUI extends JPanel implements KeyHandled{
	
	private static final long serialVersionUID = 1L;
	private final int width = 725, height = 850, fps = 60, maxDistanceToEdge = 200;
	private JFrame frame = new JFrame("Little Game");
	private PlayerController playerController;
	private Player player;
	
	private ArrayList<GameObject> world = new ArrayList<GameObject>();
	
	public GUI() {
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				closeGame();
			}
		});
		this.setPreferredSize(new Dimension(width,height));
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		WorldGenerator worldGenerator = new WorldGenerator(WorldGenerator.FLAT_G, WorldGenerator.EXTRA_SMALL_W);
		worldGenerator.startGeneration();
		for (GameObject gameObject: worldGenerator.getWorld()) {
			world.add(gameObject);
		}
		playerController = new PlayerController(this);
		playerController.setGravity(true);
		frame.addKeyListener(playerController);
		
		mainTimer.start();
		
		player = new Player(40, 40);
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
		int halfWidth = width/2;
		int doubleWidth = width*2;
		int halfHeight = height/2;
		int doubleHeight = height*2;
		for (GameObject object: world) {
			if (Math.abs(object.getX()+(halfWidth))<doubleWidth && Math.abs(object.getY()+(halfHeight))<doubleHeight) {
				object.drawObject(g);
			}
		}
		player.drawObject(g);
	}
	
	public void keyEvent(int keyCode) {
		if (keyCode == KeyEvent.VK_W) {
			for (GameObject i: world) {
				if (player.collideWith(i, 0)) {
					return;
				}
			}
			if (player.getY()>maxDistanceToEdge) {
				player.setY(player.getY()-1);
			}else {
				for (GameObject i: world) {
					i.setY(i.getY()+1);
				}
			}
			return;
		}
		if (keyCode == KeyEvent.VK_S) {
			for (GameObject i: world) {
				if (player.collideWith(i, 2)) {
					return;
				}
			}
			if (player.getY()+player.getHeight()<=height-maxDistanceToEdge) {
				player.setY(player.getY()+1);
			}else {
				for (GameObject i: world) {
					i.setY(i.getY()-1);
				}
			}
			return;
		}
		if (keyCode == KeyEvent.VK_D) {
			for (GameObject i: world) {
				if (player.collideWith(i, 1)) {
					return;
				}
			}
			if (player.getX()+player.getWidth()<=width-maxDistanceToEdge) {
				player.setX(player.getX()+1);
			}else {
				for (GameObject i: world) {
					i.setX(i.getX()-1);
				}
			}
			return;
		}
		if (keyCode == KeyEvent.VK_A) {
			for (GameObject i: world) {
				if (player.collideWith(i, 3)) {
					return;
				}
			}
			if (player.getX()>maxDistanceToEdge) {
				player.setX(player.getX()-1);
			}else {
				for (GameObject i: world) {
					i.setX(i.getX()+1);
				}
			}
			return;
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			closeGame();
		}
	}
	
	private CloseWindow closeWindow = new CloseWindow();
	
	public void closeGame() {
		if (!closeWindow.isVisible()) {
			closeWindow.setVisible(true);
			playerController.getKeysPressed().remove(KeyEvent.VK_ESCAPE);
		}
	}
	
	public class CloseWindow extends JFrame {
		
		private static final long serialVersionUID = 1L;
		
		public CloseWindow() {
			setTitle("Exiting?");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					super.windowClosing(e);
					setVisible(false);
				}
			});
			setLayout(new GridBagLayout());
			GridBagConstraints GC = new GridBagConstraints();
			JLabel text = new JLabel("Are you sure you want to Exit?");
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setPreferredSize(new Dimension(260,50));
			GC.gridwidth = 2;
			add(text, GC);
			
			GC = new GridBagConstraints();
			JButton yes = new JButton("Yes");
			yes.setPreferredSize(new Dimension(130,50));
			yes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			GC.gridy = 1;
			add(yes, GC);
			
			GC = new GridBagConstraints();
			JButton no = new JButton("No");
			no.setPreferredSize(new Dimension(130,50));
			no.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			GC.gridy = 1;
			GC.gridx = 1;
			add(no, GC);
			
			pack();
			setLocationRelativeTo(frame);
		}
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
