package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.awt.Point;

public abstract class RoomObject {
	protected Point position;
	protected double size;
	protected char colour;
	protected int id;
	private static int contId;
	
	public RoomObject(Point p){
		this.position = p;
		this.id = 1 + this.contId++;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void setPosition(Point p) {
		this.position = p;
	}
	
	public int getX() {
		return (int) this.position.getX();
	}
	
	public int getY() {
		return (int) this.position.getY();
	}
	
	public double getSize() {
		return this.size;
	}
	
	public void setSize(double s) {
		this.size = s;
	}
	
	public char getColour() {
		return this.colour;
	}
	
	public void setColour(char c) {
		this.colour = c;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String toString() {
		String res = "";
		
		res += "Object at (" + this.getX() + ", " + this.getY() + ") with ID: " + this.getID() + "\n";
		
		return res;
	}
}
