package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.util.ArrayList;

public class DialogCapture {
	
	private ArrayList<String> capture;
	private int index;
	
	public DialogCapture() {
		capture = new ArrayList<String>();
		index = 0;
	}
	
	public ArrayList<String> getCapture() {
		return capture;
	}
	
	public void clearCapture() {
		capture.clear();
	}
	
	public void add(String s) {
		capture.add(s);
	}
	
	public String get(int i) {
		return capture.get(i);
	}
	
	public String readNext() {
		if (index >= capture.size()) {
			return "";
		} else {
			return capture.get(index++);
		}
	}
	
	public String covertToBuilding() {
		String res = get(0) + " " + get(1) + ";";
		for (int i = 2; i < capture.size(); i++) {
			res += get(i);
			if ((i-1) % 7 == 0 && (i-2) != 0 && i != capture.size() - 1) {
				res += ";";  
			} else {
				res += " ";
			}
		}
		return res;
	}
	
	public void addAt(int index, String s) {
		capture.add(index, s);
	}
}
