package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;

public class Person {

	private Point pos;
	
	public Person() {
		this.pos = new Point(0,0);
	}
	
	/**
	 * This constructor can create a person and set their position using two integers which are converted into a point object.
	 * @param x the x position of the person.
	 * @param y the y position of the person.
	 */
	public Person(int x, int y) {
		this.pos = new Point(x,y);
	}
	
	/**
	 * This constructor creates a person and sets their position using a Point object as an input.
	 * @param p a Point object containing the x and y coordinates for the person's position.
	 */
	public Person(Point p) {
		this.pos = p;
	}
	
	/**
	 * Sets the person's position to different location.
	 * @param x the new x value of the person's location.
	 * @param y the new y value of teh person's location.
	 */
	public void setPosition(int x, int y) {
		pos.setLocation(x, y);
	}
	
	/**
	 * Retrieves the current location of the person.
	 * @return the location of the person as a Point object.
	 */
	public Point getPosition() {
		return pos;
	}
	
	public void showPerson(BuildingInterface bi) {
		
	}
	
}
