import java.util.Random;
import java.util.Scanner;

public class Turn {
	private DiceCup roll;
	private Player player;
	private Scanner scnr;
	
	Turn(Player player, DiceCup roll, Scanner scnr) {
		this.roll = roll;
		this.player = player;
		this.scnr = scnr;
		
	}
	
	public void turn() {
		System.out.println("~ ~ ~ ~ ~ ~ " + player.getPlayerName() + "'S TURN ~ ~ ~ ~ ~ ~");
		boolean noYahtzee = true;
		
		for (int i = 0; i < 3; i++) {
			roll.displayDice();
			if (roll.yahtzeeCheck()) {
				noYahtzee = false;
				System.out.println("\n\n~ ~ ~ ~ ~ ~ YAHTZEE! ~ ~ ~ ~ ~ ~\n\n");
				scnr.nextLine();
				if (player.getScore().getStillToScore().contains("y")) {
					System.out.println("We'll go ahead and score that for you!");
					scnr.nextLine();
					player.getScore().setYahtzee(player.getScore().scoreYahtzee(roll.diceArray()));;
					player.getScore().removeFromStillToScore("y");
					player.displayScorecard();
					scnr.nextLine();
				} else if (player.getScore().getYahtzeeInt() == 50) {
					System.out.println("And you've already had " + (player.getScore().getYahtzeeBonusCount() + 1) + "!");
					scnr.nextLine();
					System.out.println("We'll add one to your yahtzee bonuses.");
					player.getScore().incrementYahtzeeBonusCount();
					player.displayScorecard();
					System.out.print("\nHow would you like to score this roll? ");
					scoreRoll();
					System.out.println("Thanks! Score recorded:)\n");
				} else {
					System.out.println("Dang! You already took a zero in Yahtzee.");
					System.out.println("How would you like to score this roll? ");
					scoreRoll();
					System.out.println("Thanks! Score recorded:)\n");
				}
				break;
			}
			if (i == 2) {
				break;
			}
			reRoll();
		}
		if (noYahtzee) {
			player.displayScorecard();
			System.out.print("\nHow would you like to score this roll? ");
			scoreRoll();
			System.out.println("Thanks! Score recorded:)\n");
		}
	}
	public void reRoll() {
		Random rand = new Random();
		System.out.println("Choose dice to re-roll! (other characters are ignored, press enter to keep): ");
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
	public void scoreRoll() {
		String userChoice = scnr.nextLine().toLowerCase();
		if (player.getScore().getValidCategories().contains(userChoice)) {
			if (player.getScore().getStillToScore().contains(userChoice)) {
				switch (userChoice) {
				case "a":
					player.getScore().setAces(player.getScore().scoreAces(roll.diceArray()));
					break;
				case "2":
					player.getScore().setTwos(player.getScore().scoreTwos(roll.diceArray()));
					break;
				case "3":
					player.getScore().setThrees(player.getScore().scoreThrees(roll.diceArray()));
					break;
				case "4":
					player.getScore().setFours(player.getScore().scoreFours(roll.diceArray()));
					break;
				case "5":
					player.getScore().setFives(player.getScore().scoreFives(roll.diceArray()));
					break;
				case "6":
					player.getScore().setSixes(player.getScore().scoreSixes(roll.diceArray()));
					break;
				case "3k":
					player.getScore().setThreeOfAKind(player.getScore().scoreThreeOfAKind(roll.diceArray()));
					break;
				case "4k":
					player.getScore().setFourOfAKind(player.getScore().scoreFourOfAKind(roll.diceArray()));
					break;
				case "fh":
					player.getScore().setFullHouse(player.getScore().scoreFullHouse(roll.diceArray()));
					break;
				case "ss":
					player.getScore().setSmallStraight(player.getScore().scoreSmallStraight(roll.diceArray()));
					break;
				case "ls":
					player.getScore().setLargeStraight(player.getScore().scoreLargeStraight(roll.diceArray()));
					break;
				case "y":
					player.getScore().setYahtzee(player.getScore().scoreYahtzee(roll.diceArray()));
					break;
				case "c":
					player.getScore().setChance(player.getScore().scoreChance(roll.diceArray()));
					break;
				default:
					break;
				}
				player.getScore().removeFromStillToScore(userChoice);
			} else {
				System.out.print("You already scored there! How would you like to score this roll? ");
				scoreRoll();
			}
		} else {
			System.out.print("Invalid input. How would you like to score this roll? ");
			scoreRoll();
		}
	}
}
