package uk.ac.reading.dy007252.marcelFevrier.GuiProjectJavaFx;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class SolarSystem {
	
	private Point sunPos;
	private double sunSize = 80;
	private Image sun = new Image(getClass().getResourceAsStream("sun.png"));
	
	private ArrayList<Planet> planets;
	
	public SolarSystem() {
		this.planets = new ArrayList<Planet>();
		this.sunPos = new Point(0, 0);
		this.calcSystem(0);
	}
	
	/**
	 * calculate position of Earth at specified angle and then draw system
	 * @param t		angle (time dependent) of Earth
	 */
	public void drawSystem (AnimationGui gui) {
			
		gui.clearCanvas();
		gui.drawIt( sun, sunPos.getX(), sunPos.getY(), sunSize );
		
		for (Planet planet : this.planets) {
			planet.draw(gui);
		}

	}
	
	public void calcSystem(double t) {
		for (Planet planet : this.planets) {
			planet.calcCurrentPos(this.sunPos, t);
		}
		
		//this.drawSystem();
	}
	
	public ArrayList<Planet> getAllPlanets(){
		return this.planets;
	}
	
	public Planet getPlanet(int index) {
		return this.planets.get(index);
	}
	
	public void setPlanet(int index, Planet planet) {
		this.planets.set(index, planet);
	}
	
	public void addPlanet(Planet planet) {
		this.planets.add(planet);
	}
	
	public void removePlanet(int index) {
		this.planets.remove(index);
	}
	
	public void setSunPosition(double x, double y) {
		this.sunPos.setLocation(x, y);
	}
	
	public String toString() {
		String res = "";
		
		res += "Sun position: (" + this.sunPos.getX() + ", " 
				+ this.sunPos.getY() + ")\n";
		
		for (Planet planet : this.planets) {
			res += planet.toString();
		}
		
		return res;
	}
	
	public void basicInitialise() {
		Planet earth = new Planet(30, 0.4, 1, "Earth");
		
		Planet mars = new Planet(25, 0.5, 0.5, "Mars");
		
		Planet venus = new Planet(20, 0.3, 1.3, "Venus");
		
		Planet mercury = new Planet(15, 0.2, 1.5, "Mercury");
		
		this.addPlanet(earth);
		this.addPlanet(mars);
		this.addPlanet(venus);
		this.addPlanet(mercury);
		
		
	}
	
	public static void main(String[] args) {
		
		SolarSystem solarSystem = new SolarSystem();
		//solarSystem.basicInitialise();
		System.out.println(solarSystem.toString());
	}
}
