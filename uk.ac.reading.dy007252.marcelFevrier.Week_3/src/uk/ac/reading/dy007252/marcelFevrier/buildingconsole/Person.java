package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;

public class Person {

	private Point pos;
	
	public Person() {
		this.pos = new Point(0,0);
	}
	
	public Person(int x, int y) {
		this.pos = new Point(x,y);
	}
	
	public Person(Point p) {
		this.pos = p;
	}
	
	public void setPosition(int x, int y) {
		pos.setLocation(x, y);
	}
	
	public Point getPosition() {
		return pos;
	}
	
}
