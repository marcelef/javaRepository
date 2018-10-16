package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;

public class Person {

	private Point pos;
	
	public Person() {
		this.setPosition(0, 0);
	}
	
	public void setPosition(int x, int y) {
		pos.setLocation(x, y);
	}
	
	public Point getPosition() {
		return pos;
	}
	
}
