package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.util.ArrayList;
import java.util.Random;

public class Building {
	
	/*
	 * GIT test 
	 */
	
	/**
	 * The dimensions of the building are stored in xSize and ySize
	 */
	private int xSize;
	private int ySize;
	
	private ArrayList<Person> persons = new ArrayList<Person>();
	
	private Random rand;
	
	ArrayList<Room> allRooms = new ArrayList<Room>();
	
	/**
	 * @param params The parameter string passed to the building to instantiate it.
	 */
	public Building(String params) {
		this.setBuilding(params);
		
		rand = new Random();
		
		persons.add(new Person(this.getRandomRoom().randomPosition(rand)));
		
	}
	
	/**
	 * Instantiates the building using a string of parameters.
	 * @param params The parameter string passed to the building to instantiate it.
	 */
	public void setBuilding(String params) {
		String[] paramsArr = params.split(";");
		String[] paramsDimensions = paramsArr[0].split(" ");
		
		this.xSize = Integer.parseInt(paramsDimensions[0]);
		this.ySize = Integer.parseInt(paramsDimensions[1]);
		
		for (int step = 1; step < paramsArr.length; step++) {
			Room tempRoom = new Room(paramsArr[step]);
			this.allRooms.add(tempRoom);
		}
	}
	
	/**
	 * Selects a random room from all current rooms in the building.
	 * @return The index of a randomly selected room within the building.
	 */
	public Room getRandomRoom() {
		int randRoom = this.rand.nextInt(this.allRooms.size());
		return this.allRooms.get(randRoom);
	}
	
	/**
	 * Removes all rooms in currently instantiated within the building.
	 */
	public void clearBuilding() {
		allRooms.clear();
	}
	
	public void showBuilding(BuildingInterface bi) {
		
		for (Room r : this.allRooms) {
			r.showRoom(bi);
		}
		
		
	}
	
	public int getXSize() {
		return this.xSize;
	}
	
	public int getYSize() {
		return this.ySize;
	}
	
	/**
	 * Converts the building's properties into one string which can be displayed.
	 * @return a String object representation of the entire building.
	 */
	public String toString() {
		
		String res = "";  // The string that will be returned to the caller
		
		res += "The building's dimensions are " + this.xSize + "*" + this.ySize + ".\n\n";
		
		for (Room r:allRooms) { 
			res += r.toString() + "\n"; // Gets the string representation of each room currently in the building and appends to the return result
		}
		
		res += "The person is located at " + (int) persons.get(0).getPosition().getX() + "," + (int) persons.get(0).getPosition().getY() + ".\n";
		
		return res;
	}
	
	public static void main(String[] args) {
		Building myBuilding = new Building("11 11;0 0 5 5 3 5;6 0 10 10 6 6;0 5 5 10 2 5");
		
		String res = myBuilding.toString();
		
		System.out.println(res);
	}
	
}
