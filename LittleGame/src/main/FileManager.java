package main;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import game_object.Block;
import game_object.GameObject;
import game_object.Inventory;
import world_utils.World;
import world_utils.WorldGenerator;

public class FileManager {

	public final static String GAME_FOLDER = System.getProperty("user.home")+"/Documents/LittleGame";
	
	private static ArrayList<GameObject> world = World.getWorld();
	
	public static void checkFileStructure() {
		File gameFolder = new File(GAME_FOLDER);
		File worldFolder = new File(GAME_FOLDER+"/saves");
		if (!gameFolder.exists()) {
			gameFolder.mkdir();
			worldFolder.mkdir();
		}else {
			if (!worldFolder.exists()) {
				worldFolder.mkdir();
			}
		}
	}
	
	public static void deleteWorld(String worldName) {
		File world = new File(GAME_FOLDER+"/saves/"+worldName+".world");
		deleteDir(world);
	}
	
	private static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	public static String[] getWorldsNames() {
		String[] list = new File(GAME_FOLDER+"/saves").list();
		for (int i=0;i<list.length;i++) {
			list[i] = list[i].substring(0, list[i].lastIndexOf("."));
		}
		return list;
	}
	
	public static void worldLoad(String worldName) {
		readWorld(GAME_FOLDER+"/saves/"+worldName+".world");
	}
	
	public static void worldSave(String worldName) {
		if (!World.getOverWrite()) {
			boolean taken = false;
			int interation = 0;
			do {
				taken = false;
				String[] worlds = getWorldsNames();
				for (String name: worlds) {
					if (interation == 0) {
						if (name.equals(worldName)) {
							taken = true;
						}
					}else {
						if (name.equals(worldName+interation)) {
							taken = true;
						}
					}
				}
				if (taken) {
					interation++;
				}
			}while (taken);
			if (interation == 0) {
				writeWorld(GAME_FOLDER+"/saves/"+worldName);
			}else {
				writeWorld(GAME_FOLDER+"/saves/"+worldName+interation);
			}
		}else {
			writeWorld(GAME_FOLDER+"/saves/"+worldName);
		}
	}
	
	private static void readWorld(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		Scanner sc;
		try {
			sc = new Scanner(file);
			Block block = null;
			Inventory inventory = World.inventory;
			Color[] slots = inventory.getSlots();
			int[] slotAmounts = inventory.getSlotAmounts();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.contains("id:Block")) {
					block = new Block(WorldGenerator.blockSize, WorldGenerator.blockSize);
				}
				if (line.contains("color:")) {
					String[] in = line.substring(line.indexOf(":")+1).split(",");
					block.setColor(new Color(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2])));
				}
				if (line.contains("GlobalX:")) {
					block.setX(Integer.parseInt(line.substring(line.indexOf(":")+1)));
					block.setGlobalX(Integer.parseInt(line.substring(line.indexOf(":")+1)));
				}
				if (line.contains("GlobalY:")) {
					block.setY(Integer.parseInt(line.substring(line.indexOf(":")+1)));
					block.setGlobalY(Integer.parseInt(line.substring(line.indexOf(":")+1)));
					world.add(block);
				}
				if (line.contains("slotid:")) {
					inventory.setSelectedSlot(Integer.parseInt(line.substring(line.indexOf(":")+1)));
				}
				if (line.contains("slotC:")) {
					String[] in = line.substring(line.indexOf(":")+1).split(",");
					slots[inventory.getSelectedSlot()-1] = new Color(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2]));
				}
				if (line.contains("slotamount:")) {
					slotAmounts[inventory.getSelectedSlot()-1] = Integer.parseInt(line.substring(line.indexOf(":")+1));
				}
			}
			inventory.setSelectedSlot(1);
			inventory.setSlots(slots);
			inventory.setSlotAmounts(slotAmounts);
			sc.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeWorld(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}else {
			if (!file.getPath().substring(file.getPath().lastIndexOf(".")+1).equals("world")) {
				file = new File(file.getPath()+".world");
			}
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			Inventory inventory = World.inventory;
			Color[] slots = inventory.getSlots();
			int[] slotAmounts = inventory.getSlotAmounts();
			for (int i=0;i<slots.length;i++) {
				writer.append("{\n"
						+ "\tid:Inventory\n"
						+ "\tslotid:"+(i+1)+"\n"
						+ "\tslotC:"+slots[i].getRed()+","+slots[i].getGreen()+","+slots[i].getBlue()+"\n"
						+ "\tslotamount:"+slotAmounts[i]+"\n"
						+ "}\n");
			}
			for (GameObject object: world) {
				writer.append("{\n"
						+ "\tid:Block\n"
						+ "\tcolor:"+object.getColor().getRed()+","+object.getColor().getGreen()+","+object.getColor().getBlue()+"\n"
						+ "\tGlobalX:"+object.getGlobalX()+"\n"
						+ "\tGlobalY:"+object.getGlobalY()+"\n"
						+ "}\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
