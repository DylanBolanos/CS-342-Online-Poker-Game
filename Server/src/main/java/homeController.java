
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

public class homeController {
    Server server;

    @FXML
    TextField portText;
    @FXML
    BorderPane root;

    public void openServer() throws Exception{
        server = new Server(Integer.parseInt(portText.getText()));
        changeScene();
    }

    public void changeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/serverInfo.fxml"));
        Parent gameRoot = loader.load();  // Load the FXML content into a new Parent
        infoController controller = loader.getController();
        controller.initializeInfo(server);
        gameRoot.getStylesheets().add("/styles/style1.css");
        root.getScene().setRoot(gameRoot);
    }
}
