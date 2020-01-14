package main;

import java.awt.Graphics;

public class Player {

	private int x = 0, y = 0;
	private int width, height;
	private BoxCollider boxCollider = new BoxCollider();
	
	public Player(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void drawPlayer(Graphics g) {
		g.fillRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
