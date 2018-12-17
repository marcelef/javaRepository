package uk.ac.reading.dy007252.marcelFevrier.Spiral;

import java.awt.Point;

public class Satellite {
	
	private Point position;
	private Point origin;
	private double size;
	
	
	public Satellite() {
		this.position = new Point(0,0);
		this.origin = new Point(0,0);
		this.size = 1;
	}
	
	public Satellite(Point pos, Point or, double s) {
		this.position = pos;
		this.origin = or;
		this.size = s;
	}
	
	public void setPosition(Point p) {
		this.position = p;
	}
	
	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public int getX() {
		return (int)this.position.getX();
	}
	
	public int getY() {
		return (int)this.position.getY();
	}
	
	public void setOrigin(Point p) {
		this.origin = p;
	}
	
	public void setOrigin(int x, int y) {
		this.origin = new Point(x, y);
	}
	
	public Point getOrigin() {
		return this.origin;
	}
	
	public void translate(int dx, int dy) {
		this.position.translate(dx, dy);
	}
	
	public void drawObject(Gui gui) {
		gui.drawItem(this.getX(), this.getY(), this.size, 'k');
	}
}
