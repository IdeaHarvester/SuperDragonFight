package GameState;

import java.awt.*;

import com.sun.glass.events.KeyEvent;

import TileMap.Background;
import TileMap.MenuBackground;

public class MenuOptionsState extends GameState {

	private Background bg;
	
	//menu (a bunch of choice)
	private String[] options = {
			"In Progress",
			"Press ESC"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	//regular font
	private Font font; 
	
	//reference to the GameStateManager
	public MenuOptionsState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			
			bg = new MenuBackground ("/Backgrounds/menu.gif", 0.3);
			bg.setVector(-0.3, 0);
			
			titleColor = new Color (255,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			
			font = new Font ("Arial", Font.PLAIN, 12);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {};
	public void update() {
		
		bg.update();
		
	};
	public void draw(Graphics2D g) {
		
		//draw the background
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Options", 20, 70);
		
		//draw the menu
		g.setFont(font);
		for (int i = 0; i< options.length; i++) {
			g.setColor(Color.BLACK);
			if (i == 1)
				g.drawString(options[i], 145, 200+ i*15);
			else 
				g.drawString(options[i], 145, 140+ i*15);
		}
		
	};
	

	
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	};
	public void keyReleased(int k) {};
	
}
