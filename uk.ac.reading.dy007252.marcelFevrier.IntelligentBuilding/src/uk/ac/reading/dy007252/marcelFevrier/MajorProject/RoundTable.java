package uk.ac.reading.dy007252.marcelFevrier.MajorProject;

import java.awt.Point;

public class RoundTable extends RoomObject {
	
	public RoundTable(Point p) {
		super(p);
		this.size = 10;
		this.colour = GuiColour.ORANGE.getValue();
	}
	
	public RoundTable(int x, int y) {
		super(new Point(x,y));
		this.size = 10;
	}
	
	public void showObject(BuildingGui bg) {
		bg.showItem(this.getX(), this.getY(), (int)this.size, this.colour);
	}
}
