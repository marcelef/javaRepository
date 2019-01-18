package uk.ac.reading.dy007252.marcelFevrier.IntelligentBuilding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author shsmchlr
 * Class for a building ... which will have rooms ...
 */
public class Building {
	/**
	 * The building in which there are various rooms and objects
	 * Its size is defined by xSize,ySize
	 * Variables are used for actual rooms

	 */
	private int xSize = 10;						// size of building
	private int ySize = 10;
	private ArrayList<Room> allRooms;			// array of rooms
	private Person occupant;					// one person
	private Random ranGen;						// for generating random numbers

	/**
	 * Construct a building 
	 */
	public Building(String bs) {
		allRooms = new ArrayList<Room>();		// create space for rooms
		ranGen = new Random();					// create object for generating random numbers
		setBuilding(bs);						// now set building using string bs
	}

	/**
	 * set up the building, as defined in string
	 * @param bS		of form xS,yS;x1 y1 x2 y2 xd yd ds;  etc
	 * 					xS,yS define size, and for each room have locations of opposite corners, door and door size
	 */
	public void setBuilding(String bS) {
		allRooms.clear();
		StringSplitter bSME = new StringSplitter(bS, ";");		// split strings by ;
		StringSplitter bSz = new StringSplitter(bSME.getNth(0, "5 5"), " ");	// split first by space
		xSize = bSz.getNthInt(0, 5);						// get first of the first string, being xsize
		ySize = bSz.getNthInt(1, 5);
		for (int ct = 1; ct<bSME.numElement(); ct++)		// remaining strings define rooms
			allRooms.add(new Room (bSME.getNth(ct, "")));	// add each in turn
		addPerson();										// now add a person 
	}
	/**
	 * On arena size
	 * @return size in x direction of robot arena
	 */
	public int getXSize() {
		return xSize;
	}
	
	/**
	 * On arena size
	 * @return size in y direction of robot arena
	 */
	public int getYSize() {
		return ySize;
	}

	/**
	 * set new destination for person and path to it
	 * In this version puts person in random room and sets path from there to room's door
	 * @param occupant
	 */
	void setNewRoom(Person occupant) {
		// at this stage all this does is 
		int cRoom = whichRoom(occupant.getXY());
		int dRoom = cRoom;
		while (dRoom == cRoom) dRoom = ranGen.nextInt(allRooms.size());	// get another room randomlt
		
		occupant.clearPath();
		occupant.setXY(allRooms.get(dRoom).getRandom(ranGen));
		occupant.setPath(allRooms.get(dRoom).getByDoor(0));		// position by door
		
		occupant.setStopped(false);								// say person can move
	}
	
	/**
	 * calculate a random room number
	 * @return	number in range 0.. number of rooms
	 */
	public int randRoom() {
		return ranGen.nextInt(allRooms.size());
	}
	/**
	 * create new person and set path for it to follow
	 */
	public void addPerson() {
		occupant = new Person (allRooms.get(0).getRandom(ranGen));	// create person in first room
		setNewRoom(occupant);
	}
	/**
	 * show all the building's rooms and person in the interface
	 * @param bi	the interface
	 */
	public void showBuilding (BuildingGUI bi) {
		for (Room r : allRooms) r.showRoom(bi);
				// loop through array of all rooms, displaying each
		occupant.showPerson(bi);
	}

	/**
	 * method to update the building
	 * Here it just deals with the occupant
	 */
	public void update() {
		if (occupant.getStopped()) setNewRoom(occupant); else occupant.update();
	}

	/**
	 * method to determine which room position x,y is in
	 * @param xy
	 * @return	n, the number of the room or -1 if in corridor
	 */
	public int whichRoom(Point xy) {
		int ans = -1;
		for (int ct = 0; ct<allRooms.size(); ct++)
			if (allRooms.get(ct).isInRoom(xy)) ans = ct;
		return ans;
	}
	
	
	/**
	 * method to return information bout the building as a string
	 */
	public String toString() {
		String s = "Building size " + getXSize() + "," + getYSize() + "\n";
		for (Room r : allRooms) s = s + r.toString() + "\n";
		s = s + occupant.toString();
		return s;
	}

	public static void main(String[] args) {
		Building b = new Building ("500 500;0 0 250 250 100 250 20;250 0 450 450 320 450 30;0 300 450 450 300 300 15");		// create building
		System.out.println(b.toString());				// and print it
	}
}