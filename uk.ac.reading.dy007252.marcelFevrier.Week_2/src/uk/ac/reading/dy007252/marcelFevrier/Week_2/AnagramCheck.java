package uk.ac.reading.dy007252.marcelFevrier.Week_2;

import javax.swing.JOptionPane;

public class AnagramCheck {

	String[] strings;
	boolean isAnagram;

	public AnagramCheck(String input) {
		strings = new String[2];

		strings = input.split(";");

		IsAnagram();
	}

	private void IsAnagram() {
		/*
		 * String tempStr1 = strings[0]; String tempStr2 = strings[1];
		 */
		StringBuilder tempStr1 = new StringBuilder(removeSpaces(strings[0]));
		StringBuilder tempStr2 = new StringBuilder(removeSpaces(strings[1]));

		if (tempStr1.length() != tempStr2.length()) {
			isAnagram = false;
		} else {
			boolean change = true;
			
			while (tempStr1.length() > 0 && change) {
				change = false;
				for (int cnt = 0; cnt < tempStr2.length(); cnt++) {
					if (Character.toUpperCase(tempStr1.charAt(0)) == Character.toUpperCase(tempStr2.charAt(cnt))) {
						tempStr1 = tempStr1.deleteCharAt(0);
						tempStr2 = tempStr2.deleteCharAt(cnt);
						change = true;
						break;
					}
				}
			}
			if (tempStr1.length() > 0) {
				isAnagram = false;
			} else if (tempStr1.length() == 0) {
				isAnagram = true;
			}
		}
	}
	
	private String removeSpaces(String str) {
		String newStr = "";
		for (int cnt = 0; cnt < str.length(); cnt++) {
			if (str.charAt(cnt) != ' ') {
				newStr += str.charAt(cnt);
			}
		}
		return newStr;
	}

	public void Report() {
		if (isAnagram) {
			JOptionPane.showMessageDialog(null, strings[0] + " and " + strings[1] + " are anagrams.");
		} else {
			JOptionPane.showMessageDialog(null, strings[0] + " and " + strings[1] + " are NOT anagrams.");
		}
	}
}
