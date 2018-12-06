package uk.ac.reading.dy007252.marcelFevrier.GuiProjectJavaFx;

import java.awt.Point;

import javafx.scene.image.Image;

public class Planet {
	
	private Point position;
	private double size;
	private double speed;
	private double orbitSize;
	private String name;
	private Image image;
	
	public Planet() {
		this.position = new Point(0,0);
		this.size = 1;
		this.orbitSize = 1;
		this.speed = 1;
		this.name = "earth";
	}
	
	public Planet(double size, double orbit, double speed, String name) {
		this.size = size;
		this.orbitSize = orbit;
		this.name = name;
		this.speed = speed;
		this.image = new Image(getClass().getResourceAsStream(this.name.toLowerCase() + ".png"));
		this.position = new Point(0,0);
	}
	
	public void calcCurrentPos(Point origin, double t) {
		
		double x = origin.getX() + this.orbitSize * Math.cos(t * speed);
		double y = origin.getY() + this.orbitSize * Math.sin(t * speed);
		
		this.position.setLocation(x, y);
	}
	
	public void draw(AnimationGui gui) {
		gui.drawIt(this.image, 1.5 * this.position.getX(), 1.5 * this.position.getY(), this.size);
	}
	
	public String toString() {
		String res = this.name + " position: (" + this.position.getX() + ", " 
				+ this.position.getY() + ")\n";

		return res;
	}
}
