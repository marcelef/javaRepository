package uk.ac.reading.dy007252.marcelFevrier.GuiProjectJavaFx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World");
		Scene scene = new Scene(new Group(new Text(25, 25, "Hello World")));
		primaryStage.setScene(scene);
		primaryStage.setWidth(250);
		primaryStage.setHeight(100);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
