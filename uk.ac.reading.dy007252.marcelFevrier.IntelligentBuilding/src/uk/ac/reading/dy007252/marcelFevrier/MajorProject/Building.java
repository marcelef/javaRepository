package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * The building class used to create and manage a building
 * 
 * @author fevri
 *
 */
public class Building {

	/**
	 * The dimensions of the building are stored in xSize and ySize
	 */
	private int xSize;  // the x size of this building
	private int ySize;  // the y size of this building

	private boolean personAtDoor; // tracks if the person is at the door or not

	private Person occupant; // the occupant of the building

	private Random rand; // a random generator object

	private ArrayList<Room> allRooms = new ArrayList<Room>(); // a list of all rooms in this building

	private ArrayList<RoomObject> objects = new ArrayList<RoomObject>();
	
	/**
	 * @param params
	 *            The parameter string passed to the building to instantiate it.
	 */
	public Building(String params) {
		this.setBuilding(params);

		rand = new Random();

		Room randRoom = this.getRandomRoom(); // random room to add the occupant to

		occupant = new Person(randRoom.randomPosition(rand)); // creating the occupant and adding the occupant to the
																// random room

		this.personAtDoor = false;
		
		objects.add(new RoundTable(50,50));
		objects.get(0).setColour('g');

		objects.add(new SquareObject(80, 100));
		objects.get(1).setColour('b');
		((SquareObject) objects.get(1)).setWidth(2);
		
		objects.add(new SquareObject(200, 130));
		objects.get(2).setColour('k');
		objects.get(2).setSize(14);
		((SquareObject) objects.get(2)).setWidth(1.5);
		((SquareObject) objects.get(2)).setHeight(2.5);
		
		objects.add(new SquareObject(350, 20));
		objects.get(3).setColour('o');
		objects.get(3).setSize(20);
		
		objects.add(new ImageObject(200,100));
	}

	/**
	 * Instantiates the building using a string of parameters.
	 * 
	 * @param params
	 *            the parameter string passed to the building to instantiate it.
	 */
	public void setBuilding(String params) {
		String[] paramsArr = params.split(";"); // split the string by ;
		String[] paramsDimensions = paramsArr[0].split(" "); // the first set is the building's dimensions and each
																// dimension is separated by a space (' ')

		this.xSize = Integer.parseInt(paramsDimensions[0]); // the first dimension argument is the x size
		this.ySize = Integer.parseInt(paramsDimensions[1]); // the second dimension argument is the y size

		// the rest of the parameter after the dimensions parameter are rooms
		for (int step = 1; step < paramsArr.length; step++) { // starting from the second parameter (since the first was
																// the dimensions) up until the last parameter
			Room tempRoom = new Room(paramsArr[step]); // a temporary room is made to capture that parameter and then...
			this.allRooms.add(tempRoom); // ...the room is stored in allRooms
		}
	}

	/**
	 * Selects a random room from all current rooms in the building.
	 * 
	 * @return The index of a randomly selected room within the building.
	 */
	private Room getRandomRoom() {
		return this.allRooms.get(this.getRandomRoomIndex()); // returns the actual randomly selected room
	}
	
	private int getRandomRoomIndex() {
		return this.rand.nextInt(this.allRooms.size());
	}

	/**
	 * Removes all rooms in currently instantiated within the building.
	 */
	public void clearBuilding() {
		this.allRooms.clear();
	}
	
	public void showBuilding(BuildingGui bg) {
		for (Room r : this.allRooms) {
			r.showRoom(bg);
		}
		
		this.occupant.showPerson(bg);
		
		for (RoomObject object : this.objects) {
			if (object instanceof RoundTable) ((RoundTable) object).showObject(bg);
			else if (object instanceof SquareObject) ((SquareObject) object).showObject(bg);
			else if (object instanceof ImageObject) ((ImageObject) object).showObject(bg);
		}
	}

	/**
	 * Imprints the layout (including the outline of the building, the outline of
	 * the rooms, and the occupant's location) of the building on the building
	 * interface's display layout
	 * 
	 * @param bi
	 *            the building interface that the layout will be shown on
	 */
	/*
	public void PREVIOUSSHOWBUILDING(BuildingGui bi) {

		for (int row = 0; row < this.ySize + 2; row++) { // for every row from row 0 to row building size + 2 (the + 2
															// because we need to display walls at the start and end of
															// the building)
			for (int col = 0; col < this.xsSize + 2; col++) { // for every column from column 0 to column building size +
																// 2 (the + 2 same as above)
				if ((row == 0 || row == this.ySize + 1) || (col == 0 || col == this.xSize + 1)) { // if the row is the
																									// first or last row
																									// OR the column is
																									// the first or last
																									// column THEN there
																									// must be a wall
					bi.showIt(row, col, '#'); // display the wall on the building interface
				} else { // if it isn't first or last row or column then it isn't a wall and is therefore
							// a space (' ')
					bi.showIt(row, col, ' '); // display a space on the building interface
				}
			}
		}
		

		/*
		 * For debugging purposes
		 * 
		 * for (int row = 0; row < xSize + 2; row++) { for (int col = 0; col < ySize +
		 * 2; col++) { if (row == 0 || row == xSize + 1) { bi.drawing[row][col] =
		 * Character.forDigit(col -1, 13); } else if (col == 0 || col == ySize + 1) {
		 * bi.drawing[row][col] = Character.forDigit(row -1, 13); } else {
		 * bi.drawing[row][col] = ' '; } } }
		 * 
		 */
/*
		for (Room r : this.allRooms) { // then for every room in the building
			r.showRoom(bi); // show the room on the building interface
		}
*/
		/*
		 * Showing that the getDoorLocation(-1/1) method works
		 * 
		 * for (Room r: allRooms) { bi.drawing[(int) r.getDoorLocation(-1).getY() +
		 * 1][(int) r.getDoorLocation(-1).getX() + 1] = '-'; bi.drawing[(int)
		 * r.getDoorLocation(1).getY() + 1][(int) r.getDoorLocation(1).getX() + 1] =
		 * '+'; }
		 * 
		 */
	/*
		// for (Person p : this.persons) {
		// bi.drawing[(int) p.getPosition().getY() + 1][(int) p.getPosition().getX() +
		// 1] = 'P';
		// }

		occupant.showPerson(bi); // lastly show the occupant on the building interface
	}
	*/

