package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.awt.Point;

import javax.swing.JOptionPane;

public class Room {
	private int[] door = new int[3];
	private int[][] corners = new int[2][3];
	private int defaultSize = 1;

	/*
	 * The constructor of the room object takes a string as an argument
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

	public boolean isInRoom(Point p) {

		if (isBetween(this.corners[0][0], this.corners[1][0], (int) p.getX())
				&& isBetween(this.corners[0][1], this.corners[1][1], (int) p.getY())) {
			return true;
		} else {
			return false;
		}

	}

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

	public String toString() {
		String res = "";

		res += "Corners are at x = " + corners[0][0] + " and y = " + corners[0][1] + " with size " + corners[0][2]
				+ "\n";
		res += "Corners are at x = " + corners[1][0] + " and y = " + corners[1][1] + " with size " + corners[1][2]
				+ "\n";
		res += "Door is at x = " + door[0] + " and y = " + door[1] + " with size " + door[2] + "\n";

		return res;
	}

	public static void main(String[] args) {

		Room room = new Room("0 0 5 5 0 2");

		String res = room.toString();
		
		Point testPoint = new Point(1,2);
		
		boolean test = room.isInRoom(testPoint);

		JOptionPane.showMessageDialog(null, test);

	}
}
