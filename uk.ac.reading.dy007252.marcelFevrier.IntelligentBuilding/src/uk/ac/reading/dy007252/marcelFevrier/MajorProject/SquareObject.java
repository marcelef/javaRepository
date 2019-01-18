package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.awt.Point;

public class SquareObject extends RoomObject {
	
	private double width;
	private double height;
	
	public SquareObject(Point p) {
		super(p);
		this.size = 10;
		this.colour = 'w';
		this.width = 10;
		this.height = 10;
	}
	
	public SquareObject(int x, int y) {
		super(new Point(x,y));
		this.size = 10;
		this.colour = 'w';
		this.width = 1;
		this.height = 1;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setWidth(double w) {
		this.width = w;
	}
	
	public double getHeight() {
		return this.width;
	}
	
	public void setHeight(double h) {
		this.height = h;
	}
	
	public void showObject(BuildingGui bg) {
		bg.showRectangle(this.getX(), this.getY(), this.width, this.height, this.size, this.colour);
	}
}
