import java.util.Random;

public class DiceCup {
	//instance variables
	private int dieOne;
	private int dieTwo;
	private int dieThree;
	private int dieFour;
	private int dieFive;
	
	//Constructor
	DiceCup() {
		Random rand = new Random();
		setDieOne(1 + Math.abs(rand.nextInt() % 6));
		setDieTwo(1 + Math.abs(rand.nextInt() % 6));
		setDieThree(1 + Math.abs(rand.nextInt() % 6));
		setDieFour(1 + Math.abs(rand.nextInt() % 6));
		setDieFive(1 + Math.abs(rand.nextInt() % 6));
	}
	
	//methods
	public int[] diceArray() {
		int[] diceList = {getDieOne(), getDieTwo(), getDieThree(), getDieFour(), getDieFive()};
		return diceList;
	}
	public boolean yahtzeeCheck() {
		return (getDieOne() == getDieTwo() && getDieOne() == getDieThree() && getDieOne() == getDieFour()
				&& getDieOne() == getDieFive());
	}
	@Override
	public String toString() {
		return "Die One: " + getDieOne() + " Die Two: " + getDieTwo() + " Die Three: " + getDieThree() + " Die Four: " + getDieFour() + " Die Five: " + getDieFive();
	}
	public void displayDice() {
		System.out.println("    A         B         C         D         E");
		System.out.println(" _______/  _______/  _______/  _______/  _______/ ");
		System.out.println(rowOne(getDieOne()) + rowOne(getDieTwo()) + rowOne(getDieThree()) + rowOne(getDieFour()) + rowOne(getDieFive()));
		System.out.println(rowTwo(getDieOne()) + rowTwo(getDieTwo()) + rowTwo(getDieThree()) + rowTwo(getDieFour()) + rowTwo(getDieFive()));
		System.out.println(rowThree(getDieOne()) + rowThree(getDieTwo()) + rowThree(getDieThree()) + rowThree(getDieFour()) + rowThree(getDieFive()));
		System.out.println(" =======/  =======/  =======   =======   ======= ");
	}
	
	public String rowOne(int n) {
		switch (n) {
			case 1:
				return "|       | ";
			case 2:
			case 3:
				return "| *     | ";
			case 4:
			case 5:
			case 6:
				return "| *   * | ";
			default:
				return "";
		}
	}
	public String rowTwo(int n) {
		switch (n) {
			case 1:
			case 3:
			case 5:
				return "|   *   | ";
			case 2:
			case 4:
				return "|       | ";
			case 6:
				return "| *   * | ";
			default:
				return "";
		}
	}
	public String rowThree(int n) {
		switch (n) {
		case 1:
			return "|       | ";
		case 2:
		case 3:
			return "|     * | ";
		case 4:
		case 5:
		case 6:
			return "| *   * | ";
		default:
			return "";
		}
	}
	

	
	//getters/setters
	public int getDieOne() {
		return dieOne;
	}
	public void setDieOne(int dieOne) {
		this.dieOne = dieOne;
	}
	public int getDieTwo() {
		return dieTwo;
	}
	public void setDieTwo(int dieTwo) {
		this.dieTwo = dieTwo;
	}
	public int getDieThree() {
		return dieThree;
	}
	public void setDieThree(int dieThree) {
		this.dieThree = dieThree;
	}
	public int getDieFour() {
		return dieFour;
	}
	public void setDieFour(int dieFour) {
		this.dieFour = dieFour;
	}
	public int getDieFive() {
		return dieFive;
	}
	public void setDieFive(int dieFive) {
		this.dieFive = dieFive;
	}
}
