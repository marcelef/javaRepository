package uk.ac.reading.dy007252.marcelFevrier.buildingconsole;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BuildingInterface {

	Scanner s; // scanner used for input from user

	Building myBuilding; // building in which rooms are shown

	char drawing[][];

	/**
	 * return as String definintion of bOpt'th building
	 * 
	 * @param bOpt
	 */
	public String buildingString(int bOpt) {
		if (bOpt == 1)
			return "11 11;0 0 4 4 2 4;6 0 10 10 6 6;0 6 4 10 2 6";
		else
			return "40 12;0 0 15 4 8 4;15 0 30 4 22 4;0 6 10 11 6 6";
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
			System.out.print("(N)ew buidling (I)nfo, e(X)it, (D)raw, (M)ove, (A)nimates > ");
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

			}
		} while (ch != 'X'); // test if end

		s.close(); // close scanner
	}
	
	public void doDisplay() {
		
		int xSize = myBuilding.getXSize();
		int ySize = myBuilding.getYSize();
		
		myBuilding.showBuilding(this);
		
		for (int row = 0; row < xSize + 2; row++) {
			for (int col = 0; col < ySize + 2; col++) {
				System.out.print(this.drawing[row][col] + " ");
				if (col == ySize + 1) {
					System.out.print("\n");
				}
			}
		}
	}
	
	public void animate() {
		while (!myBuilding.getPersonAtDoor()) {
			myBuilding.movePerson();
			this.doDisplay();
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
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
