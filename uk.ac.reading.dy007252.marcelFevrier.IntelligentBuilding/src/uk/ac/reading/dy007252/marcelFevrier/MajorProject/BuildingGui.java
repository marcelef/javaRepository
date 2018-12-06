package uk.ac.reading.dy007252.marcelFevrier.MajorProject;



import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class BuildingGui extends Application {
	
	private Stage stagePrimary;
	
	private Building primaryBuilding;
	private VBox rightPane;
	private GraphicsContext gc;
	private AnimationTimer timer;
	private int whichBuilding;

	
	public void showItem(int x, int y, int size, char colour) {
		gc.setFill(colFromChar(colour));
		gc.fillArc(x-size, y-size, size * 2, size * 2, 0, 360, ArcType.ROUND);
	}
	
	public void showRectangle(int x, int y, double w, double h, double size, char colour) {
		gc.setFill(colFromChar(colour));
		gc.fillRect(x, y, w * size, h * size);
	}
	
	public void showImage(Image img, int x, int y, double w, double h) {
		gc.drawImage(img, x, y, w, h);
	}
	
	/**
	 * show a Line from first xy point to second xy point, with given width and colour
	 * 
	 * @param xy1     is xy1[0] is x, xy1[1] is y
	 * @param xy2
	 * @param width
	 * @param col
	 */
	 void showLine (int[] xy1, int[] xy2, int width, char col) {
		 gc.setStroke(colFromChar(col));					// set the stroke colour
		 gc.setLineWidth(width);
		 gc.strokeLine(xy1[0], xy1[1], xy2[0], xy2[1]);		// draw line
	 }
	
	 void showWall(int x1, int y1, int x2, int y2) {
		 gc.setStroke(colFromChar('k'));					// set the stroke colour
		 gc.setLineWidth(3);
		 gc.strokeLine(x1, y1, x2, y2);		// draw line
	 }
	 
	 /**
	  * draw the arena and its contents
	  */
	 void drawBuilding() {		           
		 	gc.setFill(Color.BEIGE);
		 	gc.fillRect(0,  0,  primaryBuilding.getXSize(),  primaryBuilding.getYSize());	// clear the canvas 
			primaryBuilding.showBuilding(this);											// draw all items
			
			String s = primaryBuilding.toString();
			rightPane.getChildren().clear();											// clear rtpane
			Label l = new Label(s);													// turn string to label
			rightPane.getChildren().add(l);											// add label
		
		}
	
	private Color colFromChar(char c) {
		Color ans = Color.BLACK;
		switch(c) {
		case 'b' : 
			ans = Color.BLUE;
			break;
		case 'c' :
			ans = Color.CYAN;
			break;
		case 'g' :
			ans = Color.GREEN;
			break;
		case 'k' :
			ans = Color.BLACK;
			break;
		case 'o' :
			ans = Color.ORANGE;
			break;
		case 'p' :
			ans = Color.PINK;
			break;
		case 'r' :
			ans = Color.RED;
			break;
		case 'w' :
			ans = Color.WHITE;
			break;
		case 'y' : 
			ans = Color.YELLOW;
			break;
		}
		return ans;
	}
	
	/**
     * return as String definition of bOpt'th building
     * @param bOpt
     */
    public String buildingString () {
    	whichBuilding = 1 - whichBuilding;
    	if (whichBuilding == 1)
			return "420 400;10 10 140 60 60 60 10;140 10 240 60 180 60 10;240 10 400 60 320 60 20 ;10 90 120 180 40 90 15;120 90 280 180 160 90 10;280 90 400 180 340 90 10";
    	else
    		return "400 400;10 10 90 140 70 140 20;90 10 320 70 220 70 10;10 180 100 380 60 180 15;100 180 320 380 200 180 20;320 10 400 380 320 110 25";
    }
    
    private MenuBar setMenu() {
    	MenuBar menuBar = new MenuBar();
    	
    	Menu fileMenu = new Menu("File");
    	
    	MenuItem fileExit = new MenuItem("Exit");
    	fileExit.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			timer.stop();
    			System.exit(0);
    		}
    	});
    	
    	fileMenu.getItems().addAll(fileExit);
    	
    	Menu helpMenu = new Menu("Help");
    	
    	MenuItem helpAbout = new MenuItem("About");
    	// implement the about part
    	
    	menuBar.getMenus().addAll(fileMenu, helpMenu);
    	
    	return menuBar;
    }
    
    private HBox setButtons() {
    	
    	Button btnNewBuild = new Button("New Building");
		btnNewBuild.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        		primaryBuilding = new Building(buildingString());
		        	drawBuilding();								// then redraw arena
		       }
		    });
	    
	    Button btnStart = new Button("Start");
	    btnStart.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	timer.start();								// whose action is to start the timer
	       }
	    });

	    Button btnStop = new Button("Pause");
	    btnStop.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	timer.stop();								// and its action to stop the timer
	       }
	    });

	    	// now add these buttons + labels to a HBox
	    HBox hbox = new HBox(new Label("Config: "), btnNewBuild, 
	    		             new Label("Run: "), btnStart, btnStop);
	    return hbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// TODO Auto-generated method stub
    	this.stagePrimary= primaryStage;
    	this.stagePrimary.setTitle("Intelligent Building");
    	BorderPane bp = new BorderPane();
    	bp.setPadding(new Insets(10, 20, 10, 20));
    	
    	bp.setTop(setMenu());
    	
    	Group root = new Group();
    	Canvas canvas = new Canvas(500, 500);
    	root.getChildren().add(canvas);
    	bp.setCenter(root);
    	
    	gc = canvas.getGraphicsContext2D();
    	
    	timer = new AnimationTimer() {
    		public void handle(long cuurentNanoTime) {
    			primaryBuilding.update();
    			drawBuilding();
    		}
    	};
    	
    	rightPane = new VBox();												// set vBox on right to list items
		rightPane.setAlignment(Pos.TOP_LEFT);
		rightPane.setPadding(new Insets(5, 75, 75, 5));
 		bp.setRight(rightPane);
		  
	    bp.setBottom(setButtons());											// set bottom pane with buttons

	    Scene scene = new Scene(bp, 800, 600);								// set overall scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());

	    primaryStage.setScene(scene);
	    primaryStage.show();
	    whichBuilding = 0;
	    primaryBuilding = new Building(buildingString());
	    //showWelcome();														// set welcome message
	    drawBuilding();
    }
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
