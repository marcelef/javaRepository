package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;

public class BuildingInterface {

	Scanner s; // scanner used for input from user

	Building myBuilding; // building in which rooms are shown
	
	String buildingSpec;

	private char drawing[][];

	/**
	 * return as String definintion of bOpt'th building
	 * 
	 * @param bOpt
	 */
	public String buildingString(int bOpt) {
		if (bOpt == 1) {
			buildingSpec = "11 11;0 0 4 4 2 4;6 0 10 10 6 6;0 6 4 10 2 6";
			return "11 11;0 0 4 4 2 4;6 0 10 10 6 6;0 6 4 10 2 6";
		} else {
			buildingSpec = "40 12;0 0 15 4 8 4;15 0 30 4 22 4;0 6 10 11 6 6";
			return "40 12;0 0 15 4 8 4;15 0 30 4 22 4;0 6 10 11 6 6";
		}
	}

	/**
	 * constructor for BuildingInterface sets up scanner used for input and the
	 * arena then has main loop allowing user to enter commands
	 */
	public BuildingInterface() {
		s = new Scanner(System.in); // set up scanner for user input
		int bno = 1; // initially building 1 selected

		myBuilding = new Building(buildingString(bno));// create building
		
		this.drawing = new char[myBuilding.getXSize() + 2][myBuilding.getYSize() + 2];

		char ch = ' ';
		do {
			System.out.print("(N)ew building (I)nfo, e(X)it, (D)raw, (M)ove, (A)nimate, Move (B)etween Rooms, (U)ser Building, (S)ave, (O)pen > ");
			ch = s.next().charAt(0);
			s.nextLine();
			switch (ch) {
			case 'N':
			case 'n':
				bno = 3 - bno; // change 1 to 2 or 2 to 1
				myBuilding.setBuilding(buildingString(bno));
				break;
			case 'I':
			case 'i':
				System.out.print(myBuilding.toString());
				break;
			case 'x':
				ch = 'X'; // when X detected program ends
				break;
			case 'D':
			case 'd':
				this.doDisplay();
				break;
			case 'M':
			case 'm':
				myBuilding.movePerson();
				this.doDisplay();
				break;
			case 'a':
			case 'A':
				this.animate();
				break;
			case 'b':
			case 'B':
				this.movePersonBetweenRooms();
				break;
			case 'u':
			case 'U':
				this.userBuilding();
				break;
			case 's':
			case 'S':
				this.save();
				break;
			case 'o':
			case 'O':
				this.open();
				break;

			}
		} while (ch != 'X'); // test if end

		s.close(); // close scanner
	}
	
	public void doDisplay() {
		
		int xSize = myBuilding.getXSize();
		int ySize = myBuilding.getYSize();
		
		myBuilding.showBuilding(this);
		
		// the following nested for loops are used to print the building's layout
		for (int row = 0; row < ySize + 2; row++) { // for every row
			for (int col = 0; col < xSize + 2; col++) { // for every column
				System.out.print(this.drawing[row][col] + " "); // print the item at that row and column with a space for formatting
				if (col == ySize + 1) { // if we have reached the last column then we are about to go to a new row so we need a new line
					System.out.print("\n"); // print the new line
				}
			}
		}
	}
	
	/**
	 * Allows a given cell of the drawing to be altered.
	 * @param row the row of the cell to be altered
	 * @param col the column of the cell to be altered
	 * @param ch the character that will be assigned to the cell
	 */
	public void showIt(int row, int col, char ch) {
		this.drawing[row][col] = ch;
	}
	
	/**
	 * Animates the moving of the person from their random position in the room to the door of that room
	 */
	public void animate() {
		while (!myBuilding.getPersonAtDoor()) { // while the person is not at the door...
			myBuilding.movePerson(); // move the person one step closer to the door...
			this.doDisplay(); // and display the person's new position after they took one step closer to the door
			
			try {
				TimeUnit.MILLISECONDS.sleep(500); // delay the next moving so that the animation doesn't complete too rapidly
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Moves the person from their current room to a random location within a random room that is not the current room of the person
	 */
	public void movePersonBetweenRooms() {
		myBuilding.movePersonBetweenRooms(); // move the person between rooms
		this.doDisplay(); // display the new location of the person
	}
	
	public void userBuilding() {
		
		Scanner scan = new Scanner(System.in); // Scanner used to get input from the user
		
		System.out.print("\n\nNew Building\n\nEnter the building's parameters: "); // request that the user enters the building's parameters
		
		String param = scan.nextLine(); // get the user's input
		
		this.buildingSpec = param; // assign these new parameters to the building spec
		
		this.myBuilding = new Building(param); // create the new building
		
		this.drawing = new char[myBuilding.getXSize() + 2][myBuilding.getYSize() + 2]; // fix the drawing to match the size of the new building
	}
	
	/**
	 * Allows the user to save the current building's parameters to a file. The user can name the file and indicate where to save the file.
	 */
	public void save() {
		
		JFileChooser fileChooser = new JFileChooser("C:\\Users\\fevri\\Desktop\\University\\Java\\Save Files"); // file chooser will open to the specified directory
		int status = fileChooser.showSaveDialog(null); // open the save dialog allowing the user to give the file a name and directory
		if (status == JFileChooser.APPROVE_OPTION) { 
			try (FileWriter fw = new FileWriter(fileChooser.getSelectedFile())) { // try to create a FileWriter object with the user's file being the designated file for the FileWriter
				fw.write(this.buildingSpec); // write the building's spec to the file
				fw.close(); // close the file
			} catch (Exception ex) { // if an exception happens
				ex.printStackTrace(); // print the exception stack
			}
		}
	}
	
	/**
	 * Allows the user to open a saved parameter string which will be used to create a new building. 
	 */
	public void open() {
		
		String res = ""; // the result of what is stored in the file
		
		JFileChooser fileChooser = new JFileChooser("C:\\Users\\fevri\\Desktop\\University\\Java\\Save Files"); // FileChooser will open to the sepcified directory
		
		int status = fileChooser.showOpenDialog(null); // open the open dialog allowing the user to select which file to open from a directory
		if (status == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile(); // the file which the parameters are being retrieved from
				BufferedReader b = new BufferedReader(new FileReader(file)); // the BufferedReader will be used to read the contents of the file
				res = b.readLine(); // read the first line of the file, since the file only has one line the file is closed after this
				b.close(); // close the file
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			this.buildingSpec = res; // assign the new parameters as the building's spec
			
			this.myBuilding = new Building(res); // create the new building
			
			this.drawing = new char[myBuilding.getXSize() + 2][myBuilding.getYSize() + 2];
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuildingInterface b = new BuildingInterface();
		// just call the interface
	}
}
