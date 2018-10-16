package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;

import javax.swing.JOptionPane;

public class Room {
	private int[] door = new int[3];
	private int[][] corners = new int[2][3];
	private int defaultSize = 1;

	/**
	 * Instantiates the room with a definition of its corners and the location of the room's door.
	 * @param params The string used to instantiate the room object.
	 */
	public Room(String params) {
		String paramArr[] = params.split(" "); // Split the parameter into separate parameters

		this.corners[0][0] = Integer.parseInt(paramArr[0]); // corners[0] is the first corner, corner[0][0] is the x
															// value for the first corner
		this.corners[0][1] = Integer.parseInt(paramArr[1]); // corners[0][1] is the y value for the first corner
		this.corners[0][2] = 1; // PLACEHOLDER

		this.corners[1][0] = Integer.parseInt(paramArr[2]); // corners[1] is the second corner
		this.corners[1][1] = Integer.parseInt(paramArr[3]);
		this.corners[1][2] = 1; // PLACEHOLDER

		this.door[0] = Integer.parseInt(paramArr[4]);
		this.door[1] = Integer.parseInt(paramArr[5]);
		this.door[2] = 1; // PLACEHOLDER

	}

	/**
	 * Determines whether a give coordinate is within the current room or not.
	 * @param p The point variable to be determined whether it is in the room or not.
	 * @return A boolean value of true or false depending on whether the given parameter is within the room or not.
	 */
	public boolean isInRoom(Point p) {

		if (isBetween(this.corners[0][0], this.corners[1][0], (int) p.getX())
				&& isBetween(this.corners[0][1], this.corners[1][1], (int) p.getY())) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Determines whether a particular point lays between two given boundary points.
	 * @param firstPoint The first of the two given boundary points which the inspected point must be between.
	 * @param secondPoint The second of the two given boundary points which the inspected point must be between.
	 * @param x The actual point being determined whether it lays between the two given boundary points.
	 * @return true or false depending on whether or not the inpsected point lays between the two boundary points.
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
	 * Converts the current room object into a textual representation which can be displayed.
	 * @return A string/textual representation of the room object which can be displayed.
	 */
	public String toString() {
		String res = "";

		res += "Corners are at x = " + corners[0][0] + " and y = " + corners[0][1] + " with size " + corners[0][2]
				+ "\n";
		res += "Corners are at x = " + corners[1][0] + " and y = " + corners[1][1] + " with size " + corners[1][2]
				+ "\n";
		res += "Door is at x = " + door[0] + " and y = " + door[1] + " with size " + door[2] + "\n";

		return res;
	}

//	public static void main(String[] args) {
//
//		Room room = new Room("0 0 5 5 0 2");
//
//		String res = room.toString();
//		
//		Point testPoint = new Point(1,2);
//		
//		boolean test = room.isInRoom(testPoint);
//
//		JOptionPane.showMessageDialog(null, test);
//
//	}
}
