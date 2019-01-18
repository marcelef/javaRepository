package uk.ac.reading.dy007252.marcelFevrier.IntelligentBuilding;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author shsmchlr
 * This is for people moving in building
 */
public class Person {
	private Point xy;					// person's position
	private ArrayList<Point> path;		// path it follows .. a series of xy points moves between
	private boolean stopped;			// is it moving
	
	/**
	 * create person at the given xy position 
	 * @param xys	position
	 */
	Person(Point xys) {
		xy = xys;
		path = new ArrayList<Point>();			// create empty path
		stopped = true;							// by default not moving
	}
	/**
	 * get the person's position
	 * @return	the position
	 */
	public Point getXY() {
		return xy;
	}
	/**
	 * get x coordinate of person
	 * @return x
	 */
	public int getX() {
		return (int) xy.getX();
	}
	/**
	 * get y coordinate of person
	 * @return y
	 */
	public int getY() {
		return (int) xy.getY();
	}
	/**
	 * set the person's position
	 * @param pxy	new position
	 */
	public void setXY(Point pxy) {
		xy = pxy;
	}
	/**
	 * set person as being stopped or not
	 * @param isStopped
	 */
	public void setStopped(boolean isStopped) {
		stopped = isStopped;
	}
	/**
	 * Is person stopped
	 * @return if so
	 */
	public boolean getStopped() {
		return stopped;
	}
	/**
	 * show person in the given building interface
	 * @param bi
	 */
	public void showPerson(BuildingGUI bi) {
		bi.showItem((int) xy.getX(), (int) xy.getY(), 4, 'r');
	}
	
	/** 
	 * return info about person as string
	 * @return	the string
	 */
	public String toString() {
		return "Person at " + (int) xy.getX() + ", " + (int) xy.getY();
	}
	/** 
	 * clear the path the person has to follow
	 */
	public void clearPath() {
		path.clear();
	}
	/** 
	 * add new xy to path
	 * @param xyp	new position
	 */
	public void setPath (Point xyp) {
		path.add(xyp);
	}
	/**
	 * is person at the specified position?
	 * @param pathXY
	 * @return
	 */
	private boolean equalXY(Point pathXY) {
		return ( (int)pathXY.getX() == (int) xy.getX() ) && ( (int)pathXY.getY() == (int) xy.getY() );
	}
	/**
	 * move one step towards the given position
	 * @param pathXY
	 */
	private void moveTowards (Point pathXY) {
		int dx = 0;			// amount by which it will move in x .. and y, set to -1, 0 or 1
		int dy = 0;
		if (xy.getX() < pathXY.getX()) dx = 1; else if (xy.getX() > pathXY.getX()) dx = -1;
		if (xy.getY() < pathXY.getY()) dy = 1; else if (xy.getY() > pathXY.getY()) dy = -1;
		xy.translate(dx, dy);	// now move
	}
	/**
	 * attempt to move person unless it is stopped
	 */
	public void update() {
		if (stopped) {} 						// do nowt
		else if (equalXY(path.get(0)) ){		// if at next point on path
			stopped = true;						// as one point in this version, say stopped
		}	
		else moveTowards(path.get(0));			// move closer to next destination
	}
}