package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

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

		//this.oldCorners[0][0] = Integer.parseInt(paramArr[0]); // corners[0] is the first corner, corner[0][0] is the x
															// value for the first corner
		//this.oldCorners[0][1] = Integer.parseInt(paramArr[1]); // corners[0][1] is the y value for the first corner
		//this.oldCorners[0][2] = defaultSize; // PLACEHOLDER
		
		this.corners[0] = new Point(Integer.parseInt(paramArr[0]), Integer.parseInt(paramArr[1]));
		
		//this.newCorners[0].setLocation(Integer.parseInt(paramArr[0]), Integer.parseInt(paramArr[1]));

		//this.oldCorners[1][0] = Integer.parseInt(paramArr[2]); // corners[1] is the second corner
		this.oldCorners[1][1] = Integer.parseInt(paramArr[3]);
		this.oldCorners[1][2] = defaultSize; // PLACEHOLDER

		this.corners[1] = new Point(Integer.parseInt(paramArr[2]), Integer.parseInt(paramArr[3]));
		
		//this.newCorners[1].setLocation(Integer.parseInt(paramArr[2]), Integer.parseInt(paramArr[3]));

		this.oldDoor[0] = Integer.parseInt(paramArr[4]);
		this.oldDoor[1] = Integer.parseInt(paramArr[5]);
		this.oldDoor[2] = defaultSize; // PLACEHOLDER
		
		this.door = new Point(Integer.parseInt(paramArr[4]), Integer.parseInt(paramArr[5]));
		
		//this.newDoor.setLocation(Integer.parseInt(paramArr[4]), Integer.parseInt(paramArr[5]));
		
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

	public void showRoom(BuildingInterface bi) {
		for (int row = (int)this.corners[0].getY(); row <= (int)this.corners[1].getY(); row++) {
			for (int col = (int)this.corners[0].getX(); col <= (int)this.corners[1].getX(); col++) {
				if (row == (int)this.corners[0].getY() || row == (int)this.corners[1].getY()) {
					bi.drawing[row+1][col+1] = '-';
				}
				else if (col == (int)this.corners[0].getX() || col == (int)this.corners[1].getX()) {
					bi.drawing[row+1][col+1] = '|';
				}
				if (row == (int)this.door.getY() && col == (int)this.door.getX()) {
					bi.drawing[row+1][col+1] = ' ';
				}
			}
		}
	}
	
	public Point getDoorLocation() {
		return this.door;
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

		res += "Corner at " + oldCorners[0][0] + "," + oldCorners[0][1] + " with size " + oldCorners[0][2] + "\n";
		res += "Corner at " + oldCorners[1][0] + "," + oldCorners[1][1] + " with size " + oldCorners[1][2] + "\n";
		
		res += "\n\nTesting New Corners\n\n";
		
		res += "Corner at " + (int)this.corners[0].getX() + "," + (int)this.corners[0].getY() + " with size " + this.defaultSize + "\n";
		res += "Corner at " + (int)this.corners[1].getX() + "," + (int)this.corners[1].getY() + " with size " + this.defaultSize + "\n";

		res += "\nDoor is at " + oldDoor[0] + "," + oldDoor[1] + " with size " + oldDoor[2] + "\n";
		
		res += "\n\nTesting new door\n\n";
		
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
