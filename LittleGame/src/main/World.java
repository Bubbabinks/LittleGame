package main;

import java.awt.Color;
import java.util.ArrayList;

public class World {
	
	//Different colors in game
	public static Color 
			SKY = new Color(77, 195, 255),
			GRASS = new Color(0, 153, 25),
			DIRT = new Color(153, 102, 0),
			STONE = new Color(105,105,105);
	
	private static ArrayList<GameObject> world = new ArrayList<GameObject>();
	private static Boolean newWorld = true;
	
	public static Inventory inventory;
	private static String worldName = "";
	
	private static int globalShiftX = 0, globalShiftY = 0;
	
	public static void setInventory(Inventory inventory) {
		World.inventory = inventory;
	}
	
	public static void setWorldName(String name) {
		worldName = name;
	}
	
	public static String getWorldName() {
		return worldName;
	}
	
	public static int getGlobalShiftX() {
		return globalShiftX;
	}
	
	public static int getGlobalShiftY() {
		return globalShiftY;
	}
	
	public static void setGlobalShiftX(int shift) {
		globalShiftX = shift;
	}
	
	public static void setGlobalShiftY(int shift) {
		globalShiftY = shift;
	}
	
	public static ArrayList<GameObject> getWorld() {
		return world;
	}
	
	public static Boolean getNewWorld() {
		return newWorld;
	}
	
	public static void setNewWorld(boolean bool) {
		World.newWorld = bool;
	}
	
}
