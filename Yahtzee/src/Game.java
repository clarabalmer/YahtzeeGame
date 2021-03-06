import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private int numOfPlayers;
	private Scanner scnr;
	private ArrayList<Player> players = new ArrayList<>();
	
	Game(int numOfPlayers, Scanner scnr) {
		this.numOfPlayers = numOfPlayers;
		this.scnr = scnr;
		for (int i = 0; i < numOfPlayers; i++) {
			System.out.print("What is player " + (i+1) + "'s name? ");
			String playerName = scnr.nextLine().toUpperCase();
			
			players.add(new Player(playerName, new ScoreCard()));
		}
		System.out.println("Okay! Ready to play?");
		scnr.nextLine();
	}
	public void displayRanking() {
		
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
