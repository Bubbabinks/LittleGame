package main;

import java.awt.Color;
import java.awt.Graphics;

public class Block implements GameObject {
	
	private int width, height, x = 0, y = 0;
	private Color color;
	
	public Block(int width, int height) {
		this.width = width;
		this.height = height;
		int random = (int)(Math.random()*200d);
		color = new Color(random, random, random);
	}
	
	public void drawBlock(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
