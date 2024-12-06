
import java.io.Serializable;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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

public class JavaFXTemplate extends Application {

	// Initialize the TextField and Button
	TextField c1;
	Button clientChoice, b1, b2;
	HashMap<String, Scene> sceneMap;
	HBox buttonBox;
	ListView<String> listItems2;

	Scene startScene;
	BorderPane startPane;
	Client clientConnection;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Client Side");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/clientHome.fxml"));
		root.getStylesheets().add("/styles/style1.css");//set style
		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
//		primaryStage.setTitle("The Networked Client GUI Example");
//
//		// Create and initialize the Client button
//		this.clientChoice = new Button("Client");
//		this.clientChoice.setStyle("-fx-pref-width: 300px");
//		this.clientChoice.setStyle("-fx-pref-height: 300px");
//
//		// Set up action for the Client button
//		this.clientChoice.setOnAction(e -> {
//			primaryStage.setScene(sceneMap.get("client"));
//			primaryStage.setTitle("This is a client");
//			clientConnection = new Client(data -> {
//				Platform.runLater(() -> {
//					listItems2.getItems().add(data.toString());
//				});
//			});
//			clientConnection.start();
//		});
//
//		// Initialize the TextField (c1) and Button (b1)
//		c1 = new TextField();
//		b1 = new Button("Send");
//		b2 = new Button("Poker");
//		// Set up action for the Send button (b1)
//		b1.setOnAction(e -> {
//			if (clientConnection != null) {
//				clientConnection.send(c1.getText()); // on send button press we are using .send with the message in the textfield
//				c1.clear();
//			}
//		});
//		b2.setOnAction(e -> {
//			if (clientConnection != null) {
//				clientConnection.send(clientConnection.p);
//				//clientConnection.send(c1.getText()); // on send button press we are using .send with the message in the textfield
//				//c1.clear();
//			}
//		});
//
//		// Set up the button layout (only the Client button)
//		this.buttonBox = new HBox(400, clientChoice);
//		startPane = new BorderPane();
//		startPane.setPadding(new Insets(70));
//		startPane.setCenter(buttonBox);
//
//		// Initial scene
//		startScene = new Scene(startPane, 800, 800);
//
//		// Create ListView for the client
//		listItems2 = new ListView<String>();
//
//		// Set up the scene map for the client
//		sceneMap = new HashMap<String, Scene>();
//		sceneMap.put("client", createClientGui());
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
//	}
//
//	// Create the client GUI layout
//	public Scene createClientGui() {
//		VBox clientBox = new VBox(10, c1, b1, b2, listItems2);
//		clientBox.setStyle("-fx-background-color: blue");
//		return new Scene(clientBox, 400, 300);
	}


}