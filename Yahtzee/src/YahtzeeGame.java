import java.util.Random;
import java.util.Scanner;

public class YahtzeeGame {
	//accidentally pressed enter after a random character and it wasted a roll--
	//make it multiplayer
	//play again option
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		welcome();
		displayRules();
		Game game = gameStart(scnr);
		
		for (int i = 1; i <= 13; i++) {
			System.out.println("~ ~ ~ ~ ~ ~ ~ ROUND " + i + " ~ ~ ~ ~ ~ ~ ~");
			for (int j = 0; j < game.getNumOfPlayers(); j++) {
				Turn turn = new Turn(game.getPlayers().get(j), new DiceCup(), scnr);
				turn.turn();
			}
		}
		System.out.println("What a game!");
		for (int i = 0; i < game.getNumOfPlayers(); i++) {
			System.out.println("\nLet's see how " + game.getPlayers().get(i).getPlayerName() + " did: ");
			game.getPlayers().get(i).endgame();
		}
		if (game.getNumOfPlayers() > 1) {
			game.displayRanking();
		}
		
		scnr.close();
	}
	public static Game gameStart(Scanner scnr) {
		System.out.print("\nHow many players are there? ");
		String userResponse = scnr.nextLine();
		try {
			int numOfPlayers = Integer.parseInt(userResponse);
			if (numOfPlayers < 1) {
				System.out.println("Please enter a number greater than zero.");
				return gameStart(scnr);
			}
			Game game = new Game(numOfPlayers, scnr);
			return game;
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number.");
			return gameStart(scnr);
		}
	}
	
	public static void welcome() {
		System.out.println("Hello! Welcome to Yahtzee.");
		System.out.println("Here's how to play:");
	}
	
	public static void displayRules() {
		System.out.println("\nFor thirteen turns, each player rolls five dice and scores them.");
		System.out.println("Each turn gets two rerolls, where you may select 0-5 dice to roll again.");
		System.out.println("After each turn, choose the category in which to score the dice.");
		System.out.println("\nCategories are as follows:");
		System.out.println("\nUpper Section:");
		System.out.println("Aces: sum of dice with the number 1");
		System.out.println("Twos: sum of dice with the number 2");
		System.out.println("Threes: sum of dice with the number 3");
		System.out.println("Fours: sum of dice with the number 4");
		System.out.println("Fives: sum of dice with the number 5");
		System.out.println("Sixes: sum of dice with the number 6");
		System.out.println("\nLower Section:");
		System.out.println("Three of a Kind: sum of all dice");
		System.out.println("Four of a Kind: sum of all dice");
		System.out.println("Full House (3 of one number, 2 of another): 25");
		System.out.println("Small Straight (four sequential numbers): 30");
		System.out.println("Large Straight (five sequential numbers): 40");
		System.out.println("Yahtzee (6 of a kind): 50");
		System.out.println("Chance: sum of all dice");
		System.out.println("\nIf you've already scored a yahtzee as 50 and you roll another, ");
		System.out.println("you get a 100 point yahtzee bonus, and still score the yahtzee in a remaining category.");
		System.out.println("At the end, if the upper section scored 63 or more, a bonus of 35 is added");
	}
}
