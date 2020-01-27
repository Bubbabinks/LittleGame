package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private int x = 0, y = 0;
	private int width, height;
	
	public Player(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void drawPlayer(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
	
	public int collideWith(GameObject gameObject) {
		if (x+width<gameObject.getWidth()+gameObject.getX()+1 && x>gameObject.getX()-1) {
			if (y+height == gameObject.getY()-1) {
				return 2;
			}
		}
		return 4;
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
