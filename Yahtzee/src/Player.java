import java.util.Scanner;

public class Player {
	//fields
	private String playerName;
	private ScoreCard score;
	private Scanner scnr = new Scanner(System.in);
	
	//constructor
	Player(String playerName, ScoreCard score) {
		this.playerName = playerName;
		this.score = score;
	}
	public void endgame() {
		scnr.nextLine();
		score.scoreFinal();
		displayScorecard();
		if (score.getBonusBool()) {
			System.out.println("Wow! You even got the Upper Section bonus!");
			System.out.println("Your total score was " + (score.getUpperTotalInt() + 35 + score.getLowerTotalInt()));
			score.setEndScore(score.getUpperTotalInt() + 35 + score.getLowerTotalInt());
		} else {
			System.out.println("Your total score was " + (score.getUpperTotalInt() + score.getLowerTotalInt()));
			score.setEndScore(score.getUpperTotalInt() + score.getLowerTotalInt());
		}
	}
	public void displayScorecard() {
		System.out.println(playerName + "'S SCORECARD:");
		System.out.println("UPPER SECTION:		      CODE|SCORE");
		System.out.println("Aces				 A|" + score.getAces());
		System.out.println("Twos				 2|" + score.getTwos());
		System.out.println("Threes				 3|" + score.getThrees());
		System.out.println("Fours				 4|" + score.getFours());
		System.out.println("Fives				 5|" + score.getFives());
		System.out.println("Sixes				 6|" + score.getSixes());
		System.out.println("==================================|" + score.getUpperTotal() + score.getBonus());
		System.out.println("LOWER SECTION:		      CODE|SCORE");
		System.out.println("3 of a Kind			3K|" + score.getThreeOfAKind());
		System.out.println("4 of a Kind			4K|" + score.getFourOfAKind());
		System.out.println("Full House			FH|" + score.getFullHouse());
		System.out.println("Small Straight			SS|" + score.getSmallStraight());
		System.out.println("Large Straight			LS|" + score.getLargeStraight());
		System.out.println("Yahtzee				 Y|" + score.getYahtzee());
		System.out.println("Chance				 C|" + score.getChance());
		System.out.println("Yahtzee BONUS			  |" + score.getYahtzeeBonusCount() + "*100");
		System.out.println("==================================|" + score.getLowerTotal());
	}
	
	
	public String getPlayerName() {
		return playerName;
	}
	public ScoreCard getScore() {
		return score;
	}
}
