package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import javafx.scene.paint.Color;

public enum GuiColour {
	BLACK ('k'),
	BLUE ('b'),
	CYAN ('c'),
	GREEN ('g'),
	RED ('r'),
	PINK ('p'),
	ORANGE ('o'),
	WHITE ('w'),
	YELLOW ('y');
	
	private char value;
	
	GuiColour(char value) { this.value = value; }
	
	public char getValue() { return value; }
	
	public static char reverse(String str) {
		for (GuiColour c : GuiColour.values()) {
			if (c.toString() == str) {
				return c.value;
			}
		}
		return 'k';
	}
	
	private String getStringFromChar(char c) {
		for (GuiColour colour : GuiColour.values()) {
			if (colour.value == c) {
				return colour.toString();
			}
		}
		return "BLACK";
	}
}