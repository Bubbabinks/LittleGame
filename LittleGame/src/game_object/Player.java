package game_object;

import java.awt.Color;
import java.awt.Graphics;

public class Player implements GameObject{

	private int x = 0, y = 0;
	private int width, height;
	private int globalX = 0, globalY = 0;
	
	private int maxJumpHeight = 100;
	private int jump = 0;
	
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
				jump = -1;
				return true;
				}
			}
		if (check == 2 && jump == -1) {
			jump = 0;
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
				jump = 0;
				return true;
			}
		}
		return false;
	}
	
	public void setGlobalX(int x) {
		this.globalX = x;
	}
	
	public void setGlobalY(int y) {
		this.globalY = y;
	}
	
	public int getGlobalX() {
		return globalX;
	}
	
	public int getGlobalY() {
		return globalY;
	}
	
	public void setJump(int jump) {
		this.jump = jump;
	}
	
	public int getJump() {
		return jump;
	}
	
	public int getMaxJumpHeight() {
		return maxJumpHeight;
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
