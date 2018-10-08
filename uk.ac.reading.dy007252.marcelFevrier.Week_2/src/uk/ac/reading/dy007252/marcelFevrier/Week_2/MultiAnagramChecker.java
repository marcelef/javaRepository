package uk.ac.reading.dy007252.marcelFevrier.Week_2;

public class MultiAnagramChecker extends AnagramCheck {

	public MultiAnagramChecker(String input) {

		super(input);

		this.strings = input.split(" ");
		
		findAnagrams();

	}

	private void findAnagrams() {
		for (int a = 0; a < strings.length; a++) {
			for (int b = 0; b < strings.length; b++) {
				if (a != b) {
					this.report(areAnagrams(strings[a], strings[b]), strings[a], strings[b]);
				}
			}
		}
	}

}
