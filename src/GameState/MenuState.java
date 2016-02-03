package GameState;

import java.awt.*;

import com.sun.glass.events.KeyEvent;

import TileMap.Background;
import TileMap.MenuBackground;

public class MenuState extends GameState {

	private Background bg;
	
	//menu (a bunch of choice)
	private int currentChoice = 0;
	private String[] options = {
			"Start game",
			"High score",
			"Options",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	//regular font
	private Font font; 
	
	//reference to the GameStateManager
	public MenuState(GameStateManager gsm) {
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
		g.drawString("Super Dragon Fight", 20, 70);
		
		//draw the menu
		g.setFont(font);
		for (int i = 0; i< options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			if (i==0|i==1)
				g.drawString(options[i], 130, 140+ i*15);
			if (i==2)
				g.drawString(options[i], 140, 140+ i*15);
			if(i==3)
			g.drawString(options[i], 145, 140+ i*15);
		}
		
	};
	
	private void select (){
		if (currentChoice == 0) {
			//start
			//gsm.setState(GameStateManager.LEVEL1STATE);
			gsm.setState(GameStateManager.CHOOSEPLAYERSTATE);
		}
		if (currentChoice == 1) {
			//High Scores
			gsm.setState(GameStateManager.HIGHSCORESSTATE);
		}
		if (currentChoice == 2) {
			//Options
			gsm.setState(GameStateManager.OPTIONSSTATE);
		}
		if (currentChoice == 3) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {//if the user press enter
			select();
		}
		if (k == KeyEvent.VK_UP){
			currentChoice --;
			if (currentChoice == -1) {
				currentChoice = options.length-1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length){
				currentChoice = 0;
			}
		}
	};
	public void keyReleased(int k) {};
	
}
