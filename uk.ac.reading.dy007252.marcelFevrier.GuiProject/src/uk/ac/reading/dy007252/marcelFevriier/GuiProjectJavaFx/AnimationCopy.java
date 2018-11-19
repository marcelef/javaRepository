package uk.ac.reading.dy007252.marcelFevriier.GuiProjectJavaFx;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AnimationCopy extends Application {
	int canvasSize = 512;				// constants for relevant sizes
	double earthOrbitSize = canvasSize / 3;
	double sunSize = 80;
	double earthSize = 30;
	double marsSize = 25;
	VBox rightPane;
    GraphicsContext gc; 
    Image earth = new Image(getClass().getResourceAsStream("earth.png"));
    Image sun = new Image(getClass().getResourceAsStream("sun.png"));
    Image mars = new Image(getClass().getResourceAsStream("mars.png"));

    /**
     * drawIt ... draws object defined by given image at position and size
     * @param i
     * @param x
     * @param y
     * @param sz
     */
	public void drawIt (Image i, double x, double y, double sz) {
		gc.drawImage(i, x - sz/2, y - sz/2, sz, sz );
	}
	
	/**
	 * calculate position of Earth at specified angle and then draw system
	 * @param t		angle (time dependent) of Earth
	 */
	private void drawSystem (double t) {
		double x = canvasSize/2 + earthOrbitSize * Math.cos(t);	// calculate coordinates of earth
		double y = canvasSize/2 + earthOrbitSize * Math.sin(t);
		
		double xMars = canvasSize/2 + earthOrbitSize * 1.5 * Math.cos(t/2);
		double yMars = canvasSize/2 + earthOrbitSize * 1.5 * Math.sin(t/2);
			
			// now clear canvas and draw earth and sun
		gc.clearRect(0,  0,  canvasSize,  canvasSize);
		drawIt( earth, x, y, earthSize );
		drawIt( sun, canvasSize/2, canvasSize/2, sunSize );
		drawIt( mars, xMars, yMars, marsSize );

	}
	
	private void showMessage(String title, String message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
	}
	
	private void showAbout(){
		showMessage("About", "Author: Marcel Fevrier\n\nSolar Animation Program");
	}
	
	private void showHelp(){
		showMessage("Help", "HELPING");
	}
	
	/**
	 * 
	 * @return menu
	 */
	private MenuBar setMenu(){
		MenuBar menu = new MenuBar();
		
		Menu mFile = new Menu("File");
		
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		
		mFile.getItems().addAll(exit);
		
		Menu mHelp = new Menu("Help");
		
		MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent actionEvent){
				showAbout();
			}
		
		});
		
		MenuItem help = new MenuItem("Help");
		help.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent){
				showHelp();
			}
		});
		
		mHelp.getItems().addAll(about, help);
		
		menu.getMenus().addAll(mFile, mHelp);
		
		return menu;
	}
	
	/**
	 * main function ... sets up canvas, menu, buttons and timer
	 */
	@Override
	public void start(Stage stagePrimary) throws Exception {
		stagePrimary.setTitle("Marcel Fevrier");
		
		BorderPane bp = new BorderPane();
		
		bp.setTop(setMenu());
		
		rightPane = new VBox();
		
	    Group root = new Group();					// for group of what is shown
	    Scene scene = new Scene( root );			// put it in a scene
	    stagePrimary.setScene( scene );				// apply the scene to the stage
	 
	    Canvas canvas = new Canvas( canvasSize, canvasSize );
	    							// create canvas onto which animation shown
	    root.getChildren().add( canvas );			// add to root and hence stage
	 
	    gc = canvas.getGraphicsContext2D();
	    								// get context on canvas onto which images put
		// now load images of earth and sun
		// note these should be in package
	    
	    bp.setCenter(root);
	    bp.setRight(rightPane);
		
	    final long startNanoTime = System.nanoTime();
		// for animation, note start time

	    new AnimationTimer()			// create timer
	    	{
	    		public void handle(long currentNanoTime) {
	    				// define handle for what do at this time
	    			double t = (currentNanoTime - startNanoTime) / 1000000000.0; 			// calculate time
	    			drawSystem(t);
	    		}
	    	}.start();					// start it
	    
		stagePrimary.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);			// launch the GUI
	}

}