	/**
	 * Getter for the x size of the building
	 * 
	 * @return the x size of the building as a integer
	 */
	public int getXSize() {
		return this.xSize;
	}

	/**
	 * Getter for the y size of the building
	 * 
	 * @return the y size of the building as a integer
	 */
	public int getYSize() {
		return this.ySize;
	}

	/**
	 * moves the occupant one step closer to the door of the room that the occupant
	 * is currently within
	 */
	/*
	public void movePerson() {
		this.occupant.setDoorPos(this.allRooms.get(this.getOccupantRoom(this.occupant)).getDoorLocation());
		this.personAtDoor = occupant.movePerson(this.allRooms.get(this.getOccupantRoom(occupant)).getDoorLocation());
	}
	*/

	/**
	 * moves the person from the room the occupant is currently within to a random
	 * location within a random room that is not the room the person is currently
	 * within
	 */
	public void movePerson() {
		Room startRoom; // the room the occupant is currently inside of
		Room destRoom; // the random destination room the person will go to

		startRoom = this.allRooms.get(this.getOccupantRoom(occupant)); // get the room the occupant is currently in
		do {
			destRoom = this.getRandomRoom(); // get a random room for the destination room...
		} while (destRoom.equals(startRoom)); // ...while the destination room is not the same as the current room

		occupant.clearPath(); // clear all of the occupant's destinations

		occupant.addDestination(startRoom.getDoorLocation(-1)); // add the current room's inner point (before the door)
																// to the list of destinations of the occupant
		occupant.addDestination(startRoom.getDoorLocation(1)); // add the current room's outer point (after the door) to
																// the list of destinations of the occupant
		occupant.addDestination(destRoom.getDoorLocation(1)); // add the destination room's outer point to the list of
																// destinations of the occupant
		occupant.addDestination(destRoom.getDoorLocation(-1)); // add the destination room's inner point to the list of
																// destinations of the occupant
		occupant.addDestination(destRoom.randomPosition(rand)); // add to the occupant's list of destinations a random
																// position within the destination room

		occupant.move(); // move the person after the list of destination's has been set
	}

	/**
	 * gets the index of the room that the occupant is currently within
	 * 
	 * @return the index (referencing allRooms) of the room the occupant is
	 *         currently within
	 */
	public int getOccupantRoom(Person target) {
		for (int r = 0; r < this.allRooms.size(); r++) { // for every room (index)...
			if (this.allRooms.get(r).isInRoom(target.getPosition())) { // if the occupant's position is within the
																			// room...
				return r; // return that room's index and exit the method
			}
		}
		return -1; // if the occupant was not found in any of the rooms then return -1
	}
	
	public void setNewRoom(Person occupant) {
		int cRoom = getOccupantRoom(occupant);
		int dRoom = cRoom;
		while (dRoom == cRoom) dRoom = getRandomRoomIndex();
		
		this.occupant.clearPath();
		this.occupant.setStopped(false);
		this.occupant.addDestination(this.allRooms.get(getOccupantRoom(occupant)).getDoorLocation(-1));
		this.occupant.addDestination(this.allRooms.get(getOccupantRoom(occupant)).getDoorLocation(1));
		this.occupant.addDestination(this.allRooms.get(dRoom).getDoorLocation(1));
		this.occupant.addDestination(this.allRooms.get(dRoom).getDoorLocation(-1));
		this.occupant.addDestination(this.allRooms.get(dRoom).randomPosition(rand));
	}

	public boolean getPersonAtDoor() {
		if (this.occupant.getPosition().equals(this.allRooms.get(this.getOccupantRoom(this.occupant)).getDoorLocation())) this.personAtDoor = true; else this.personAtDoor = false;
		return this.personAtDoor;
	}
	
	public void update() {
		if (this.occupant.getStopped()) {
			this.setNewRoom(this.occupant);
		} else {
			this.occupant.move();
		}
	}

	/**
	 * Converts the building's properties into one string which can be displayed.
	 * 
	 * @return a String object representation of the entire building.
	 */
	public String toString() {

		String res = ""; // The string that will be returned to the caller
	
		res += "Person:\n" + this.occupant.toString();

		res += "The building's dimensions are " + this.xSize + "*" + this.ySize + ".\n\n";

		for (Room r : allRooms) {
			res += "Room:\n";
			
			res += r.toString() + "\n"; // Gets the string representation of
										// each room currently in the building
										// and
										// appends to the return result
		}

		return res;
	}

	public static void main(String[] args) {
		Building myBuilding = new Building("11 11;0 0 5 5 3 5;6 0 10 10 6 6;0 5 5 10 2 5");

		String res = myBuilding.toString();

		System.out.println(res);
	}

}