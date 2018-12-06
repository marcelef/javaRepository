package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.awt.Point;

import javafx.scene.image.Image;

public class ImageObject extends SquareObject {
	
	private Image image = new Image(getClass().getResourceAsStream("missingImg.png"));
	
	public ImageObject(Point p) {
		super(p);
		this.size = 1;
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
	}
	
	public ImageObject(int x, int y) {
		super(new Point(x,y));
	}
	
	public void setImage(String path) {
		this.image = new Image(getClass().getResourceAsStream(path));
	}
	
	public void showObject(BuildingGui bg) {
		bg.showImage(image, this.getX(), this.getY(), this.getWidth() * this.size, this.getHeight() * this.size);
	}
}
