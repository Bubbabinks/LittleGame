package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class UIManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 975, height = 850;
	private GUI gui;
	private MUI mui;
	private AudioManager audioManager = new AudioManager();
	
	public UIManager() {
		audioManager.startBackgroundMusic();
		setTitle("Little Game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				closeGame();
			}
		});
		setLayout(new BorderLayout());
		
		World.setInventory(new Inventory(width));
		
		mui = new MUI(this, width, height);
		add(mui, BorderLayout.CENTER);
		mui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] i = e.getActionCommand().split(",");
				if (i.length == 2) {
					startGame(i[0],i[1]);
				}else {
					startGame(i[0],i[1],i[2]);
				}
			}
		});
		
		pack();
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void closeGame() {
		if (gui != null) {
			FileManager.worldSave();
		}
		if (!closeWindow.isVisible()) {
			closeWindow.setVisible(true);
		}
	}
	
	private void startGame(String worldGeneratorType, String worldSize) {
		remove(mui);
		repaint();
		
		gui = new GUI(width, height, WorldGenerator.worldGenerationTypeConverter(worldGeneratorType), WorldGenerator.worldSizeConverter(worldSize), "0");
		add(gui,BorderLayout.CENTER);
		
		revalidate();
		pack();
		
		gui.requestFocus();
	}
	
	private void startGame(String worldGeneratorType, String worldSize, String needsPlayer) {
		remove(mui);
		repaint();
		
		gui = new GUI(width, height, WorldGenerator.worldGenerationTypeConverter(worldGeneratorType), WorldGenerator.worldSizeConverter(worldSize), needsPlayer);
		add(gui,BorderLayout.CENTER);
		
		revalidate();
		pack();
		
		gui.requestFocus();
	}
	
	private CloseWindow closeWindow = new CloseWindow();
	
	public class CloseWindow extends JFrame {
		
		private static final long serialVersionUID = 1L;
		
		public CloseWindow() {
			setTitle("Exiting?");
			setResizable(false);
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
			JRootPane rootPane = SwingUtilities.getRootPane(this); 
			rootPane.setDefaultButton(yes);
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
			setLocationRelativeTo(this);
		}
	}
	
	public static void main(String[] args) {
		new UIManager();
	}
	
}
