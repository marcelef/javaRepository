package uk.ac.reading.dy007252.marcelFevrier.Spiral;

import java.awt.Point;
import java.util.ArrayList;

public class OrbitSystem {
	
	private ArrayList<Satellite> satellites;
	private ArrayList<Line> lines;
	private int count = 0;

	public OrbitSystem() {
		satellites = new ArrayList<Satellite>();
		lines = new ArrayList<Line>();
	}
	
	public void standardSetUp() {
		satellites.clear();
		satellites.add(new Satellite(new Point(350,250), new Point(350,350), 10));
		satellites.get(0).setOrbitSize(100);
		satellites.get(0).setSpeed(0.02);
		satellites.get(0).resetPosition();
		satellites.add(new Satellite(new Point(350,150), new Point(350,350), 10));
		satellites.get(1).setOrbitSize(200);
		satellites.get(1).setSpeed(0.07);
		satellites.get(1).resetPosition();
	}
	
	public void showSystem(Gui gui) {
		for (Satellite s : satellites) {
			s.showSatellite(gui);
		}
		
		for (Line l : lines) {
			l.showLine(gui);
		}
	}
	
	public void update(double t) {
		count++;
		
		for(Satellite s : satellites) {
			s.update(t);
		}
		
		
		if (count % 3 == 0) {
			newLine();
		}
	}
	
	public void newLine() {
		
		int x1 = satellites.get(0).getX();
		int y1 = satellites.get(0).getY();
		int x2 = satellites.get(1).getX();
		int y2 = satellites.get(1).getY();
		
		lines.add(new Line(x1, y1, x2, y2));
	}
}
