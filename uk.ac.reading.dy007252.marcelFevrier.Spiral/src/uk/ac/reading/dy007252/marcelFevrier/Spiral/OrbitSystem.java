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
		
		/* 
		 * Nice presets (in order of (OrbitSize, Speed)
		 * 
		 * [(100, 0.06), (250, 0.01), (350, 0.035)] <- Pentagram
		 * 
		 * [(40, 0.025), (300, 0.025), (350, 0.045)] Increasing the speed of the last satellite creates more detail
		 * 
		 */
		
		satellites.clear();
		satellites.add(new Satellite(new Point(350,250), new Point(350,350), 10));
		satellites.get(0).setOrbitSize(100);
		satellites.get(0).setSpeed(0.06);
		satellites.get(0).resetPosition();
		
		satellites.add(new Satellite(new Point(350,150), new Point(350,350), 10));
		satellites.get(1).setOrbitSize(250);
		satellites.get(1).setSpeed(0.01);
		satellites.get(1).resetPosition();
		
		satellites.add(new Satellite(new Point(0,0), new Point(0,0), 10));
		satellites.get(2).setOrbitSize(350);
		satellites.get(2).setSpeed(0.06);
		satellites.get(2).resetPosition();
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
			s.update(count);
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
		int x3 = satellites.get(2).getX();
		int y3 = satellites.get(2).getY();
		
		lines.add(new Line(x1, y1, x2, y2));
		lines.add(new Line(x2, y2, x3, y3));
		
	}
}
