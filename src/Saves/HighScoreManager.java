package Saves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HighScoreManager {
	
	private String path;
	private ArrayList<String> players;
	
	public HighScoreManager() {
		
		path = "SaveGames/highScores.txt";
		
	}

	public void write(String s) {
		
		File file = new File(path);
		
		try{
			if (!file.exists()) {
				file.createNewFile();
			}
			
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());
			
			try{
				
				out.print(s);
			} finally {
				
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void exists()  {
		
		File file = new File(path);
		if (!file.exists()){
			try{
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("fileNotFound");
			//throw new FileNotFoundException(file.getName());
		}
		
	}
	
	public String read() {
		
		StringBuilder sb = new StringBuilder();
		File file  = new File(path);
		exists();
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try{
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			}finally {
				in.close();
			}
		}
		catch (IOException e) {
			
			throw new RuntimeException(e);
		}
		return sb.toString();
		
	}
	
public ArrayList<String> getPlayers() {
		
		players = new ArrayList<String>();
		String []names;
		String sortStringTemp;
		
		int sortIntTemp;
		int []results;
		
		File file  = new File(path);
		
		
		try{
			exists();
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try{
				String s;
				while ((s = in.readLine()) != null) {
					players.add(s);
				}
			}finally {
				in.close();
			}
		}
		catch (IOException e) {
			
			throw new RuntimeException(e);
		}
		
		names = new String[players.size()];
		results = new int [players.size()];
		
		//fill the additional arrays
		for (int i = 0; i< players.size(); i++){	
			StringBuffer s = new StringBuffer();
			s.append(players.get(i));
			names[i] = s.substring(0, s.indexOf(" "));
			results[i] = Integer.parseInt((s.substring(s.indexOf(" ")+1).toString()));
		}
		
		//sort the high scores
		for (int i = 0; i< players.size()-1; i++) {
			for (int j = 0; j< players.size()-1; j++) {
				if (results [j]< results[j+1]) {
					
					sortIntTemp = results[j];
					results[j] = results[j+1];
					results[j+1] = sortIntTemp;
					
					sortStringTemp = names[j];
					names[j] = names[j+1];
					names[j+1] = sortStringTemp;
				}
			}
		}
		
		players.clear();
		
		//fill the ArreyList with the sorted results
		for (int i = 0; i< names.length; i++) {
			players.add(names[i] + " " + results[i]);
		}
		return players;
		
	}
	
	public void add(String player, int result) {
		
		int subStart; 	//replacing result start position
		int subEnd;		//replacing result end position
		
		//exists();
		StringBuffer sb = new StringBuffer();
		String oldFile  = read();		
		sb.append(oldFile);
		
		if (sb.indexOf(player)<0) {
			sb.append(player + " " + result + "\n");
		}
		else {
			subStart = sb.indexOf(" ",sb.indexOf(player)+1)+1;
			subEnd = sb.indexOf("\n", subStart);
			if (Integer.parseInt((sb.substring(subStart, subEnd).toString())) < result)
				sb.replace(subStart, subEnd, Integer.toString(result));
		}
		write(sb.toString());
	}
	

}
