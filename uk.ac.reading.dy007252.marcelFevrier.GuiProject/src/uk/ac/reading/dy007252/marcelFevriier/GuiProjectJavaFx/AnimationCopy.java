package uk.ac.reading.dy007252.marcelFevriier.GuiProjectJavaFx;

import java.awt.Point;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnimationCopy extends Application {
	int canvasSize = 512;				// constants for relevant sizes
	double earthOrbitSize = canvasSize / 3;
	double sunSize = 80;
	Point sunPos;
	double earthSize = 30;
	double marsSize = 25;
    GraphicsContext gc;
    VBox rightPane;
    Random rgen = new Random();
    boolean isPaused;
    long pauseTime;
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
		double xEarth = sunPos.getX() + earthOrbitSize * Math.cos(t);	// calculate coordinates of earth
		double yEarth = sunPos.getY() + earthOrbitSize * Math.sin(t);
		
		double xMars = sunPos.getX() + earthOrbitSize * 1.5 * Math.cos(t/2);
		double yMars = sunPos.getY() + earthOrbitSize * 1.5 * Math.sin(t/2);
			
			// now clear canvas and draw earth and sun
		gc.clearRect(0,  0,  canvasSize * 1.5,  canvasSize * 1.5);
		drawIt( earth, xEarth, yEarth, earthSize );
		drawIt( sun, sunPos.getX(), sunPos.getY(), sunSize );
		drawIt( mars, xMars, yMars, marsSize );
		drawStatus(xEarth, yEarth, xMars, yMars);

	}
	
	private void drawStatus(double eX, double eY, double mX, double mY){
		rightPane.getChildren().clear();
		Label earth = new Label("Earth at " + String.format("%.1f", eX) + ", " + String.format("%.1f", eY));
		Label mars = new Label("Mars at " + String.format("%.1f", mX) + ", " + String.format("%.1f", mY));
		rightPane.getChildren().addAll(earth, mars);
	}
	
	private void showMessage(String type, String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(type);
		alert.setHeaderText(type + " Section");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	private void doExit(){
		System.exit(0);
	}
	
	private void showAbout(){
		showMessage("About", "Marcel's BorderPane Application");
	}
	
	private void showHelp(){
		showMessage("Help", "HELP");
	}
	
	private MenuBar setMenu(){
		MenuBar menuBar = new MenuBar();
		
		Menu mFile = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent actionEvent){
				doExit();
			}
		});
		
		mFile.getItems().addAll(exit);
		
		Menu mHelp = new Menu("Help");
		MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent actionEvent){
				showAbout();
			}
		});
		
		MenuItem help = new MenuItem("Help");
		help.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent actionEvent){
				showHelp();
			}
		});
		
		mHelp.getItems().addAll(about, help);
		
		menuBar.getMenus().addAll(mFile, mHelp);
		
		return menuBar;
	}
	
	private ButtonBar setButtons(){
		
		ButtonBar buttonBar = new ButtonBar();
		
		Button startBtn = new Button("Animate");
		Button pauseBtn = new Button("Pause");
		
		
		startBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent actionEvent){
				isPaused = false;
				animate();
			}
		});
		
		pauseBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent actionEvent){
				pause();
			}
		});
		
		buttonBar.getButtons().addAll(startBtn, pauseBtn);
		
		return buttonBar;
	}
	
	private void pause(){
		if (!isPaused){
			isPaused = true;
		} else {
			isPaused = false;
		}
	}
	
	private void animate(){
		final long startNanoTime = System.nanoTime();
			// for animation, note start time
		
		new AnimationTimer()			// create timer
	    	{
	    		public void handle(long currentNanoTime) {
	    				// define handle for what do at this time
	    			if(!isPaused){
	    				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 			// calculate time
		    			drawSystem(t);
	    			} else {
	    				this.stop();
	    			}
	    		}
	    	}.start();
	}
	
	private void setMouseEvents(Canvas canvas){
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent e) {
						sunPos.setLocation(e.getX(), e.getY());
					}
				});
	}
	
	/**
	 * main function ... sets up canvas, menu, buttons and timer
	 */
	@Override
	public void start(Stage stagePrimary) throws Exception {
		stagePrimary.setTitle("Marcel Fevrier");
		
		BorderPane bp = new BorderPane();
		
		bp.setTop(setMenu());
		
		isPaused = true;
		
		sunPos = new Point(canvasSize/2, canvasSize/2);
		
	    Group root = new Group();					// for group of what is shown
	    Canvas canvas = new Canvas( canvasSize * 1.5, canvasSize * 1.5);
	    							// create canvas onto which animation shown
	    root.getChildren().add( canvas );			// add to root and hence stage
	    gc = canvas.getGraphicsContext2D();
	    setMouseEvents(canvas);
	    bp.setCenter(root);
	    
	    rightPane = new VBox();
	    bp.setRight(rightPane);
	    bp.setBottom(setButtons());
	    
	    drawSystem(0);
	    
	    Scene scene = new Scene(bp, canvasSize*1.8, canvasSize*1.8);
	    								// get context on canvas onto which images put
		// now load images of earth and sun
		// note these should be in package
	    
		stagePrimary.setScene(scene);
	    stagePrimary.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);			// launch the GUI
	}

}