package uk.ac.reading.dy007252.marcelFevrier.Week_2;

import javax.swing.JOptionPane;

public class SmallestDifference {

	private int[] numbers;			// array of integers to analyse
	// you may want to add more private variables
	
	/**
	 * create class : 
	 * @param instr	- string with series of numbers separated by space
	 */
	SmallestDifference (String instr) {
		StringSplitter S = new StringSplitter (instr, " ");
		numbers = S.getIntegers();
	}
	/**
	 * method to search through array and find pairs of adjacent numbers
	 * which are closest in value; note the difference and where in array
	 */
	public void findSmallest() {
		int shortest, number1, number2;
		
		shortest = diff(numbers[0], numbers[1]);
		
		for (int num1 = 0; num1 < numbers.length; num1++) {
			
		}
    }
	
	private int diff(int a, int b) {
		
		int res = a - b;
		
		if (res < 0){
			return res * (-1);
		} else {
			return res;
		}
	}
	
	/**
	 * return as string the result of analysis
	 */
	public String toString() {
		return "Smallest difference is " ;	// you extend this string
	}
}
