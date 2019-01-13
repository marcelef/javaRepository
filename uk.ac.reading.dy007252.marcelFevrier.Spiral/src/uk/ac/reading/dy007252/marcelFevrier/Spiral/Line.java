package uk.ac.reading.dy007252.marcelFevrier.Spiral;

import java.awt.Point;

public class Line {
	private Point start;
	private Point end;
	
	public Line() {
		start = new Point(0,0);
		end = new Point(0,0);
	}
	
	public Line(int x1, int y1, int x2, int y2) {
		start = new Point(x1, y1);
		end = new Point(x2, y2);
	}
	
	public int getX1() {
		return (int) start.getX();
	}
	public int getY1() {
		return (int) start.getY();
	}
	public int getX2() {
		return (int) end.getX();
	}
	public int getY2() {
		return (int) end.getY();
	}
	
	public void showLine(Gui gui) {
		gui.showLine(getX1(), getY1(), getX2(), getY2(), 1, 'k');
	}
}
