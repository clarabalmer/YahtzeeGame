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
		enterContinue();
		
		ScoreCard score = new ScoreCard();
		//turn level
		for (int i = 1; i <= 13; i++) {
			turn(scnr, score, i);
		}
		endgame(score);
		System.out.println("I hope you'll play again when I make it two player!");
		
		scnr.close();
	}
//	public static void playerMenu(Scanner scnr) {
//		System.out.print("How many players? ");
//		int players = scnr.nextInt();
//		
//	}
	
	public static void turn(Scanner scnr, ScoreCard score, int roundNumber) {
		System.out.println("TURN NUMBER " + roundNumber);
		DiceCup roll = new DiceCup();
		boolean noYahtzee = true;
		
		for (int i = 0; i < 3; i++) {
			roll.displayDice();
			if (roll.yahtzeeCheck()) {
				noYahtzee = false;
				yahtzeeSpecial(scnr, roll, score);
				break;
			}
			if (i == 2) {
				break;
			}
			System.out.println("Dice to re-roll: ");
			reRoll(scnr, roll);
		}
		if (noYahtzee) {
			score.displayScorecard();
			System.out.print("\nHow would you like to score this roll? ");
			scoreRoll(scnr, roll, score);
			System.out.println("Thanks! Score recorded:)\n");
		}
	}
	public static void yahtzeeSpecial(Scanner scnr, DiceCup roll, ScoreCard score) {
		System.out.println("\n\n~ ~ ~ ~ ~ ~ YAHTZEE! ~ ~ ~ ~ ~ ~\n\n");
		enterContinue();
		if (score.getStillToScore().contains("y")) {
			System.out.println("We'll go ahead and score that for you!");
			enterContinue();
			score.setYahtzee(score.scoreYahtzee(roll.diceArray()));;
			score.removeFromStillToScore("y");
			score.displayScorecard();
			enterContinue();
		} else if (score.getYahtzeeInt() == 50) {
			System.out.println("And you've already had " + (score.getYahtzeeBonusCount() + 1) + "!");
			enterContinue();
			System.out.println("We'll add one to your yahtzee bonuses.");
			score.incrementYahtzeeBonusCount();
			score.displayScorecard();
			System.out.print("\nHow would you like to score this roll? ");
			scoreRoll(scnr, roll, score);
			System.out.println("Thanks! Score recorded:)\n");
		} else {
			System.out.println("Dang! You already took a zero in Yahtzee.");
			System.out.println("How would you like to score this roll? ");
			scoreRoll(scnr, roll, score);
			System.out.println("Thanks! Score recorded:)\n");
		}
	}
	public static void scoreRoll(Scanner scnr, DiceCup roll, ScoreCard score) {
		String userChoice = scnr.nextLine().toLowerCase();
		if (score.getValidCategories().contains(userChoice)) {
			if (score.getStillToScore().contains(userChoice)) {
				switch (userChoice) {
				case "a":
					score.setAces(score.scoreAces(roll.diceArray()));
					break;
				case "2":
					score.setTwos(score.scoreTwos(roll.diceArray()));
					break;
				case "3":
					score.setThrees(score.scoreThrees(roll.diceArray()));
					break;
				case "4":
					score.setFours(score.scoreFours(roll.diceArray()));
					break;
				case "5":
					score.setFives(score.scoreFives(roll.diceArray()));
					break;
				case "6":
					score.setSixes(score.scoreSixes(roll.diceArray()));
					break;
				case "3k":
					score.setThreeOfAKind(score.scoreThreeOfAKind(roll.diceArray()));
					break;
				case "4k":
					score.setFourOfAKind(score.scoreFourOfAKind(roll.diceArray()));
					break;
				case "fh":
					score.setFullHouse(score.scoreFullHouse(roll.diceArray()));
					break;
				case "ss":
					score.setSmallStraight(score.scoreSmallStraight(roll.diceArray()));
					break;
				case "ls":
					score.setLargeStraight(score.scoreLargeStraight(roll.diceArray()));
					break;
				case "y":
					score.setYahtzee(score.scoreYahtzee(roll.diceArray()));
					break;
				case "c":
					score.setChance(score.scoreChance(roll.diceArray()));
					break;
				default:
					break;
				}
				score.removeFromStillToScore(userChoice);
			} else {
				System.out.print("You already scored there! How would you like to score this roll? ");
				scoreRoll(scnr, roll, score);
			}
		} else {
			System.out.print("Invalid input. How would you like to score this roll? ");
			scoreRoll(scnr, roll, score);
		}
	}
	
	public static void reRoll(Scanner scnr, DiceCup roll) {
		Random rand = new Random();
		String userReroll = scnr.nextLine().toLowerCase();
		if (userReroll.contains("a")) {
			roll.setDieOne(1 + Math.abs(rand.nextInt() % 6));
		}
		if (userReroll.contains("b")) {
			roll.setDieTwo(1 + Math.abs(rand.nextInt() % 6));
		}
		if (userReroll.contains("c")) {
			roll.setDieThree(1 + Math.abs(rand.nextInt() % 6));
		}
		if (userReroll.contains("d")) {
			roll.setDieFour(1 + Math.abs(rand.nextInt() % 6));
		}
		if (userReroll.contains("e")) {
			roll.setDieFive(1 + Math.abs(rand.nextInt() % 6));
		}
		
	}
	public static void endgame(ScoreCard score) {
		System.out.println("What a game! Lets see how you did:");
		enterContinue();
		score.scoreFinal();
		score.displayScorecard();
		if (score.getBonusBool()) {
			System.out.println("Wow! You even got the Upper Section bonus!");
			System.out.println("Your total score was " + (score.getUpperTotalInt() + 35 + score.getLowerTotalInt()));
		} else {
			System.out.println("Your total score was " + (score.getUpperTotalInt() + score.getLowerTotalInt()));
		}
		
	}
	
	public static void welcome() {
		System.out.println("Hello! Welcome to (currently one-player) Yahtzee.");
		System.out.println("Here's how to play:");
	}
	
	public static void displayRules() {
		System.out.println("\nFor thirteen turns, you roll five dice and score them.");
		System.out.println("Each turn gets two rerolls, where you may select any dice to roll again.");
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
		System.out.println("Press enter to begin!");
	}

	public static void enterContinue() {
		try {
			System.in.read();
		} catch(Exception e) {}
	}
}
