package uk.ac.reading.dy007252.marcelFevrier.Spiral;

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
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Gui extends Application {
	
	private Stage stagePrimary;
	private GraphicsContext gc;
	private AnimationTimer timer;
	private VBox rightPane;
	private double canvasSize;
	private OrbitSystem system;
	
	private void drawSystem() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0.0, 0.0, this.canvasSize, this.canvasSize);
		system.showSystem(this);
	}
	
	public void drawItem(int x, int y, double size, char colour) {
		gc.setFill(colFromChar(colour));
		gc.fillArc(x-size, y-size, size * 2, size * 2, 0, 360, ArcType.ROUND);
	}
	
	void showLine (int x1, int y1, int x2, int y2, double width, char colour) {
		 gc.setStroke(colFromChar(colour));					// set the stroke colour
		 gc.setLineWidth(width);
		 gc.strokeLine(x1, y1, x2, y2);		// draw line
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
		case 'd' :
			ans = Color.DARKGRAY;
			break;
		case 'a' :
			ans = Color.GRAY;
			break;
		}
		return ans;
	}
	
	private MenuBar setMenus() {
		MenuBar menuBar = new MenuBar();
		return menuBar;
	}
	
	private ButtonBar setButtons() {
		ButtonBar buttonBar = new ButtonBar();
		
		Button startBtn = new Button("Start");
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.start();
			}
		});
		
		Button pauseBtn = new Button("Pause");
		pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop();
			}
		});
		
		buttonBar.getButtons().addAll(startBtn, pauseBtn);
		
		return buttonBar;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stagePrimary = primaryStage;
		this.stagePrimary.setTitle("Spiral");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,20,10,20));
		bp.setTop(setMenus());
		
		system = new OrbitSystem();
		system.standardSetUp();
		
		this.canvasSize = 700;
		
		Group root = new Group();
		Canvas canvas = new Canvas(this.canvasSize, this.canvasSize);
		root.getChildren().add(canvas);
		bp.setCenter(root);
		
		gc = canvas.getGraphicsContext2D();
		
		final long startNanoTime = System.nanoTime();
		
		timer = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - startNanoTime) / 10000000.0;
				system.update(t);
				drawSystem();
			}
		};
		
		rightPane = new VBox();
		rightPane.setAlignment(Pos.TOP_LEFT);
		rightPane.setPadding(new Insets(5, 75, 75, 5));
		bp.setRight(rightPane);
		
		bp.setBottom(setButtons());
		
		Scene scene = new Scene(bp, 1000, 800);
		scene.fillProperty().set(Color.DARKGRAY);
		bp.prefHeightProperty().bind(scene.heightProperty());
		bp.prefWidthProperty().bind(scene.widthProperty());
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		this.drawSystem();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
