import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class clientEndScreenController {

    @FXML
    private BorderPane root;


    Client c;

    @FXML
    Label amountWonText;
    @FXML
    Label winOrLoser;

    int amountWonOrLost;
    int gameResult;
    boolean style;

    public void quitGame(){
        Platform.exit();
        System.exit(1);
    }

    public void playAgain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/clientGame.fxml"));
        Parent gameRoot = loader.load();  // Load the FXML content into a new Parent
        gameController controller = loader.getController();
        controller.initializeInfo(c); // pass the client to the next controller
        if(style){
            gameRoot.getStylesheets().add("/styles/style1.css");
        } else {
            gameRoot.getStylesheets().add("/styles/style2.css");
        }
        root.getScene().setRoot(gameRoot);
    }

    public void initializeInfo(Client c, int a, int g, boolean style) {
        this.c = c;
        amountWonOrLost = a;
        gameResult = g;
        this.style = style;

        if(g == 2){ // winner
            winOrLoser.setText("WIN");
            amountWonText.setText("You Won: " + amountWonOrLost + " So Far");
        } else if(g == 1){
            winOrLoser.setText("LOSE");
            amountWonText.setText("You Lost: " + amountWonOrLost + " So Far");
        } else if(g == 0){
            winOrLoser.setText("TIED");
            amountWonText.setText("You didn't win or lose money!");
        }
    }

}
