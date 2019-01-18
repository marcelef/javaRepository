package uk.ac.reading.dy007252.marcelFevrier.GuiProjectJavaFx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AnimationGui extends Application {
	
	private int canvasSize = 512;
	private GraphicsContext gc;
	
	private SolarSystem solarSystem;

	public void drawIt (Image i, double x, double y, double sz) {
		gc.drawImage(i, x - sz/2, y - sz/2, sz, sz );
	}
	
	public void clearCanvas() {
		// clear canvas
		gc.clearRect(0,  0,  canvasSize * 1.5,  canvasSize * 1.5);
	}
	
	@Override
	public void start(Stage stagePrimary) throws Exception {
		stagePrimary.setTitle("Marcel Fevrier");
		
		BorderPane bp = new BorderPane();
		
	    Group root = new Group();					
	    Canvas canvas = new Canvas( canvasSize * 1.5, canvasSize * 1.5);

	    root.getChildren().add( canvas );
	    gc = canvas.getGraphicsContext2D();
	    bp.setCenter(root);
	    
	    Scene scene = new Scene(bp, canvasSize*1.8, canvasSize*1.8);
	    
	    this.solarSystem = new SolarSystem();
	    this.solarSystem.setSunPosition(canvasSize/2, canvasSize/2);
	    this.solarSystem.basicInitialise();
	    this.solarSystem.calcSystem(0);
	    this.solarSystem.drawSystem(this);
	    System.out.println(this.solarSystem.toString());

	    stagePrimary.setScene(scene);
	    stagePrimary.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);			// launch the GUI
	}

}
