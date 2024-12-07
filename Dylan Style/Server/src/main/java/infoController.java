
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

public class infoController {

    Server server;

    @FXML
    ListView<String> serverInfo;
    static public ObservableList<String> serverInfoList;
    @FXML
    Button numClients;

    public void initializeInfo(Server server) {
      this.server = server;
      server.serverInfo = serverInfo;
      serverInfoList = Server.serverInfoList;
      serverInfo.setItems(server.serverInfoList);

    }

    public void killServer(){
        Platform.exit();
        System.exit(0);
    }

}
