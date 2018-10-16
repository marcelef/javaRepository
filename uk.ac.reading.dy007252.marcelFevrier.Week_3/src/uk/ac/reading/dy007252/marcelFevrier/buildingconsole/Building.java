package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.util.ArrayList;

public class Building {
	
	private int xSize;
	private int ySize;

	ArrayList<Room> allRooms = new ArrayList<Room>();
	
	public Building(String params) {
		String[] paramsArr = params.split(";");
		String[] paramsDimentions = paramsArr[0].split(" ");
		
		this.xSize = Integer.parseInt(paramsDimentions[0]);
		this.ySize = Integer.parseInt(paramsDimentions[1]);
		
		for (int step = 1; step < paramsArr.length; step++) {
			Room tempRoom = new Room(paramsArr[step]);
			this.allRooms.add(tempRoom);
		}
	}
	
	public void clearBuilding() {
		allRooms.clear();
	}
	
	public String toString() {
		
		String res = "";
		
		res += "The building's dimentions are " + this.xSize + "*" + this.ySize + ".\n\n";
		
		for (Room r:allRooms) {
			res += r.toString() + "\n";
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		Building myBuilding = new Building("11 11;0 0 5 5 3 5;6 0 10 10 6 6;0 5 5 10 2 5");
		
		String res = myBuilding.toString();
		
		System.out.println(res);
	}
	
}
