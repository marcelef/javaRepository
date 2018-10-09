package uk.ac.reading.dy007252.marcelFevrier.Week_2;

import javax.swing.JOptionPane;

public class AnagramCheck {

	protected String[] strings;
	private boolean isAnagram;

	public AnagramCheck(String input) {
		strings = new String[2];

		strings = input.split(";");
		
	}

	public void run() {
		
		isAnagram = areAnagrams(strings[0], strings[1]);
		
	}

	protected boolean areAnagrams(String str1, String str2) {
		/*
		 * String tempStr1 = strings[0]; String tempStr2 = strings[1];
		 */
		StringBuilder tempStr1 = new StringBuilder(removeSpaces(str1));
		StringBuilder tempStr2 = new StringBuilder(removeSpaces(str2));

		if (tempStr1.length() != tempStr2.length()) {
			return false;
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
			if (tempStr1.length() == 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	protected String removeSpaces(String str) {
		String newStr = "";
		for (int cnt = 0; cnt < str.length(); cnt++) {
			if (str.charAt(cnt) != ' ') {
				newStr += str.charAt(cnt);
			}
		}
		return newStr;
	}
	
	public void report() {
		String not = "";
		not = (isAnagram) ? "" : "NOT ";
		
		JOptionPane.showMessageDialog(null, this.strings[0] + " and " + this.strings[1] + " are " + not + "anagrams");
	}

	public void report(AnagramCheck[] anagrams) {
		String not = "";
		String res = "";
		
		int counter = 0;
		
		for (AnagramCheck anAnagram : anagrams) {
			counter++;
			not = (anAnagram.isAnagram) ? "" : "NOT ";
			res += anAnagram.strings[0] + " and " + anAnagram.strings[1] + " are "  + not + "anagrams.\n";
		}
		JOptionPane.showMessageDialog(null, res);
	}
}
