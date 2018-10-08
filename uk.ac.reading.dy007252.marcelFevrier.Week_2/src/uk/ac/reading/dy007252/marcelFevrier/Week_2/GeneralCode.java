package uk.ac.reading.dy007252.marcelFevrier.Week_2;

import javax.swing.JOptionPane;

public class GeneralCode {

	static void Exercise1() {

		String sizeStr = JOptionPane.showInputDialog(null, "Enter max number");

		int size = Integer.parseInt(sizeStr);

		MultiplicationTable table = new MultiplicationTable(size);

		String multiplicationTable = table.toString();

		System.out.println(multiplicationTable);

	}

	static void Exercise2() {

		// main function to test class
		StringSplitter ME = new StringSplitter("2 5 6 9", " "); // create example
		System.out.println(ME.toString());

		String[] temp = ME.getStrings(); // get a copy of the strings

		for (int ct = 0; ct < temp.length; ct++)
			System.out.print(temp[ct] + "\t");
		System.out.println();

		temp[0] = "fred"; // change the copy of the first element

		for (int ct = 0; ct < temp.length; ct++)
			System.out.print(temp[ct] + "\t");
		System.out.println();

		System.out.println(ME.toString());

	}
	
	static void Exercise3() {
		
		String userIn = JOptionPane.showInputDialog(null, "Enter series of numbers separated by space > ");
		SmallestDifference sd = new SmallestDifference(userIn);
		sd.findSmallest();
		JOptionPane.showMessageDialog(null, sd.toString());
		
	}
	
	static void Exercise4() {
		
		String input = JOptionPane.showInputDialog(null, "Enter two strings seperated by \";\"");
		
		AnagramCheck anagram = new AnagramCheck(input);
		anagram.Report();
		
	}

	public static void main(String[] args) {

		String choiceStr = JOptionPane.showInputDialog(null,
				"Menu\n\n1)\tMultiplication Table (Ex 1)\n2)\tString Splitter (Ex 2)\n3)\tSmallest Difference (Ex 3)\n4)\tAnagram Check (Ex 4)");

		int choice = Integer.parseInt(choiceStr);

		switch (choice) {
		case 1:
			Exercise1();
			break;
		case 2:
			Exercise2();
			break;
		case 3:
			Exercise3();
		case 4:
			Exercise4();
			break;
		}

	}
}
