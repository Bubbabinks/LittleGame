package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class World {

	public static final int NORMAL_G = 0;
	
	public static final int SMALL_W = 100;
	public static final int MEDIUM_W = 500;
	public static final int LARGE_W = 1000;
	
	private int blockSize = 40;
	private ArrayList<Block> world = new ArrayList<Block>();
	
	private int generationType;
	private int worldSize;
	
	public World(int generationType, int worldSize) {
		this.generationType = generationType;
		this.worldSize = worldSize;
	}
	
	public void startGeneration() {
		if (generationType == NORMAL_G) {
			int StartingX = -(worldSize*blockSize)/2;
			int StartingY = -(worldSize*blockSize)/2;
			for (int i=0;i<worldSize;i++) {
				for (int e=0;e<worldSize;e++) {
					Block block = new Block(blockSize,blockSize);
					block.setX(StartingX+(e*blockSize));
					block.setY(StartingY+(i*blockSize));
					world.add(block);
				}
			}
		}
	}
	
	public ArrayList<Block> getBlocks() {
		return world;
	}
	
	public void paintWorld(Graphics g) {
		
	}
	
}
