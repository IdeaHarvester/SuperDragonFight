package Main;

import java.io.*;
import javax.swing.JFrame;

public class Game {
	
	public static void main (String[]args) throws FileNotFoundException{
		JFrame window = new JFrame ("Super Dragon Fight");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
