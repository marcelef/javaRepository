package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.awt.Point;
import java.util.ArrayList;

public class Person {
	
	private Point position;	
	private ArrayList<Point> path;
	private boolean hasStopped;
	private int size;
	
	Person(Point p) {
		this.position = p;
		this.path = new ArrayList<Point>();
		this.hasStopped = true;
		this.size = 4;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int s) {
		this.size = s;
	}
	
	public void setStopped(boolean bool) {
		this.hasStopped = bool;
	}
	
	public boolean getStopped() {
		return this.hasStopped;
	}
	
	public void setPosition(Point p) {
		this.position = p;
	}
	
	public void setPosition(int x, int y) {
		this.position.setLocation(x, y);
	}
	
	public void setPosition(double x, double y) {
		this.position.setLocation(x, y);
	}
	
	public void showPerson(BuildingGui bg) {
		bg.showItem((int) this.position.getX(), (int) this.position.getY(), this.size, 'r');
	}
	
	public String toString() {
		return "Person is at " + (int) this.position.getX() + ", " + (int) this.position.getY() + "\n";
	}
	
	public void clearPath() {
		this.path.clear();
	}
	
	public void addDestination(Point d) {
		this.path.add(d);
	}
	
	public int getX() {
		return (int) this.position.getX();
	}
	
	public int getY() {
		return (int) this.position.getY();
	}
	
	private void moveTowards(Point d, Building b) {
		int dx = 0;
		int dy = 0;
		
		if (this.position.getX() < d.getX() && !b.isInWall(this.getX() + 1, this.getY())) dx = 1;
		else if (this.position.getX() > d.getX() && !b.isInWall(this.getX() - 1, this.getY())) dx = -1;
		
		if (this.position.getY() < d.getY() && !b.isInWall(this.getX(), this.getY() + 1)) dy = 1;
		else if (this.position.getY() > d.getY() && !b.isInWall(this.getX() + 1, this.getY() - 1)) dy = -1;
		
		this.position.translate(dx, dy);
	}
	
	private void removeNextInPath() {
		this.path.remove(0);
	}
	
	public Point getNextInPath() {
		return this.path.get(0);
	}
	
	private boolean reachedDestination() {
		
		if (this.position.getX() == this.path.get(0).getX()
				&& this.position.getY() == this.path.get(0).getY()) return true;
		else return false;
	}
	
	public void move(Building b) {
		
		this.moveTowards(this.path.get(0), b);
		if (reachedDestination()) {
			this.removeNextInPath();
			if (this.path.size() == 0) this.setStopped(true);
		}
	}
}
