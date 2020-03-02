package main;

import java.awt.Color;
import java.awt.Graphics;

public class Block implements GameObject {
	
	private int width, height, x = 0, y = 0, globalX=0, globalY=0;
	private Color color;
	
	public Block(int width, int height) {
		this.width = width;
		this.height = height;
		//Black And White Random Color
		//int random = (int)(Math.random()*200d);
		//color = new Color(random, random, random);
		//All Color Random Color
		color = new Color((int)(Math.random()*200d), (int)(Math.random()*200d), (int)(Math.random()*200d));
	}
	
	public Block(int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void drawObject(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public Color getColor() {
		return color;
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
	
	public void setColor(Color color) {
		this.color = color;
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
	
	public int getGlobalX() {
		return globalX;
	}

	public int getGlobalY() {
		return globalY;
	}

	public void setGlobalX(int x) {
		this.globalX = x;
	}

	public void setGlobalY(int y) {
		this.globalY = y;
	}
	
}
