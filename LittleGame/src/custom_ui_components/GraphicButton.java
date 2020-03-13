package custom_ui_components;

import java.awt.Color;
import java.awt.Graphics;

public class GraphicButton {

	private int width, height,x = 0,y = 0;
	private String txt;
	private Color background = new Color(181, 143, 76), foreground = Color.WHITE, border = new Color(121, 83, 16);
	private int borderWidth = 3;
	
	public GraphicButton(int width, int height, String txt) {
		this.width = width;
		this.height = height;
		this.txt = txt;
	}
	
	public void drawButton(Graphics g) {
		g.setColor(background);
		g.fillRect(x, y, width, height);
		g.setColor(border);
		for (int i=0;i<borderWidth;i++) {
			g.drawRect(x+i, y+i, width-(i*2), height-(i*2));
		}
		g.setColor(foreground);
		int stringWidth = g.getFontMetrics().stringWidth(txt);
		g.drawString(txt, x+(width/2)-(stringWidth/2), y+(height/2)+(g.getFontMetrics().getHeight()/4));
		
	}
	
}
