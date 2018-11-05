package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;
import java.util.ArrayList;

/**
 * The Person class is used to create a Person object which will be used as an occupant of the building
 * @author fevri
 *
 */
public class Person {

	private Point pos;
	private Point doorPos;

	private ArrayList<Point> destinations;

	public Person() {
		this.pos = new Point(0, 0);
		this.doorPos = new Point(0, 0);
		this.destinations = new ArrayList<Point>();
	}

	/**
	 * This constructor can create a person and set their position using two
	 * integers which are converted into a point object.
	 * 
	 * @param x
	 *            the x position of the person.
	 * @param y
	 *            the y position of the person.
	 */
	public Person(int x, int y) {
		this.pos = new Point(x, y);
		this.doorPos = new Point(0, 0);
		this.destinations = new ArrayList<Point>();
	}

	/**
	 * This constructor creates a person and sets their position using a Point
	 * object as an input.
	 * 
	 * @param p
	 *            a Point object containing the x and y coordinates for the person's
	 *            position.
	 */
	public Person(Point p) {
		this.pos = p;
		this.doorPos = new Point(0, 0);
		this.destinations = new ArrayList<Point>();
	}

	/**
	 * Sets the person's position to different location.
	 * 
	 * @param x
	 *            the new x value of the person's location.
	 * @param y
	 *            the new y value of the person's location.
	 */
	public void setPosition(int x, int y) {
		pos.setLocation(x, y);
	}

	/**
	 * Retrieves the current location of the person.
	 * 
	 * @return the location of the person as a Point object.
	 */
	public Point getPosition() {
		return pos;
	}

	/**
	 * Shows the person's location on the building interface
	 * 
	 * @param bi
	 *            the building interface on which the person's location will be
	 *            shown
	 */
	public void showPerson(BuildingInterface bi) {
		bi.showIt((int) this.pos.getY() + 1, (int) this.pos.getX() + 1, 'P');
	}

	/**
	 * Setter for the location of the door of the person's current room
	 * 
	 * @param p
	 *            the Point location of the door
	 */
	public void setDoorPos(Point p) {
		this.doorPos = p;
	}

	/**
	 * Setter for the location of the door of the person's current room
	 * 
	 * @param x
	 *            the x coordinate for the door's location
	 * @param y
	 *            the y location for the door's location
	 */
	public void setDoorPos(int x, int y) {
		this.doorPos.setLocation(x, y);
	}

	/**
	 * Moves the person using the given list of destinations
	 */
	public void movePerson() {

		int dx; // the amount that we will translate in the x direction
		int dy; // the amount that we will translate in the y direction

		for (Point dest : this.destinations) { // for every destination Point in the list of destinations

			dx = 0; // start with 0 change in x as we do not know where we want to translate yet
			dy = 0; // same for y as above

			while (!this.pos.equals(dest)) { // while the person's position is not equal to the location of the current
												// destination...

				if (this.pos.getX() > dest.getX()) { // if the person's x is GREATER than that of the destination then
														// we want to move the person's x closer to the x of the
														// destination...
					dx = -1; // so we have to decrease the person's x to get the person's x closer to the
								// destination's x
				} else if (this.pos.getX() < dest.getX()) { // otherwise if the person's x is LESs than that of the
															// destination then to get closer we must move the person
															// closer by...
					dx = 1; // increasing the person's x as it will bring the person's x 1 step closer to
							// the destination
				} else /// if the person's x is NOT greater or less than (so the same as) the
						/// destination's x then we do want to move the person's x location so...
					dx = 0; // we do nothing

				if (this.pos.getY() > dest.getY()) { // same as above for the y location of the person
					dy = -1;
				} else if (this.pos.getY() < dest.getY()) {
					dy = 1;
				} else
					dy = 0;

				this.pos.translate(dx, dy); // now we know where to move the person so move them. If we moved closer to
											// the destination but still aren't at the
											// destination (since we are taking one step at a time) then the while loop
											// will loop again and again until we are at the destination,
											// then the person will move on to the next destination in the for loop

			}
		}
	}

	/**
	 * Moves the person to a given destination rather than to a list of destinations
	 * @param destination the destination where the person will move toward one step at a time
	 * @return a boolean showing if the person is at the location of the door or not
	 */
	public boolean movePerson(Point destination) {

		int dx = 0;
		int dy = 0;

		if (!this.pos.equals(destination)) {
			if (this.pos.getX() > destination.getX()) {
				dx = -1;
			} else if (this.pos.getX() < destination.getX()) {
				dx = 1;
			} else
				dx = 0;

			if (this.pos.getY() > destination.getY()) {
				dy = -1;
			} else if (this.pos.getY() < destination.getY()) {
				dy = 1;
			} else
				dy = 0;

			this.pos.translate(dx, dy);

			if (this.pos.equals(destination))
				return true;
			else
				return false;

		} else {
			return true;
		}
	}

	/**
	 * Clears the current list of destinations for the person
	 */
	public void clearDestinations() {
		this.destinations.clear();
	}

	/**
	 * Adds a destination to the list of destinations for the person
	 * @param destination the destination to add to the list of destinations as a Point
	 */
	public void addDestination(Point destination) {
		this.destinations.add(destination);
	}

}
