package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * The class for the Room object
 * @author fevri
 *
 */
public class Room {
	private int[] oldDoor = new int[3];
	private int[][] oldCorners = new int[2][3];
	private int defaultSize = 1;

	private Point[] corners = new Point[2];
	
	private Point door;

	/**
	 * Instantiates the room with a definition of its corners and the location of
	 * the room's door.
	 * 
	 * @param params
	 *            The string used to instantiate the room object.
	 */
	public Room(String params) {
		String paramArr[] = params.split(" "); // Split the parameter into separate parameters

		this.corners[0] = new Point(Integer.parseInt(paramArr[0]), Integer.parseInt(paramArr[1]));
		
		this.corners[1] = new Point(Integer.parseInt(paramArr[2]), Integer.parseInt(paramArr[3]));
		
		this.door = new Point(Integer.parseInt(paramArr[4]), Integer.parseInt(paramArr[5]));
		
	}

	/**
	 * Determines whether a give coordinate is within the current room or not.
	 * 
	 * @param p
	 *            The point variable to be determined whether it is in the room or
	 *            not.
	 * @return A boolean value of true or false depending on whether the given
	 *         parameter is within the room or not.
	 */
	public boolean isInRoom(Point p) {

		if (isBetween((int)this.corners[0].getX(), (int)this.corners[1].getX(), (int) p.getX())
				&& isBetween((int)this.corners[0].getY(), (int)this.corners[1].getY(), (int) p.getY())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * gets a random position within the room's walls
	 * @param rand the random generator used to generate random x and y coordinates
	 * @return a Point object which is the random location within the room
	 */
	public Point randomPosition(Random rand) {

		int x;
		int y;

		if ((int)this.corners[0].getX() > (int)this.corners[1].getX()) {
			x = (int)this.corners[1].getX() + 1 + rand.nextInt((int)this.corners[0].getX() - (int)this.corners[1].getX() - 1);
		} else {
			x = (int)this.corners[0].getX() + 1 + rand.nextInt((int)this.corners[1].getX() - (int)this.corners[0].getX() - 1);
		}

		if ((int)this.corners[0].getY() > (int)this.corners[1].getY()) {
			y = (int)this.corners[1].getY() + 1 + rand.nextInt((int)this.corners[0].getY() - (int)this.corners[1].getY() - 1);
		} else {
			y = (int)this.corners[0].getY() + 1 + rand.nextInt((int)this.corners[1].getY() - (int)this.corners[0].getY() - 1);
		}

		return new Point(x, y);
	}

	/**
	 * Determines whether a particular point lays between two given boundary points.
	 * 
	 * @param firstPoint
	 *            The first of the two given boundary points which the inspected
	 *            point must be between.
	 * @param secondPoint
	 *            The second of the two given boundary points which the inspected
	 *            point must be between.
	 * @param x
	 *            The actual point being determined whether it lays between the two
	 *            given boundary points.
	 * @return true or false depending on whether or not the inspected point lays
	 *         between the two boundary points.
	 */
	private boolean isBetween(int firstPoint, int secondPoint, int x) {

		if (firstPoint > secondPoint) {
			if (x >= secondPoint && x <= firstPoint) {
				return true;
			} else {
				return false;
			}
		} else if (firstPoint < secondPoint) {
			if (x >= firstPoint && x <= secondPoint) {
				return true;
			} else {
				return false;
			}
		} else if (firstPoint == secondPoint) {
			if (x == firstPoint)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	/**
	 * Shows the room on the building interface
	 * @param bi the building interface that the room will be shown on
	 */
	public void showRoom(BuildingInterface bi) {
		for (int row = (int)this.corners[0].getY(); row <= (int)this.corners[1].getY(); row++) {
			for (int col = (int)this.corners[0].getX(); col <= (int)this.corners[1].getX(); col++) {
				if (row == (int)this.corners[0].getY() || row == (int)this.corners[1].getY()) {
					bi.showIt(row+1, col+1, '-');
				}
				else if (col == (int)this.corners[0].getX() || col == (int)this.corners[1].getX()) {
					bi.showIt(row+1, col+1, '|');
				}
				if (row == (int)this.door.getY() && col == (int)this.door.getX()) {
					bi.showIt(row+1, col+1, ' ');
				}
			}
		}
	}
	
	/**
	 * getter for the doors location for this particular room
	 * @return the location for this room's door as a Point
	 */
	public Point getDoorLocation() {
		return this.door;
	}
	
	/**
	 * Gets the Point just within or just outside this room's door depending on the given parameter
	 * @param param the parameter which will be used to determine whether to return the Point just inside the door (-1 as param) or the Point just outside the door (1 as param)
	 * @return the Point just inside or outside the door. The door's location is returned if any integer other than a -1 or 1 is passed as the param
	 */
	public Point getDoorLocation(int param) {
		
	Point tempPoint = new Point(this.door);

	if (this.door.getX() == this.corners[0].getX()) { // if the door is in line with the first corner's x...
		if (param == -1) { // and we want the point just inside the door then...
			tempPoint.translate(1, 0); // the point we want is just the door's location with its x value incremented by 1
		} else { // if we want the location just outside the door then...
			tempPoint.translate(-1, 0); // the point we want is the location of the door with its x decremented by 1
		}
	} else if (this.door.getX() == this.corners[1].getX()) { // if the door is in line with the second corner's x...
		if (param == -1) { // we want the inner point
			tempPoint.translate(-1, 0); // we want the door location with x decremented
		} else { // we want the outer location
			tempPoint.translate(1, 0); // we want the door's location with x incremented
		}
	}
	
	if (this.door.getY() == this.corners[0].getY()) { // if the door is in line with the first corner's y...
		if (param == -1) { // we want the inner point
			tempPoint.translate(0, 1); // increment door's location's y
		} else { // we want the outer point
			tempPoint.translate(0, -1); // decrement door's location's y
		}
	} else if (this.door.getY() == this.corners[1].getY()) { // if door in line with second corner's y...
		if (param == -1) { // want inner point
			tempPoint.translate(0, -1); // decrement door's location's y
		} else { // want outer point
			tempPoint.translate(0, 1); // increment door's location's y
		}
	}
	
	return tempPoint;
	
	}

	/**
	 * Converts the current room object into a textual representation which can be
	 * displayed.
	 * 
	 * @return A string/textual representation of the room object which can be
	 *         displayed.
	 */
	public String toString() {
		String res = "";

		res += "Corner at " + (int)this.corners[0].getX() + "," + (int)this.corners[0].getY() + " with size " + this.defaultSize + "\n";
		res += "Corner at " + (int)this.corners[1].getX() + "," + (int)this.corners[1].getY() + " with size " + this.defaultSize + "\n";

		res += "Door is at " + (int)this.door.getX() + "," + (int)this.door.getY() + " with size " + this.defaultSize + "\n";


		return res;
	}

	public static void main(String[] args) {

		Room room = new Room("0 0 5 5 0 2");

		String res = room.toString();
		
		JOptionPane.showMessageDialog(null, res);

		Random randomVal = new Random();

		Point testPoint = room.randomPosition(randomVal);

		JOptionPane.showMessageDialog(null, "(" + (int) testPoint.getX() + "," + (int) testPoint.getY() + ")");

	}
}

