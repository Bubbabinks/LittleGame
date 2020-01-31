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
	
	public boolean collideWith(GameObject gameObject, int check) {
		if (check==2 && gameObject.getX()+gameObject.getWidth()>x && x+width>gameObject.getX()) {
			if (gameObject.getY() <= y+height) {
				return true;
			}
		}
		if (check==3 && gameObject.getY()+gameObject.getHeight()>y && y+height>gameObject.getY()) {
			if (gameObject.getX()+gameObject.getWidth() >= x && gameObject.getX()<x+width) {
				return true;
			}
		}
		if (check==1 && gameObject.getY()+gameObject.getHeight()>y && y+height>gameObject.getY()) {
			if (gameObject.getX()<=x+width && gameObject.getX()+gameObject.getWidth()>x) {
				return true;
			}
		}
		return false;
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
