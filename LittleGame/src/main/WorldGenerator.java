package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class WorldGenerator {

	public static final int NORMAL_G = 0;
	public static final int FLAT_G = 1;
	public static final int RANDOM_G = 2;
	public static final int INFINITE_G = 3;
	
	public static final int EXTRA_SMALL_W = 25;
	public static final int SMALL_W = 100;
	public static final int MEDIUM_W = 500;
	public static final int LARGE_W = 1000;
	
	public static final String[] GENERATION_TYPES = {"NORMAL_G", "FLAT_G", "RANDOM_G", "INFINITE_G"};
	public static final String[] WORLDSIZE_TYPES = {"EXTRA_SMALL_W", "SMALL_W", "MEDIUM_W", "LARGE_W"};
	public static final int[] WORLDSIZE_VALUES = {EXTRA_SMALL_W, SMALL_W, MEDIUM_W, LARGE_W};
	
	private int blockSize = 40;
	private int maxRandomValue = 5;
	private ArrayList<GameObject> world;
	
	private int generationType;
	private int worldSize;
	
	private Player player;
	
	public WorldGenerator(int generationType, int worldSize, ArrayList<GameObject> world) {
		this.generationType = generationType;
		this.worldSize = worldSize;
		this.world = world;
	}
	
	public static int worldGenerationTypeConverter(String GenerationType) {
		for (int i=0;i<GENERATION_TYPES.length;i++) {
			if (GenerationType.equalsIgnoreCase(GENERATION_TYPES[i])) {
				return i;
			}
		}
		return 0;
	}
	
	public static int worldSizeConverter(String worldSize) {
		for (int i=0;i<WORLDSIZE_TYPES.length;i++) {
			if (worldSize.equalsIgnoreCase(WORLDSIZE_TYPES[i])) {
				return WORLDSIZE_VALUES[i];
			}
		}
		return WORLDSIZE_VALUES[1];
	}
	
	public void startGeneration() {
		if (generationType == NORMAL_G) {
			int blockHeight = 0;
			int previousChange = 0;
			for (int x=-worldSize/2;x<worldSize/2;x++) {
				for (int y=0;y<100-blockHeight;y++) {
					Block block = new Block(blockSize, blockSize);
					block.setX(x*blockSize);
					block.setY(blockSize*15+(blockSize*(y+blockHeight)));
					if (y == 0) {
						block.setColor(new Color(126,255,51));
					}else if (y < 4) {
						block.setColor(new Color(181,143,76));
					}else {
						block.setColor(new Color(130,130,130));
					}
					world.add(block);
				}
				int random = (int)(Math.random()*3d);
				if (random == 0 && blockHeight-1>=-maxRandomValue && previousChange <= 0) {
					previousChange = -1;
					blockHeight--;
				}else if (random == 2 && blockHeight+1<=maxRandomValue && previousChange >= 0) {
					previousChange = 1;
					blockHeight++;
				}else {
					previousChange = 0;
				}
			}
		}else if (generationType == FLAT_G) {
			for (int x=-worldSize/2;x<worldSize/2;x++) {
				for (int y=0;y<100;y++) {
					Block block = new Block(blockSize, blockSize);
					block.setX(x*blockSize);
					block.setY(750+(blockSize*(y)));
					if (y == 0) {
						block.setColor(new Color(126,255,51));
					}else if (y < 4) {
						block.setColor(new Color(181,143,76));
					}else {
						block.setColor(new Color(130,130,130));
					}
					world.add(block);
				}
			}
		}else if (generationType == RANDOM_G) {
			for (int x=-worldSize/2;x<worldSize/2;x++) {
				for (int y=-worldSize/2;y<worldSize/2;y++) {
					if (Math.random() > 0.5) {
						Block block = new Block(blockSize, blockSize);
						block.setX(x*blockSize);
						block.setY(750+(blockSize*(y)));
						world.add(block);
					}
				}
			}
		}else if (generationType == INFINITE_G) {
			System.out.println("Forgot to add Player to World Generator: ERROR");
			System.exit(0);
		}
	}
	
	public void startGeneration(Player player) {
		this.player = player;
		if (generationType != INFINITE_G) {
			System.out.println("Forgot to remove Player frome World Generator: ERROR");
			System.exit(0);
		}
		int blockHeight = 0;
		int previousChange = 0;
		for (int x=-worldSize/2;x<worldSize/2;x++) {
			for (int y=0;y<100-blockHeight;y++) {
				Block block = new Block(blockSize, blockSize);
				block.setX(x*blockSize);
				block.setY(blockSize*15+(blockSize*(y+blockHeight)));
				if (y == 0) {
					block.setColor(new Color(126,255,51));
				}else if (y < 4) {
					block.setColor(new Color(181,143,76));
				}else {
					block.setColor(new Color(130,130,130));
				}
				world.add(block);
			}
			int random = (int)(Math.random()*3d);
			if (random == 0 && blockHeight-1>=-maxRandomValue && previousChange <= 0) {
				previousChange = -1;
				blockHeight--;
			}else if (random == 2 && blockHeight+1<=maxRandomValue && previousChange >= 0) {
				previousChange = 1;
				blockHeight++;
			}else {
				previousChange = 0;
			}
		}
		int minBlockHeight = 0;
		int minX = -(worldSize/2);
		int maxX = -(worldSize/2);
		Timer timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		timer.start();
	}
	
	public int getBlockSize() {
		return this.blockSize;
	}
	
}
