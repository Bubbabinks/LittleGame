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
import javax.swing.SwingConstants;

public class UIManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 975, height = 850;
	private GUI gui;
	private MUI mui;
	
	public UIManager() {
		setTitle("Little Game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				closeGame();
			}
		});
		setLayout(new BorderLayout());
		
		mui = new MUI(this, width, height);
		add(mui, BorderLayout.CENTER);
		mui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] i = e.getActionCommand().split(",");
				startGame(i[0],i[1]);
			}
		});
		
		pack();
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void closeGame() {
		if (!closeWindow.isVisible()) {
			closeWindow.setVisible(true);
		}
	}
	
	private void startGame(String worldGeneratorType, String worldSize) {
		remove(mui);
		repaint();
		
		gui = new GUI(this, width, height, WorldGenerator.worldGenerationTypeConverter(worldGeneratorType), WorldGenerator.worldSizeConverter(worldSize));
		add(gui,BorderLayout.CENTER);
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
