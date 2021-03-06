import java.util.ArrayList;
import java.util.Arrays;

public class ScoreCard {
	//NOTE:
	//Two types of getters, one to get string, one to get int
	//setters take int parameter and set as string
	private String aces = "";
	private String twos = "";
	private String threes = "";
	private String fours = "";
	private String fives = "";
	private String sixes = "";
	private String upperTotal = "";
	private String bonus = "";
	private boolean bonusBool;
	private String threeOfAKind = "";
	private String fourOfAKind = "";
	private String fullHouse = "";
	private String smallStraight = "";
	private String largeStraight = "";
	private String yahtzee = "";
	private int yahtzeeBonusCount = 0;
	private String chance = "";
	private String lowerTotal = "";
	private int endScore = 0;
	private ArrayList<String> validCategories = new ArrayList<String>(Arrays.asList("a", "2", "3", "4", "5", "6", "3k", "4k", "fh", "ss", "ls", "y", "c"));
	private ArrayList<String> stillToScore = new ArrayList<String>(Arrays.asList("a", "2", "3", "4", "5", "6", "3k", "4k", "fh", "ss", "ls", "y", "c"));
	
	//constructors
	ScoreCard() {}
	
	//methods
	@Override
	public String toString() {
		return "Aces: " + getAcesInt() + " Twos: " + getTwosInt() + " Threes: " + getThreesInt() + " Fours: " + getFoursInt() + " Fives: " + getFivesInt() + 
				" Sixes: " + getSixesInt() + " Three of a kind: " + getThreeOfAKindInt() + " Four of a kind:" + getFourOfAKindInt() + 
				" Full House: " + getFullHouseInt() + " Small straight: " + getSmallStraightInt() + " Large straight: " + getLargeStraightInt() + 
				" Yahtzee: " + getYahtzeeInt() + " Chance: " + getChanceInt() + "Yahtzee bonuses: " + yahtzeeBonusCount;
	}
	
