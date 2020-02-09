package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player implements GameObject{

	private int x = 0, y = 0;
	private int width, height;
	
	private Color color = Color.BLACK;
	
	public Player(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void drawObject(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public boolean collideWith(GameObject gameObject, int check) {
		if (check==2 && gameObject.getX()+gameObject.getWidth()>x && x+width>gameObject.getX()) {
			if (gameObject.getY() <= y+height && y<gameObject.getY()+gameObject.getHeight()) {
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
		if (check==0 && gameObject.getX()+gameObject.getWidth()>x && x+width>gameObject.getX()) {
			if (gameObject.getY()+gameObject.getWidth() >= y && y+height>gameObject.getY()) {
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
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
