package GameState;

import Entity.*;
import Entity.Enemy.Enemy;
import Entity.Enemy.Slugger;
import Main.GamePanel;
import TileMap.*;
import Audio.AudioPlayer;

import java.awt.*;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	private Player player;
	private HUD hud;
	private AudioPlayer bgMusic;

	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;

	// high score saving
	private int score;

	// timer:
	private long timer;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		// init timer
		timer = System.nanoTime();

		bg = new Background("/Backgrounds/Level-1.gif", 0.2);

		player = new Player(tileMap);
		player.setPosition(100, 100);

		populateEnemies();

		explosions = new ArrayList<Explosion>();

		hud = new HUD(player);

		bgMusic = new AudioPlayer("/Music/MainTheme.mp3");
		bgMusic.play();

	}

	private void populateEnemies() {

		enemies = new ArrayList<Enemy>();

		Slugger s;
		Point[] points = new Point[] { new Point(200, 200),
				new Point(400, 200), new Point(500, 110), new Point(2375, 50),
				new Point(1970, 200), new Point(2200, 200),
				new Point(2260, 200) };

		for (int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}

		// Gazer g = new Gazer(tileMap) ;
		// g.setPosition(150, 180);
		// enemies.add(g);
	}

	public void update() {
		// check if player dead event should start
		if (player.getHealth() == 0 || player.gety() > tileMap.getHeight() + 30) {
			bgMusic.stop();
			gsm.save(score);
			gsm.setState(GameStateManager.DEADSTATE);
		}

		// check if player has won the level
		if (player.getx() > tileMap.getWidth() - 100) {
			//bgMusic.stop();
			if ((System.nanoTime() - timer) / 1000000000 < 45)
				score = score * 2;
			else if ((System.nanoTime() - timer) / 1000000000 < 60)
				score += (int) score * 1.5;
			else if ((System.nanoTime() - timer) / 1000000000 < 70)
				score += (int) score * 1.2;

			gsm.save(score);
			gsm.setPaused(false);
			gsm.setState(GameStateManager.WINSTATE);
		}

		bgMusic.resume();

		// update player
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());

		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());

		// attack enemies
		player.checkAttack(enemies);

		// update all enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
				score += 55;
			}
		}

		// update explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}

	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);

		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		// draw explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition((int) tileMap.getx(),
					(int) tileMap.gety());
			explosions.get(i).draw(g);
		}

		// draw hud
		hud.draw(g);

		// show coordinates
		g.drawString("X: " + player.getx(), 230, 15);
		g.drawString("Y: " + player.gety(), 230, 30);

		// amount of enemies
		g.drawString("Enemies: " + enemies.size(), 230, 45);
		// time

		g.drawString("Time: " + (System.nanoTime() - timer) / 1000000000, 230,
				60);
		// score
		g.drawString("Score: " + score, 5, 15);
		if ((System.nanoTime() - timer) / 1000000000 < 40) {
			g.drawString("Time Bonus: X2", 5, 30);
		} else if ((System.nanoTime() - timer) / 1000000000 < 60) {
			g.drawString("Time Bonus: X1.5", 5, 30);
		} else if ((System.nanoTime() - timer) / 1000000000 < 70) {
			g.drawString("Time Bonus: X1.2", 5, 30);
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (k == KeyEvent.VK_UP)
			player.setUp(true);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(true);
		if (k == KeyEvent.VK_SPACE)
			player.setJumping(true);
		if (k == KeyEvent.VK_W)
			player.setGliding(true);
		if (k == KeyEvent.VK_R)
			player.setScratching();
		if (k == KeyEvent.VK_F)
			player.setFiring();
		if (k == KeyEvent.VK_ESCAPE) {
			bgMusic.pause();
			gsm.setPaused(true);
		}

	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(false);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(false);
		if (k == KeyEvent.VK_UP)
			player.setUp(false);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(false);
		if (k == KeyEvent.VK_SPACE)
			player.setJumping(false);
		if (k == KeyEvent.VK_W)
			player.setGliding(false);
	}

}