	public void scoreFinal() {
		int upperSum = getAcesInt() + getTwosInt() + getThreesInt() + getFoursInt() + getFivesInt() + getSixesInt();
		upperTotal = Integer.toString(upperSum);
		if (upperSum >= 63) {
			bonus = " + 35 bonus!";
			bonusBool = true;
		}
		int lowerSum = getThreeOfAKindInt() + getFourOfAKindInt() + getFullHouseInt() + getSmallStraightInt() + getLargeStraightInt() + getYahtzeeInt() + getChanceInt() + (100 * yahtzeeBonusCount);
		lowerTotal = Integer.toString(lowerSum);
	}
	public int scoreAces(int[] diceList) {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 1) {
				result++;
			}
		}
		return result;
	}
	public int scoreTwos(int[] diceList) {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 2) {
				result += 2;
			}
		}
		return result;
	}
	public int scoreThrees(int[] diceList) {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 3) {
				result += 3;
			}
		}
		return result;
	}
	public int scoreFours(int[] diceList) {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 4) {
				result += 4;
			}
		}
		return result;
	}
	public int scoreFives(int[] diceList) {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 5) {
				result += 5;
			}
		}
		return result;
	}
	public int scoreSixes(int[] diceList) {
		int result = 0;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 6) {
				result += 6;
			}
		}
		return result;
	}
	public int scoreThreeOfAKind(int[] diceList) {
		//add yahtzee bonus if statements
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += diceList[i];
		}
		int count = 1;
		for (int i = 1; i < 5; i++) {
			if (diceList[0] == diceList[i]) {
				count++;
			}
			if (count >= 3) {
				return sum;
			}
		}
		count = 1;
		for (int i = 2; i < 5; i++) {
			if (diceList[1] == diceList[i]) {
				count++;
			}
			if (count >= 3) {
				return sum;
			}
		}
		count = 1;
		for (int i = 3; i < 5; i++) {
			if (diceList[2] == diceList[i]) {
				count++;
			}
			if (count >= 3) {
				return sum;
			}
		}
		return 0;
	}
	public int scoreFourOfAKind(int[] diceList) {
		//add yahtzee bonus if statement
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += diceList[i];
		}
		int count = 1;
		for (int i = 1; i < 5; i++) {
			if (diceList[0] == diceList[i]) {
				count++;
			}
			if (count >= 4) {
				return sum;
			}
		}
		count = 1;
		for (int i = 2; i < 5; i++) {
			if (diceList[1] == diceList[i]) {
				count++;
			}
			if (count >= 4) {
				return sum;
			}
		}
		return 0;
	}
	public int scoreFullHouse(int[] diceList) {
		int a = diceList[0];
		int b = 0;
		int aCount = 1;
		int bCount = 0;
		for (int i = 1; i < 5; i++) {
			if (diceList[i] == a) {
				aCount++;
			} else {
				b = diceList[i];
			}
		}
		for (int i = 1; i < 5; i++) {
			if (diceList[i] == b) {
				bCount++;
			}
		}
		if (aCount + bCount == 5 && bCount != 0) {
			return 25;
		} else {
			return 0;
		}
	}
	public int scoreSmallStraight(int[] diceList) {
		boolean one = false;
		boolean two = false;
		boolean three = false;
		boolean four = false;
		boolean five = false;
		boolean six = false;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 1) {
				one = true;
			}
			if (diceList[i] == 2) {
				two = true;
			}
			if (diceList[i] == 3) {
				three = true;
			}
			if (diceList[i] == 4) {
				four = true;
			}
			if (diceList[i] == 5) {
				five = true;
			}
			if (diceList[i] == 6) {
				six = true;
			}
		}
		if (three && four) {
			if (one && two || two && five || five && six) {
				return 30;
			}
		}
		return 0;
	}
	public int scoreLargeStraight(int[] diceList) {
		boolean one = false;
		boolean two = false;
		boolean three = false;
		boolean four = false;
		boolean five = false;
		boolean six = false;
		for (int i = 0; i < 5; i++) {
			if (diceList[i] == 1) {
				one = true;
			}
			if (diceList[i] == 2) {
				two = true;
			}
			if (diceList[i] == 3) {
				three = true;
			}
			if (diceList[i] == 4) {
				four = true;
			}
			if (diceList[i] == 5) {
				five = true;
			}
			if (diceList[i] == 6) {
				six = true;
			}
		}
		if (two && three && four && five) {
			if (one || six) {
				return 40;
			}
		}
		return 0;
	}
	public int scoreYahtzee (int[] diceList) {
		int first = diceList[0];
		if (first == diceList[1] && first == diceList[2] && first == diceList[3] && first == diceList[4]) {
			return 50;
		}
		return 0;
	}
	public int scoreChance (int[] diceList) {
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += diceList[i];
		}
		return sum;
	}
	
	//getters/setters
	public String getAces() {
		return aces;
	}
	public int getAcesInt() {
		return Integer.parseInt(aces);
	}
	public void setAces(int aces) {
		this.aces = String.valueOf(aces);
	}
	public String getTwos() {
		return twos;
	}
	public int getTwosInt() {
		return Integer.parseInt(twos);
	}
	public void setTwos(int twos) {
		this.twos = String.valueOf(twos);
	}
	public String getThrees() {
		return threes;
	}
	public int getThreesInt() {
		return Integer.parseInt(threes);
	}
	public void setThrees(int threes) {
		this.threes = String.valueOf(threes);
	}
	public String getFours() {
		return fours;
	}
	public int getFoursInt() {
		return Integer.parseInt(fours);
	}
	public void setFours(int fours) {
		this.fours = String.valueOf(fours);
	}
	public String getFives() {
		return fives;
	}
	public int getFivesInt() {
		return Integer.parseInt(fives);
	}
	public void setFives(int fives) {
		this.fives = String.valueOf(fives);
	}
	public String getSixes() {
		return sixes;
	}
	public int getSixesInt() {
		return Integer.parseInt(sixes);
	}
	public void setSixes(int sixes) {
		this.sixes = String.valueOf(sixes);
	}
	public String getUpperTotal() {
		return upperTotal;
	}
	public int getUpperTotalInt() {
		return Integer.parseInt(upperTotal);
	}
	public void setUpperTotal(String upperTotal) {
		this.upperTotal = String.valueOf(upperTotal);
	}
	public String getBonus() {
		return bonus;
	}
	public boolean getBonusBool() {
		return bonusBool;
	}
	public void setBonusBool(boolean bonusBool) {
		this.bonusBool = bonusBool;
	}
	public String getThreeOfAKind() {
		return threeOfAKind;
	}
	public int getThreeOfAKindInt() {
		return Integer.parseInt(threeOfAKind);
	}
	public void setThreeOfAKind(int threeOfAKind) {
		this.threeOfAKind = String.valueOf(threeOfAKind);
	}
	public String getFourOfAKind() {
		return fourOfAKind;
	}
	public int getFourOfAKindInt() {
		return Integer.parseInt(fourOfAKind);
	}
	public void setFourOfAKind(int fourOfAKind) {
		this.fourOfAKind = String.valueOf(fourOfAKind);
	}
	public String getFullHouse() {
		return fullHouse;
	}
	public int getFullHouseInt() {
		return Integer.parseInt(fullHouse);
	}
	public void setFullHouse(int fullHouse) {
		this.fullHouse = String.valueOf(fullHouse);
	}
	public String getSmallStraight() {
		return smallStraight;
	}
	public int getSmallStraightInt() {
		return Integer.parseInt(smallStraight);
	}
	public void setSmallStraight(int smallStraight) {
		this.smallStraight = String.valueOf(smallStraight);
	}
	public String getLargeStraight() {
		return largeStraight;
	}
	public int getLargeStraightInt() {
		return Integer.parseInt(largeStraight);
	}
	public void setLargeStraight(int largeStraight) {
		this.largeStraight = String.valueOf(largeStraight);
	}
	public String getYahtzee() {
		return yahtzee;
	}
	public int getYahtzeeInt() {
		return Integer.parseInt(yahtzee);
	}
	public void setYahtzee(int yahtzee) {
		this.yahtzee = String.valueOf(yahtzee);
	}
	public int getYahtzeeBonusCount() {
		return yahtzeeBonusCount;
	}
	public void setYahtzeeBonusCount(int yahtzeeBonusCount) {
		this.yahtzeeBonusCount = yahtzeeBonusCount;
	}
	public String getChance() {
		return chance;
	}
	public int getChanceInt() {
		return Integer.parseInt(chance);
	}
	public void setChance(int chance) {
		this.chance = String.valueOf(chance);
	}
	public String getLowerTotal() {
		return lowerTotal;
	}
	public int getLowerTotalInt() {
		return Integer.parseInt(lowerTotal);
	}
	public void setLowerTotal(int lowerTotal) {
		this.lowerTotal = String.valueOf(lowerTotal);
	}
	public int getEndScore() {
		return endScore;
	}
	public void setEndScore(int endScore) {
		this.endScore = endScore;
	}
	public ArrayList<String> getValidCategories() {
		return validCategories;
	}
	public ArrayList<String> getStillToScore() {
		return stillToScore;
	}
	//special setters:
	public void removeFromStillToScore(String userChoice) {
		stillToScore.remove(userChoice);
	}
	public void incrementYahtzeeBonusCount() {
		yahtzeeBonusCount++;
	}
}
