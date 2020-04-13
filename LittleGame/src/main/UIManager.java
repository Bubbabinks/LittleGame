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

import game_object.Inventory;
import panels.GUI;
import panels.MUI;
import world_utils.World;
import world_utils.WorldGenerator;

public class UIManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 975, height = 850;
	private GUI gui;
	private MUI mui;
	private AudioManager audioManager = new AudioManager();
	
	public UIManager() {
		FileManager.checkFileStructure();
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
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void closeGame() {
		if (gui != null) {
			FileManager.worldSave(World.getWorldName());
		}
		System.exit(0);
	}
	
	private void startGame(String worldGeneratorType, String worldSize) {
		remove(mui);
		repaint();
		
		gui = new GUI(width, height, WorldGenerator.worldGenerationTypeConverter(worldGeneratorType), WorldGenerator.worldSizeConverter(worldSize), "0");
		gui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(gui);
				gui = null;
				repaint();
				mui.loadMainScreen();
				add(mui, BorderLayout.CENTER);
				revalidate();
			}
			
		});
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
	
	public static void main(String[] args) {
		new UIManager();
	}
	
}
