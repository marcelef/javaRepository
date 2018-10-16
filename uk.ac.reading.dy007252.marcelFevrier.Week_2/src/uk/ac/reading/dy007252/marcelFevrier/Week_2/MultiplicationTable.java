package uk.ac.reading.dy007252.marcelFevrier.Week_2;

/*
 * 
 * class to create the multiplication table
 * @author dy007252
 * 
 */

public class MultiplicationTable {
	
	private int maxNum;
	private int[][] TableData;
	
	/*
	 * 
	 * Constructor for the class with a default maxNum value of 10
	 * 
	 */
	
	MultiplicationTable() {
		this.maxNum = 10;
		TableData = new int[this.maxNum][this.maxNum];
		MakeTable();
	}
	
	/*
	 * 
	 * Constructor with option to set the maxNum value and therefore set the range of the multiplication table
	 * 
	 */
	
	MultiplicationTable(int maxNum){
		this.maxNum = maxNum;
		TableData = new int[maxNum][maxNum];
		MakeTable();
	}
	
	/*
	 * 
	 * MakeTable() fills the table with the correct value which would be column * row
	 * 
	 */

	private void MakeTable() {
		for (int a = 0; a < this.maxNum; a++) {
			for (int b = 0; b < this.maxNum; b++) {
				TableData[a][b] = (a+1) * (b+1);
			}
		}
	}
	
	/*
	 * 
	 * toString() uses the TableData to create one large string respresentation of the multipliacation table
	 * 
	 */
	
	public String toString() {
		String res = "Marcel's Multiplication Table\n";
		for (int a = 0; a < this.maxNum; a++) { // for every table column
			for (int b = 0; b < this.maxNum; b++) { // for every table row
				res += TableData[a][b] + "\t"; // append the contents of that [column][row] to the string then add a tab "\t"
			}
			res += "\n"; // after each row, add a new line to start a new row
		}
		return res;
	}
	
	/*
	 * Original code below...
	 * 
	 * displayTable simply displays the contents of the table in the correct format without adding the contents to a string
	 * 
	 */
	
	void displayTable() {
		for (int a = 0; a < this.maxNum; a++) {
			for (int b = 0; b < this.maxNum; b++) {
				System.out.print(TableData[a][b] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * 
	 * The code below displays the multiplication table without needing any global variables to store the table or maxNum
	 * 
	 * multiplication table consists of a * b where a = 1 to 10 and b = 1 to 10
	 */
	void display() {
		for (int a = 1; a <= 10; a++) {
			for (int b = 1; b <= 10; b++) {
				System.out.print(multiply(a,b));
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	private int multiply(int a, int b) {
		return a * b;
	}
}