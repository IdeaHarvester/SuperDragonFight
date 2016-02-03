package TileMap;

import java.awt.Graphics2D;

import Main.GamePanel;

public class MenuBackground extends Background {
	
	public MenuBackground(String s, double ms) {
		super(s, ms);
		this.moveScale = ms;
	}
	
	public void update() {

		if (x>0)
			dx=-moveScale;
		if (x<-image.getWidth()+GamePanel.WIDTH)
			dx = moveScale;
		x += dx;
		y += dy;
	}
	
	public void draw (Graphics2D g) {
		if(x < 0) {//draw the extra image on the right if the bg is out of screen
			g.drawImage(
				image,
				(int)x, 
				(int)y,
				null
			);
		}
		if(x > 0) {//draw the image if the bg is out of screen on the right
			g.drawImage(
				image,
				(int)x, 
				(int)y,
				null
			);
		}
	}
}
