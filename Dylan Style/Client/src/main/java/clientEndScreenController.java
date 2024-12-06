import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class clientEndScreenController {

    @FXML
    private BorderPane root;


    Client c;

    public void quitGame(){
        Platform.exit();
        System.exit(1);
    }

    public void playAgain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/clientGame.fxml"));
        Parent gameRoot = loader.load();  // Load the FXML content into a new Parent
        //do we even need to pass it bacause if they play again it will be
        //back to this screen anyways
//        gameController controller = loader.getController();
//        controller.initializeInfo(c); // pass the client to the next controller
        gameRoot.getStylesheets().add("/styles/style1.css");
        root.getScene().setRoot(gameRoot);
    }
}
