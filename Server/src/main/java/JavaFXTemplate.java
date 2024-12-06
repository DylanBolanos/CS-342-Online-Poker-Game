
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;
import java.awt.*;
import javafx.fxml.FXMLLoader;

public class JavaFXTemplate extends Application{

//	TextField s1,s2,s3,s4, c1;
//	Button serverChoice, b1;
//	HashMap<String, Scene> sceneMap;
//	HBox buttonBox;
//	ListView<String> listItems;
//
//	Scene startScene;
//	BorderPane startPane;
//	Server serverConnection;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Server Side");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/serverHome.fxml"));
		root.getStylesheets().add("/styles/style1.css");//set style
		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
//		primaryStage.setTitle("The Networked Server GUI Example");
//
//		// Create the Server button
//		this.serverChoice = new Button("Server");
//		this.serverChoice.setStyle("-fx-pref-width: 300px");
//		this.serverChoice.setStyle("-fx-pref-height: 300px");
//
//		// Set up action for the Server button
//		this.serverChoice.setOnAction(e -> {
//			primaryStage.setScene(sceneMap.get("server"));
//			primaryStage.setTitle("This is the Server");
//			serverConnection = new Server(data -> {
//				Platform.runLater(() -> {
//					listItems.getItems().add(data.toString());
//				});
//			});
//		});
//
//		// Set up the button layout
//		this.buttonBox = new HBox(400, serverChoice);
//		startPane = new BorderPane();
//		startPane.setPadding(new Insets(70));
//		startPane.setCenter(buttonBox);
//
//		// Initial scene
//		startScene = new Scene(startPane, 800, 800);
//
//		// Create ListView for the server
//		listItems = new ListView<String>();
//
//		// Set up the scene map for the server
//		sceneMap = new HashMap<String, Scene>();
//		sceneMap.put("server", createServerGui());
//
//		// Set up window close behavior
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent t) {
//				Platform.exit();
//				System.exit(0);
//			}
//		});
//
//		// Set the initial scene and show the stage
//		primaryStage.setScene(startScene);
//		primaryStage.show();
	}

	// Create the server GUI layout
//	public Scene createServerGui() {
//		BorderPane pane = new BorderPane();
//		pane.setPadding(new Insets(70));
//		pane.setStyle("-fx-background-color: coral");
//
//		// Add the ListView to the center of the pane
//		pane.setCenter(listItems);
//
//		return new Scene(pane, 500, 400);
//	}
}
