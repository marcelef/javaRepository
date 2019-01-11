package uk.ac.reading.dy007252.marcelFevrier.MajorProject;



import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import javax.lang.model.type.NullType;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class BuildingGui extends Application {
	
	private Stage stagePrimary;
	
	private Building primaryBuilding;
	private VBox rightPane;
	private ScrollPane rightScrollPane;
	private GraphicsContext gc;
	private AnimationTimer timer;
	private int whichBuilding;
	
	private double contextMenuX;
	private double contextMenuY;
	
	private int wallThickness;
	
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
		 gc.setStroke(colFromChar(GuiColour.BLACK.getValue()));					// set the stroke colour
		 gc.setLineWidth(wallThickness);
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
		switch(c) {
		case 'b' : 
			return Color.BLUE;
		case 'c' :
			return Color.CYAN;
		case 'g' :
			return Color.GREEN;
		case 'k' :
			return Color.BLACK;
		case 'o' :
			return Color.ORANGE;
		case 'p' :
			return Color.PINK;
		case 'r' :
			return Color.RED;
		case 'w' :
			return Color.WHITE;
		case 'y' : 
			return Color.YELLOW;
		}
		return Color.BLACK;
	}
	
	private String openSingleInputDialog(String title, String header, String lableTxt) {
		DialogCapture capture = new DialogCapture();
		
		Dialog<DialogCapture> dialog = new Dialog<DialogCapture>();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setWidth(100);
		dialog.setHeight(500);
		dialog.setResizable(false);
		
		Label lbl = new Label(lableTxt);
		TextField txtField = new TextField();
		
		ButtonType doneBtn = new ButtonType("Done", ButtonData.OK_DONE);
		
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == doneBtn) {
				capture.add(txtField.getText());
				return capture;
			}
			return null;
		});
		
		GridPane grid = new GridPane();
		grid.addRow(1, lbl, txtField);
		
		dialog.getDialogPane().getButtonTypes().addAll(doneBtn,  ButtonType.CLOSE);
		dialog.getDialogPane().setContent(grid);
		
		Optional<DialogCapture> result = dialog.showAndWait();
		
		return result.get().readNext();
	}
	
	private void errorAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		
		alert.showAndWait();
	}
	
	private DialogCapture getSizeAndColourValues() {
		DialogCapture capture = new DialogCapture();
		
		Dialog<DialogCapture> dialog = new Dialog<DialogCapture>();
		dialog.setTitle("Add New Round Table");
		dialog.setHeaderText("Enter the size and colour of the new table.");
		dialog.setWidth(100);
		dialog.setHeight(500);
		dialog.setResizable(false);
		
		Label sizeLbl = new Label("Size");
		Label colourLbl = new Label("Colour");
		TextField sizeTxt = new TextField("14");
		
		ComboBox<String> coloursList = new ComboBox<String>();
		coloursList.getItems().addAll(
				"BLACK",
				"BLUE",
				"CYAN",
				"GREEN",
				"RED",
				"PINK",
				"ORANGE",
				"WHITE",
				"YELLOW");
		
		coloursList.getSelectionModel().selectFirst();
		
		GridPane grid = new GridPane();
		
		grid.add(sizeLbl, 1, 1);
		grid.add(sizeTxt, 2, 1);
		grid.add(colourLbl, 1, 2);
		grid.add(coloursList, 2, 2);
		
		
		ButtonType doneBtn = new ButtonType("Done", ButtonData.OK_DONE);
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == doneBtn) {
		        capture.add(sizeTxt.getText());
		        capture.add(Character.toString(GuiColour.reverse(coloursList.getValue())));
		        return capture;
		    }
		    if (dialogButton == ButtonType.CANCEL) {
		    	capture.clearCapture();
		    	return capture;
		    }
		    return null;
		});
		
		dialog.getDialogPane().getButtonTypes().addAll(doneBtn, ButtonType.CANCEL);
		dialog.getDialogPane().setContent(grid);
		Optional<DialogCapture> result = dialog.showAndWait();
		
		return result.get();
	}
	
	private void addNewPerson(double x, double y) {
		primaryBuilding.addNewPerson(this, (int) x, (int) y);
	}
	
	private void addNewPersonRandom() {
		primaryBuilding.addNewPerson(this);
	}
	
	private void addNewRoundObject(double x, double y) {
		DialogCapture capture = getSizeAndColourValues();
		
		if (!capture.isEmpty()) {
			primaryBuilding.createNewRoundTable(this, (int) x, (int) y, Double.parseDouble(capture.get(0)), capture.get(1).charAt(0));
		}
	}
	
	private void removeAllOccupants() {
		primaryBuilding.removeAllOccupants(this);
	}
	
	private String defineNewBuilding() {
		DialogCapture capture = new DialogCapture();
		
		Dialog<DialogCapture> dialog = new Dialog<DialogCapture>();
		dialog.setHeaderText("Enter the definitions for the new building");
		
		Label widthLbl = new Label("Width: ");
		Label heightLbl = new Label("Height: ");
		Label RoomLbl = new Label("Rooms");
		Label firstCornerXLbl = new Label("Corner 1 x: ");
		Label firstCornerYLbl = new Label("Corner 1 y: ");
		Label secondCornerXLbl = new Label("Corner 2 x: ");
		Label secondCornerYLbl = new Label("Corner 2 y: ");
		Label doorXLbl = new Label("Door x: ");
		Label doorYLbl = new Label("Door y: ");
		Label doorSizeLbl = new Label("Door Size: ");
		
		TextField widthTxt = new TextField("");
		TextField heightTxt = new TextField(""); 
		TextField firstCornerXTxt = new TextField("");
		TextField firstCornerYTxt = new TextField("");
		TextField secondCornerXTxt = new TextField("");
		TextField secondCornerYTxt = new TextField("");
		TextField doorXTxt = new TextField("");
		TextField doorYTxt = new TextField("");
		TextField doorSizeTxt = new TextField("");
		
		GridPane grid = new GridPane();
		
		grid.add(widthLbl, 1, 1);
		grid.add(widthTxt, 2, 1);
		
		grid.add(heightLbl, 1, 2);
		grid.add(heightTxt, 2, 2);
		
		grid.add(RoomLbl, 1, 3);
		
		grid.add(firstCornerXLbl, 1, 4);
		grid.add(firstCornerXTxt, 2, 4);
		
		grid.add(firstCornerYLbl, 1, 5);
		grid.add(firstCornerYTxt, 2, 5);
		
		grid.add(secondCornerXLbl, 1, 6);
		grid.add(secondCornerXTxt, 2, 6);
		
		grid.add(secondCornerYLbl, 1, 7);
		grid.add(secondCornerYTxt, 2, 7);
		
		grid.add(doorXLbl, 1, 8);
		grid.add(doorXTxt, 2, 8);
		
		grid.add(doorYLbl, 1, 9);
		grid.add(doorYTxt, 2, 9);

		grid.add(doorSizeLbl, 1, 10);
		grid.add(doorSizeTxt, 2, 10);
		
		ButtonType doneBtn = new ButtonType("Done", ButtonData.OK_DONE);
		
		Button addRoomBtn = new Button("Add Room");
		addRoomBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		        capture.add(firstCornerXTxt.getText());
		        capture.add(firstCornerYTxt.getText());
		        capture.add(secondCornerXTxt.getText());
		        capture.add(secondCornerYTxt.getText());
		        capture.add(doorXTxt.getText());
		        capture.add(doorYTxt.getText());
		        capture.add(doorSizeTxt.getText());
		        
		        firstCornerXTxt.clear();
		        firstCornerYTxt.clear();
		        secondCornerXTxt.clear();
		        secondCornerYTxt.clear();
		        doorXTxt.clear();
		        doorYTxt.clear();
		        doorSizeTxt.clear();
			}
		});
		
		grid.add(addRoomBtn, 2, 11);
		
		dialog.getDialogPane().getButtonTypes().addAll(doneBtn, ButtonType.CANCEL);
		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == doneBtn) {
		        capture.addAt(0, widthTxt.getText());
		        capture.addAt(1, heightTxt.getText());
		        return capture;
		    }
		    if (dialogButton == ButtonType.CANCEL) {
		    	capture.clearCapture();
		    	return capture;
		    }
		    return null;
		});
		
		Optional<DialogCapture> result = dialog.showAndWait();
		
		if (!result.get().isEmpty()) {
			return result.get().covertToBuilding();
		} else {
			return null;
		}
	}
	
	private void clearCanvas() {
		gc.setFill(Color.BEIGE);
	 	gc.fillRect(0,  0,  500,  500);
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
    	
    	Menu addMenu = new Menu("Add");
    	
    	MenuItem addPerson = new MenuItem("Add Random Person");
    	addPerson.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			addNewPersonRandom();
    		}
    	});
    	
    	addMenu.getItems().add(addPerson);
    	
    	Menu removeMenu = new Menu("Remove");
    	
    	MenuItem removeAllOccupants = new MenuItem("Remove All Occupants");
    	removeAllOccupants.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			removeAllOccupants();
    			drawBuilding();
    		}
    	});
    	
    	removeMenu.getItems().addAll(removeAllOccupants);
    	
    	Menu settingsMenu = new Menu("Settings");
    	
    	MenuItem wallThicknessSetting = new MenuItem("Wall Thickness");
    	wallThicknessSetting.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					String res = openSingleInputDialog("Wall Thickness", "Enter the thickness of the wall. The default value is 3.", "Thickness");
					wallThickness = Integer.parseInt(res);
					drawBuilding();
				} catch (Exception e) {
					errorAlert(" Error - Invalid Input", "Invalid input value. Value must be an integer.\n\n" + e);
				}
			}
    	});
    	
    	settingsMenu.getItems().addAll(wallThicknessSetting);
    	
    	menuBar.getMenus().addAll(fileMenu, helpMenu, addMenu, removeMenu, settingsMenu);
    	
    	return menuBar;
    }
    
    private HBox setButtons() {
    	
    	Button btnSwapBuild = new Button("Swap Building");
		btnSwapBuild.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        		primaryBuilding = new Building(buildingString());
		        	drawBuilding();								// then redraw arena
		       }
		    });
		
		Button btnCreateNewBuild = new Button("Create New Building");
		btnCreateNewBuild.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				String newBuild = defineNewBuilding();
				if (newBuild != null) {
					primaryBuilding = new Building(defineNewBuilding());
					clearCanvas();
					drawBuilding();
				}
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
	    HBox hbox = new HBox(new Label("Config: "), btnSwapBuild, btnCreateNewBuild, 
	    		             new Label("Run: "), btnStart, btnStop);
	    return hbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    	wallThickness = 3;
    	
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
    		public void handle(long curentNanoTime) {
    			primaryBuilding.update();
    			drawBuilding();
    		}
    	};
    	
    	rightPane = new VBox();												// set vBox on right to list items
    	rightScrollPane = new ScrollPane();
    	rightScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		rightPane.setAlignment(Pos.TOP_LEFT);
		rightPane.setPadding(new Insets(5, 75, 75, 5));
		rightScrollPane.setContent(rightPane);
 		bp.setRight(rightScrollPane);
		  
	    bp.setBottom(setButtons());											// set bottom pane with buttons
	    
	    // CONTEXT MENU
	    
	    ContextMenu contextMenu = new ContextMenu();
	    
        MenuItem addPersonItem = new MenuItem("Add Person");
        addPersonItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addNewPerson(contextMenuX, contextMenuY);
            }
        });
        
        MenuItem addRoundTableItem = new MenuItem("Add Round Table");
        addRoundTableItem.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		addNewRoundObject(contextMenuX, contextMenuY);
        	}
        });
        
        MenuItem closeContextMenuItem = new MenuItem("Cancel");
        closeContextMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				contextMenu.hide();
			}
        });
 
        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(addPersonItem, addRoundTableItem, closeContextMenuItem);
        
        canvas.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
            	contextMenuX = event.getX();
            	contextMenuY = event.getY();
            	contextMenu.show(canvas, event.getScreenX(), event.getScreenY());
            }
        });
	    
	    

	    Scene scene = new Scene(bp, 900, 600);								// set overall scene
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
