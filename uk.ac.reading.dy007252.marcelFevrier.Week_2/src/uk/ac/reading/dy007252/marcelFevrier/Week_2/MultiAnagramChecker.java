package uk.ac.reading.dy007252.marcelFevrier.Week_2;

public class MultiAnagramChecker extends AnagramCheck {
	
	public AnagramCheck[] anagrams;

	public MultiAnagramChecker(String input) {

		super(input);

		this.strings = input.split(" ");
		
		anagrams = new AnagramCheck[this.strings.length * (this.strings.length -1)];
		
		createAnagramObjects();
		
		reportAnagrams();

	}
	
	private void createAnagramObjects() {
		int arrCnt = 0;
		for (int cnt = 0; cnt < this.strings.length; cnt++) {
			for (int cnt2 = 0; cnt2 < this.strings.length; cnt2++) {
				if (cnt != cnt2) {
					System.out.println(strings[cnt] + ";" + strings[cnt2]);
					AnagramCheck anAnagram = new AnagramCheck(strings[cnt] + ";" + strings[cnt2]);
					anAnagram.run();
					anagrams[arrCnt] = anAnagram;
					arrCnt++;
				}
			}
		}
	}

	private void reportAnagrams() {
		this.report(anagrams);
	}

}
