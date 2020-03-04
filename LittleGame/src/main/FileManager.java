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

public class FileManager {

	private static ArrayList<GameObject> world = World.getWorld();
	
	public static void worldLoad() {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
		fileChooser.setDialogTitle("Select World File");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("World File", "world");
		fileChooser.addChoosableFileFilter(filter);
		int val = fileChooser.showOpenDialog(null);
		if (val != JFileChooser.APPROVE_OPTION) {
			return;
		}
		readWorld(fileChooser.getSelectedFile().getPath());
	}
	
	public static void worldSave() {
		ArrayList<GameObject> world = World.getWorld();
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
		fileChooser.setDialogTitle("Save");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("World File .world", "world");
		fileChooser.addChoosableFileFilter(filter);
		int val = fileChooser.showSaveDialog(null);
		if (val != JFileChooser.APPROVE_OPTION) {
			return;
		}
		writeWorld(fileChooser.getSelectedFile().getPath());
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
