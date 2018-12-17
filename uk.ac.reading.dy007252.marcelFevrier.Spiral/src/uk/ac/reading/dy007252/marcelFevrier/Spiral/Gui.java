package uk.ac.reading.dy007252.marcelFevrier.Spiral;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	
	private void drawSystem() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.stagePrimary, this.canvasSize/2);
	}
	
	public void drawItem(int x, int y, double size, char colour) {
		gc.setFill(colFromChar(colour));
		gc.fillArc(x-size, y-size, size * 2, size * 2, 0, 360, ArcType.ROUND);
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
		return buttonBar;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stagePrimary = primaryStage;
		this.stagePrimary.setTitle("Spiral");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,20,10,20));
		bp.setTop(setMenus());
		
		this.canvasSize = 500;
		
		Group root = new Group();
		Canvas canvas = new Canvas(this.canvasSize, this.canvasSize);
		root.getChildren().add(canvas);
		bp.setCenter(root);
		
		gc = canvas.getGraphicsContext2D();
		
		timer = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				//
			}
		};
		
		rightPane = new VBox();
		rightPane.setAlignment(Pos.TOP_LEFT);
		rightPane.setPadding(new Insets(5, 75, 75, 5));
		bp.setRight(rightPane);
		
		bp.setBottom(setButtons());
		
		Scene scene = new Scene(bp, 800, 600);
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
