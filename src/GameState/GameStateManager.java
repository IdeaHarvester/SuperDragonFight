package GameState;

import java.awt.Font;

import Main.GamePanel;
import Saves.HighScoreManager;

//creation and initialization of game states
public class GameStateManager {

	private HighScoreManager hsm;
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	private int score;
	private String currentPlayer;
	
	private PauseState pauseState;
	private boolean paused;
	private boolean music;
	private boolean sfx;
	
	public static final int NUMGAMESTATES = 10;
	public static final int MENUSTATE = 0; 
	
	public static final int LEVEL1STATE = 1;
	public static final int LEVEL2STATE = 2;
	
	public static final int OPTIONSSTATE = 3;
	public static final int HIGHSCORESSTATE = 4;
	public static final int CHOOSEPLAYERSTATE = 5;
	public static final int CREATENEWPLAYERSTATE = 6;
	public static final int LEVELSELECTSTATE = 7;
	
	public static final int DEADSTATE = 8;
	public static final int WINSTATE = 9;
	
	public GameStateManager() {
		gameStates = new GameState[NUMGAMESTATES];
		pauseState = new PauseState(this);
		
		currentState = 	MENUSTATE;
		previousState = currentState;
		loadState(currentState);
		
		hsm = new HighScoreManager();
		currentPlayer = new String();
	}
	
	private void loadState(int state){
		if (state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if (state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		if (state == LEVEL2STATE)
			gameStates[state] = new Level2State(this);
		if (state == LEVELSELECTSTATE)
			gameStates[state] = new LevelSelectState(this);
		if (state == OPTIONSSTATE)
			gameStates[state] = new MenuOptionsState(this);
		if (state == HIGHSCORESSTATE)
			gameStates[state] = new MenuHighScoresState(this);
		if (state == CHOOSEPLAYERSTATE)
			gameStates[state] = new ChoosePlayerState(this);
		if (state == CREATENEWPLAYERSTATE)
			gameStates[state] = new CreateNewPlayerState(this);
		if (state == DEADSTATE)
			gameStates[state] = new DeadState(this);
		if (state == WINSTATE)
			gameStates[state] = new WinState(this);
	}
	
	private void unloadState(int state){
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		previousState = currentState;
		currentState = state;
		loadState(currentState);		
	}
	
	public void setPreviousState(){
		unloadState(currentState);
		currentState = previousState;
		loadState(currentState);
	}
	
	public void setPaused(boolean b) { paused = b; }
	
	public void update() {
		
		if(paused) {
			pauseState.update();
			return;
		}
		if(gameStates[currentState] != null) 
			gameStates[currentState].update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		
		
		if(paused) {
			pauseState.draw(g);
			return;
		}
		if(gameStates[currentState] != null) gameStates[currentState].draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			g.setColor(java.awt.Color.WHITE);
			g.drawString("Loading...", 250, 220);
			g.setFont(new Font("Century Gothic", Font.PLAIN, 17));
			g.drawString("Super Dragon Fight", 30, 210);
			g.setFont(new Font("Century Gothic", Font.PLAIN, 11));
			g.drawString("2015 Vlad C.", 30, 230);
			g.drawString(currentPlayer, 100, 100);
		}

	}
	
	public void keyPressed(int k) {
		try {
			if (paused) {
				pauseState.keyPressed(k);
			}
			else
				if (gameStates[currentState] != null)
					gameStates[currentState].keyPressed(k);
		}catch (Exception e) {
			System.out.println("лажа в keyPressed"); 
		}
	}
	
	public void keyReleased(int k) {
		try{
			if (gameStates[currentState] != null)
				gameStates[currentState].keyReleased(k);
		}catch (Exception e) {
			System.out.println("лажа в keyReleased");
		}
	}
	
	public void setPlayer(String player) {
		this.currentPlayer = player;
	}
	
	public void save(int score){
		try {
			hsm.add(currentPlayer, score);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.score = score;
	}
	
	public int getScore() {return score;}
	public void setMusic(boolean b) {music = b;}
	public void setSFX(boolean b) {sfx = b;}
	public boolean getMusic() {return music;}
	public boolean getSFX() {return sfx;}
	
}
