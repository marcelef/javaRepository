package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.awt.Point;
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
	
	private Person otherOccupant;
	
	private ArrayList<Person> occupants;

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
		
		occupants = new ArrayList<Person>();
		
		this.createRandomOccupants(1);
		
		objects.add(new RoundTable(50,40));
		objects.get(0).setColour(GuiColour.GREEN.getValue());

		objects.add(new SquareObject(80, 100));
		objects.get(1).setColour(GuiColour.BLUE.getValue());
		((SquareObject) objects.get(1)).setWidth(2);
		
		objects.add(new SquareObject(200, 130));
		objects.get(2).setColour(GuiColour.BLACK.getValue());
		objects.get(2).setSize(14);
		((SquareObject) objects.get(2)).setWidth(1.5);
		((SquareObject) objects.get(2)).setHeight(2.5);
		
		objects.add(new SquareObject(350, 20));
		objects.get(3).setColour(GuiColour.ORANGE.getValue());
		objects.get(3).setSize(20);
		
		objects.add(new ImageObject(330,120));
		objects.get(4).setSize(4);
	}
	
	private void createRandomOccupants(int count) {
		Room randRoom;
		for (int i = 0; i < count; i++) {
			randRoom = this.getRandomRoom();
			occupants.add(new Person(randRoom.randomPosition(rand)));
		}
	}
	
	public void addNewPerson(BuildingGui bg) {
		createRandomOccupants(1);
		showBuilding(bg);
	}
	
	public void addNewPerson(BuildingGui bg, int x, int y) {
		occupants.add(new Person(x,y));
		showBuilding(bg);
	}
	
	public void createNewRoundTable(BuildingGui bg, int x, int y, double s, char c) {
		RoundTable table = new RoundTable(x,y);
		table.setSize(s);
		table.setColour(c);
		objects.add(table);
		showBuilding(bg);
	}
	
	public void removeAllOccupants() {
		this.occupants.clear();
	}
	
	public void removeAllObjects() {
		this.objects.clear();
	}
	
	public void changeOccupantsColour(char c) {
		for (Person p : this.occupants) { 
			p.changeColour(c);
		}
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
	
	private int getRoomIndex(Room r) {
		return this.allRooms.indexOf(r);
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
		
		for (Person p : this.occupants) {
			p.showPerson(bg);
		}
		
		for (RoomObject object : this.objects) {
			if (object instanceof RoundTable) ((RoundTable) object).showObject(bg);
			else if (object instanceof SquareObject) ((SquareObject) object).showObject(bg);
			else if (object instanceof ImageObject) ((ImageObject) object).showObject(bg);
		}
	}

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

		//occupant.move(); // move the person after the list of destination's has been set
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
	
	public void setNewRoom(Person person) {
		int cRoom = getOccupantRoom(person);
		int dRoom = cRoom;
		while (dRoom == cRoom) dRoom = getRandomRoomIndex();
		
		person.clearPath();
		person.setStopped(false);
		//this.occupant.setPosition(60, 200);
		//this.occupant.addDestination(this.allRooms.get(getOccupantRoom(occupant)).getDoorLocation());
		if (cRoom != -1) {
			person.addDestination(this.allRooms.get(getOccupantRoom(person)).getDoorLocation(-1));
			person.addDestination(this.allRooms.get(getOccupantRoom(person)).getDoorLocation(1));
		}
		person.addDestination(this.allRooms.get(dRoom).getDoorLocation(1));
		person.addDestination(this.allRooms.get(dRoom).getDoorLocation(-1));
		person.addDestination(this.allRooms.get(dRoom).randomPosition(rand));
	}

	public boolean getPersonAtDoor() {
		if (this.occupant.getPosition().equals(this.allRooms.get(this.getOccupantRoom(this.occupant)).getDoorLocation())) this.personAtDoor = true; else this.personAtDoor = false;
		return this.personAtDoor;
	}
	
	public void update() {
		
		for (Person p : this.occupants) {
			if (p.getStopped()) {
				this.setNewRoom(p);
			} else {
				p.move(this);
			}
		}
	}
	
	public boolean checkCanMove(int x, int y) {
		for (Room r : this.allRooms) {
			if (r.isInWall(x, y)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Converts the building's properties into one string which can be displayed.
	 * 
	 * @return a String object representation of the entire building.
	 */
	public String toString() {

		String res = ""; // The string that will be returned to the caller
	
		res += "Person:\n";
		
		for (Person p : this.occupants) {
			res += p.toString() + " in " +  (getOccupantRoom(p) == -1 ? "no room" :  "room " + getOccupantRoom(p)) + "\n";
		}

		res += "The building's dimensions are " + this.xSize + "*" + this.ySize + ".\n\n";
		
		for (RoomObject object : this.objects) {
			res += object.toString();
		}
		
		res += "\n";
		
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
