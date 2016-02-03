package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {

	private Player player;
	
	private BufferedImage image;
	private Font font;
	
	public HUD(Player p) {
		player = p;
		try{
			
			image = ImageIO.read(
					getClass().getResourceAsStream(
							"/HUD/hud.gif"
					)
			);
			font  = new Font ("Arial", Font.PLAIN, 14);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, 100, 2, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(
				player.getHealth()+ "/"+ player.getMaxHealth(), 
				180, 
				15
		);
		g.drawString(
				player.getFire()/100 +"/"+ player.getMaxFire()/100, 
				108, 
				15
		);
	}
	
}
