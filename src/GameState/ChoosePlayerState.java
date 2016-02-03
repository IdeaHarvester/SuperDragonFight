package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Main.GamePanel;
import Saves.HighScoreManager;


import com.sun.glass.events.KeyEvent;

public class ChoosePlayerState extends GameState{

private HighScoreManager hsm = new HighScoreManager();
private ArrayList<String> players; 
private String[] options;
private String currentPlayer;
private int numberOfPlayers;
private int currentChoice;
		
	private Color titleColor;
	private Font titleFont;
		
	//regular font
	private Font font; 
		
	//reference to the GameStateManager
	public ChoosePlayerState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
		
	public void init() {
		try {
			players = hsm.getPlayers();
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		numberOfPlayers = players.size();
		options  = new String[numberOfPlayers+1];
		
		options[0] = "NewPlayer";
		for (int i = 1; i< players.size()+1; i++) {
			options[i] =  players.get(i-1).substring(0, players.get(i-1).indexOf(" "));
		}
	
		titleColor = new Color (255,255,255);
		titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			
		font = new Font ("Arial", Font.PLAIN, 12);
		currentPlayer = options[0];
			
	};
	public void update() {};
	public void draw(Graphics2D g) {
			
		//draw the background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Choose The Player", 20, 70);
			
		//draw the High Scores
		g.setFont(font);

		for (int i = 0; i< options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 50, 100+ i*15);
		}
		g.drawString(currentPlayer, 200, 100);
	};
		
	private void select (){
		if (currentChoice == 0) {
			//create new player
			gsm.setState(GameStateManager.CREATENEWPLAYERSTATE);
		}
		else{
			gsm.setPlayer(currentPlayer);
			//gsm.setState(GameStateManager.LEVEL1STATE);
			gsm.setState(GameStateManager.LEVELSELECTSTATE);
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
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
		currentPlayer = options[currentChoice];
	};
	public void keyReleased(int k) {};
	
}
