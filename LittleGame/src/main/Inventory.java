package main;

import java.awt.Color;
import java.awt.Graphics;

public class Inventory {
	
	private int[] slotAmounts;
	private Color[] slots;
	private int width;
	private int selectedSlot = 1;
	private int maxSlot;
	
	public Inventory(int width) {
		this.width = width;
		maxSlot = ((width-100)/90);
		slots = new Color[maxSlot];
		slotAmounts = new int[maxSlot];
	}
	
	public int getSelectedSlot() {
		return selectedSlot;
	}
	
	public int[] getSlotAmounts() {
		return slotAmounts;
	}
	
	public void setSlotAmounts(int[] slotAmounts) {
		this.slotAmounts = slotAmounts;
	}
	
	public Color[] getSlots() {
		return slots;
	}
	
	public void setSlots(Color[] slots) {
		this.slots = slots;
	}
	
	public void setSelectedSlot(int selectedSlot) {
		this.selectedSlot = selectedSlot;
		while (this.selectedSlot > maxSlot) {
			this.selectedSlot = this.selectedSlot - maxSlot;
		}
		while (this.selectedSlot < 1) {
			this.selectedSlot = this.selectedSlot + maxSlot;
		}
	}
	
	public void drawObject(Graphics g) {
		g.setColor(new Color(150, 150, 150));
		g.fillRect(50, 0, width-100, 90);
		for (int i=0;i<maxSlot;i++) {
			if (i+1 != selectedSlot) {
				g.setColor(new Color(100, 100, 100));
			}else {
				g.setColor(new Color(60, 60, 60));
			}
			g.fillRect(70+(((width-100)%90)/((width-100)/90))/2+(90*i)+(((width-100)%90)*i/((width-100)/90)), 20, 50, 50);
		}
		for (int i=0;i<maxSlot;i++) {
			if (slotAmounts[i]!=0) {
				g.setColor(slots[i]);
				g.fillRect(80+(((width-100)%90)/((width-100)/90))/2+(90*i)+(((width-100)%90)*i/((width-100)/90)), 30, 30, 30);
				g.setColor(Color.BLACK);
				g.drawString(slotAmounts[i]+"", 82+(((width-100)%90)/((width-100)/90))/2+(90*i)+(((width-100)%90)*i/((width-100)/90)), 40);
			}
		}
	}

}
