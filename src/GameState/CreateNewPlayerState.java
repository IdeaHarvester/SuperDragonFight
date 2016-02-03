package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.GamePanel;

import com.sun.glass.events.KeyEvent;

public class CreateNewPlayerState extends GameState {

	private String[] alphabet = {"a","b","c","d","e","f","g","h","i",
								"j","k","l","m","n","o","p","q","r",
								"s","t","u","v","w","x","y","z","delete", "OK"};
	private StringBuffer currentPlayer;
	private int currentChoice;
	private int maxHight;
	private int maxLength;
	
	private int currentHight;
	private int currentLength;
			
		private Color titleColor;
		private Font titleFont;
			
		//regular font
		private Font font; 
			
		//reference to the GameStateManager
		public CreateNewPlayerState(GameStateManager gsm) {
			this.gsm = gsm;
			init();
		}
			
		public void init() {
		
			maxHight = 2;
			maxLength = 8;
			currentHight = 0;
			currentLength = 0;
			
			titleColor = new Color (255,255,255);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
				
			font = new Font ("Arial", Font.PLAIN, 12);
			currentPlayer = new StringBuffer();
		};
		public void update() {};
		public void draw(Graphics2D g) {
				
			//draw the background
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			
			//draw title
			g.setColor(titleColor);
			g.setFont(titleFont);
			g.drawString("Create New Player", 20, 70);
				
			//draw the High Scores
			g.setFont(font);

			g.drawString(currentPlayer.toString(), 50, 95);
			g.drawLine(50, 100, 170, 100);
			
			int counter = 0;
			for (int i = 0; i< 3; i++) {
				for (int j = 0; j< 9; j++){
					if(counter == currentChoice) {
						g.setColor(Color.RED);
					}
					else {
						g.setColor(Color.WHITE);
					}
					if (counter<27){
						g.drawString(alphabet[counter], 50+j*24, 130+ i*15);
					}
					
					counter++;
				}
			}
			if(counter == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(alphabet[counter], 285, 160);
		};
			
		private void select (){
			
			if (currentChoice == 26){
				if (currentPlayer.length()>0) {
					currentPlayer = currentPlayer.deleteCharAt(currentPlayer.length()-1);
				}
			}
			else 
				if (currentChoice == 27) {
					gsm.setPlayer(currentPlayer.toString());
					gsm.setState(GameStateManager.LEVEL1STATE);
				}
				else {
					currentPlayer.append(alphabet[currentChoice]);
			}
			
			
		}
		
		public void keyPressed(int k) {
			if (k == KeyEvent.VK_ESCAPE) {
				gsm.setState(GameStateManager.CHOOSEPLAYERSTATE);
			}
			if (k == KeyEvent.VK_ENTER) {//if the user press enter
				select();
			}
			if (k == KeyEvent.VK_UP && currentLength != maxLength+1){
				currentHight --;
				if (currentHight < 0) {
					currentHight = maxHight;
				}
			}
			if (k == KeyEvent.VK_DOWN &&currentLength != maxLength+1) {
				currentHight ++;
				if (currentHight > maxHight) {
					currentHight = 0;
				}
			}
			if (k == KeyEvent.VK_RIGHT) {
				currentLength ++;
				if (currentLength > maxLength && currentHight !=maxHight) {
					currentLength = 0;
				}
				if (currentLength > maxLength+1) {
					currentLength = 0;
				}
			}
			if (k == KeyEvent.VK_LEFT) {
				currentLength --;
				if (currentLength < 0 && currentHight !=maxHight) {
					currentLength = maxLength;
				}
				if (currentLength < 0) {
					currentLength = maxLength+1;
				}
			}
			currentChoice = (currentLength+currentHight*(maxLength+1));
		}
			
		public void keyReleased(int k) {};
	
}
