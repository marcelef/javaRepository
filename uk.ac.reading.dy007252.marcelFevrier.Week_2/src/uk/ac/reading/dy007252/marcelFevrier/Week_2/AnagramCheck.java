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

		this.report(isAnagram, strings[0], strings[1]);
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

	public void report(boolean areAnagramsBool, String str1, String str2) {
		String not = "";
		not = (areAnagramsBool) ? "" : "NOT ";

		JOptionPane.showMessageDialog(null, str1 + " and " + str2 + " are " + not + "anagrams.");
	}
}
