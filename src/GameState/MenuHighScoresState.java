package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Saves.HighScoreManager;
import TileMap.Background;
import TileMap.MenuBackground;

import com.sun.glass.events.KeyEvent;

public class MenuHighScoresState extends GameState{
	
private Background bg;
private HighScoreManager hsm = new HighScoreManager();
private ArrayList<String> players; 
	
	private Color titleColor;
	private Font titleFont;
	
	//regular font
	private Font font; 
	
	//reference to the GameStateManager
	public MenuHighScoresState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		try {
			players = hsm.getPlayers();
			
			bg = new MenuBackground ("/Backgrounds/BlackBg.gif", 0.3);
			bg.setVector(-0.3, 0);
			
			titleColor = new Color (255,255,255);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			
			font = new Font ("Arial", Font.PLAIN, 12);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	};
	public void update() {
		
		bg.update();
		
	};
	public void draw(Graphics2D g) {
		
		//draw the background
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("High Scores", 20, 70);
		
		//draw the High Scores
		g.setFont(font);
		for (int i = 0; i< players.size(); i++) {
			
			StringBuffer sb = new StringBuffer();
			sb.append(players.get(i));
						
			g.drawString(sb.substring(0, sb.indexOf(" ")), 50, 100+ i*15);
			g.drawString(sb.substring(sb.indexOf(" ")), 200, 100+ i*15);
		}
	};
	

	
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	};
	public void keyReleased(int k) {};

}
