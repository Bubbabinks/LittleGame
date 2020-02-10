package main;

import java.awt.Color;
import java.util.ArrayList;

public class WorldGenerator {

	public static final int NORMAL_G = 0;
	public static final int FLAT_G = 1;
	
	public static final int EXTRA_SMALL_W = 20;
	public static final int SMALL_W = 100;
	public static final int MEDIUM_W = 500;
	public static final int LARGE_W = 1000;
	
	private int blockSize = 40;
	private int maxRandomValue = 5;
	private ArrayList<Block> world = new ArrayList<Block>();
	
	private int generationType;
	private int worldSize;
	
	public WorldGenerator(int generationType, int worldSize) {
		this.generationType = generationType;
		this.worldSize = worldSize;
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
		}
	}
	
	public ArrayList<GameObject> getWorld() {
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		for (Block block: world) {
			gameObjects.add(block);
		}
		return gameObjects;
	}
	
	public int getBlockSize() {
		return this.blockSize;
	}
	
}
