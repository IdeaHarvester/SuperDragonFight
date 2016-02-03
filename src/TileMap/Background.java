package TileMap;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Background {
	
	protected BufferedImage image;
	
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	protected double moveScale; //the speed of the moving bsckground
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % image.getWidth();
		this.y = (y * moveScale) % image.getHeight();
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {//draw the extra image on the right if the bg is out of screen
			g.drawImage(
				image,
				(int)x + image.getWidth(),
				(int)y,
				null
			);
		}
		if(x > 0) {//draw the image if the bg is out of screen on the right
			g.drawImage(
				image,
				(int)x - GamePanel.WIDTH,
				(int)y,
				null
			);
		}
	}
	
}








