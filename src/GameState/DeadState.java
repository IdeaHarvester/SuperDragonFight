package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import Audio.AudioPlayer;
import Main.GamePanel;

public class DeadState extends GameState {
	
	private AudioPlayer music;
	
	private Font font;
	private int currentChoice = 0;
	private String[] options = {
			"Restart",
			"Menu",
			"Exit"
	};
	
	public DeadState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		// fonts
		font = new Font("Century Gothic", Font.PLAIN, 26);
		
		music = new AudioPlayer("/Music/DiedSound.mp3");
		init();
		
	}
	
	public void init() {
		music.play();
	}
	
	public void update() {
		//handleInput();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(30, 30, GamePanel.WIDTH-60, GamePanel.HEIGHT-60);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("You Died", 90, 90);
		
		g.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		for (int i = 0; i< options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 90, 140+ i*15);
		}
	}
	
	
	private void select (){
		
		if (currentChoice == 0) {
			gsm.setPaused(false);
			gsm.setPreviousState();
		}
		if (currentChoice == 1) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}
	
	//check up what a hell is this
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setPaused(false);
			//gsm.setState(gsm.LEVEL1STATE);
		}
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
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}