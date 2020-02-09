package main;

import java.awt.Color;
import java.awt.Graphics;

public interface GameObject {
	
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	
	public int getWidth();
	public int getHeight();
	public void setWidth(int width);
	public void setHeight(int height);
	
	public Color getColor();
	public void setColor(Color color);
	
	public void drawObject(Graphics g);
	
}
