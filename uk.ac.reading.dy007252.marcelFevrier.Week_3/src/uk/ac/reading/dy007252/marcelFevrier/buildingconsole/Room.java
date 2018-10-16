package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import javax.swing.JOptionPane;

public class Room {
	private int[] door = new int[3];
	private int[][] corners = new int[2][3];
	private int defaultSize = 1;
	
	/* 
	 * The constructor of the room object takes a string as an arguement 
	 */	
	public Room(String param){ 
		String paramArr[] = param.split(" "); // Split the parameter into separate parameters
		
		this.corners[0][0] = Integer.parseInt(paramArr[0]); // corners[0] is the first corner, corner[0][0] is the x value for the first corner
		this.corners[0][1] = Integer.parseInt(paramArr[1]); // corners[0][1] is the y value for the first corner
		this.corners[0][2] = 1; // PLACEHOLDER
		
		this.corners[1][0] = Integer.parseInt(paramArr[2]); // corners[1] is the second corner
		this.corners[1][1] = Integer.parseInt(paramArr[3]);
		this.corners[1][2] = 1; //PLACEHOLDER
		
		this.door[0] = Integer.parseInt(paramArr[4]);
		this.door[1] = Integer.parseInt(paramArr[5]);
		this.door[2] = 1; // PLACEHOLDER
		
		
	}
	
	public String toString(){
		String res = "";
		
		res += "Corners are at x = " + corners[0][0] + " and y = " + corners[0][1] + " with size " + corners[0][2] + "\n";
		res += "Corners are at x = " + corners[1][0] + " and y = " + corners[1][1] + " with size " + corners[1][2] + "\n";
		res += "Door is at x = " + door[0] + " and y = " + door[1] + " with size " + door[2] + "\n";
		
		return res;
	}
	
	
	public static void main(String[] args){
		
		Room room = new Room("0 0 5 5 0 2");
		
		String res = room.toString();
		
		JOptionPane.showMessageDialog(null, res);
		
	}
}


