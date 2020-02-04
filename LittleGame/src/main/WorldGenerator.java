package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class WorldGenerator {

	public static final int NORMAL_G = 0;
	
	public static final int SMALL_W = 100;
	public static final int MEDIUM_W = 500;
	public static final int LARGE_W = 1000;
	
	private int blockSize = 40;
	private ArrayList<Block> world = new ArrayList<Block>();
	
	private int generationType;
	private int worldSize;
	
	public WorldGenerator(int generationType, int worldSize) {
		this.generationType = generationType;
		this.worldSize = worldSize;
	}
	
	public void startGeneration() {
		if (generationType == NORMAL_G) {
			System.out.println("World Generation Started");
			for (int x=-worldSize/2;x<worldSize/2;x++) {
				Block block = new Block(blockSize, blockSize);
				block.setX(x*blockSize);
				block.setY(750);
				world.add(block);
			}
			System.out.println("World Generation Ended");
		}
	}
	
	public ArrayList<GameObject> getWorld() {
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		for (Block block: world) {
			gameObjects.add(block);
		}
		return gameObjects;
	}
	
	public void paintWorld(Graphics g) {
		
	}
	
}
