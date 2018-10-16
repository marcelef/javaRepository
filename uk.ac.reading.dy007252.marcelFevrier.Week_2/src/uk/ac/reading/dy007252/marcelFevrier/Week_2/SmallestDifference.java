package uk.ac.reading.dy007252.marcelFevrier.Week_2;

import javax.swing.JOptionPane;

public class SmallestDifference {

	private int smallest;
	private int[] numbers; // array of integers to analyse
	// you may want to add more private variables

	/**
	 * create class :
	 * 
	 * @param instr
	 *            - string with series of numbers separated by space
	 */
	SmallestDifference(String instr) {
		StringSplitter S = new StringSplitter(instr, " ");
		numbers = S.getIntegers();
	}

	/**
	 * method to search through array and find pairs of adjacent numbers which are
	 * closest in value; note the difference and where in array
	 */
	public void findSmallest() {
		int shortest, number1, number2, index1, index2;

		shortest = diff(numbers[0], numbers[1]);

		number1 = numbers[0];
		number2 = numbers[1];
		index1 = 1;
		index2 = 2;

		for (int num = 1; num < numbers.length - 1; num++) {
			int difference = diff(numbers[num], numbers[num + 1]);

			if (difference < shortest) {
				shortest = difference;
				number1 = numbers[num];
				number2 = numbers[num + 1];
				index1 = num + 1;
				index2 = num + 2;
			}
		}

		JOptionPane.showMessageDialog(null, "Smalles difference numbers are " + number1 + " and " + number2 + " with indexes " + index1
				+ " and " + index2 + ".");
		this.smallest = shortest;

	}

	private int diff(int a, int b) {

		int res = a - b;

		if (res < 0) {
			return res * (-1);
		} else {
			return res;
		}
	}

	/**
	 * return as string the result of analysis
	 */
	public String toString() {
		return "Smallest difference is " + this.smallest + "."; // you extend this string
	}
}
