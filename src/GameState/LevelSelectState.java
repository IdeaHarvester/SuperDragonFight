package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.GamePanel;

import com.sun.glass.events.KeyEvent;

public class LevelSelectState extends GameState {

	private String[] options = {"1","2","3"};
	private int currentChoice;
			
		private Color titleColor;
		private Font titleFont;
			
		//regular font
		private Font font; 
			
		//reference to the GameStateManager
		public LevelSelectState(GameStateManager gsm) {
			this.gsm = gsm;
			init();
		}
			
		public void init() {
			currentChoice = 0;
			
			titleColor = new Color (255,255,255);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
				
			font = new Font ("Arial", Font.PLAIN, 12);
		};
		public void update() {};
		public void draw(Graphics2D g) {
				
			//draw the background
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			
			//draw title
			g.setColor(titleColor);
			g.setFont(titleFont);
			g.drawString("Level", 20, 70);
				
			//draw the High Scores
			g.setFont(font);
			
			
			
			//g.setColor(Color.BLACK);
			
			for (int i = 0; i< options.length; i++) {
				
				if (currentChoice == i){
					g.setColor(Color.YELLOW);
					g.fillRoundRect(50+i*45, 100, 30, 30, 10, 10);
					g.setColor(Color.GRAY);
					g.drawRoundRect(50+i*45, 100, 30, 30, 10, 10);
					g.drawString(options[i], 62+i*45, 120);
				}
				else{
					g.setColor(Color.WHITE);
					g.fillRoundRect(50+i*45, 100, 30, 30, 10, 10);
					g.setColor(Color.GRAY);
					g.drawRoundRect(50+i*45, 100, 30, 30, 10, 10);
					g.drawString(options[i], 62+i*45, 120);
				}
				if (currentChoice> 0) {
					g.drawString("Only for developers", 50, 200);
				}
			}
		}
			
		private void select (){
			
			if(currentChoice == 0) {gsm.setState(GameStateManager.LEVEL1STATE);}
			if(currentChoice == 1) {gsm.setState(GameStateManager.LEVEL2STATE);}
			
		}
		
		public void keyPressed(int k) {
			if (k == KeyEvent.VK_ESCAPE) {
				gsm.setState(GameStateManager.CHOOSEPLAYERSTATE);
			}
			if (k == KeyEvent.VK_ENTER) {//if the user press enter
				select();
			}
			if (k == KeyEvent.VK_RIGHT) {
				currentChoice ++;
				if (currentChoice == options.length) {
					currentChoice = 0;
				}

			}
			if (k == KeyEvent.VK_LEFT) {
				currentChoice --;
				if (currentChoice < 0) {
					currentChoice = options.length-1;
				}
			}
		}
			
		public void keyReleased(int k) {};
	
}
