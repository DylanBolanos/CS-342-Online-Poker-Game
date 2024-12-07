import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class gameController {

    Client c;
    //PokerInfo p;

    @FXML
    BorderPane root;

    @FXML
    TextField inputMoney;
    @FXML
    Button playerAnte;
    @FXML
    Button playerPairPlus;
    @FXML
    Button playerDeal;
    @FXML
    Button playerFold;

    @FXML
    Button playerCardOne;
    @FXML
    Button playerCardTwo;
    @FXML
    Button playerCardThree;
    @FXML
    Button dealerCardOne;
    @FXML
    Button dealerCardTwo;
    @FXML
    Button dealerCardThree;

    @FXML
    Button playAgain;
    @FXML
    Button quitGame;
    @FXML
    Label totalMoneyLabel;
    @FXML
    Label totalWinningsLabel;

    @FXML
    ListView<String> serverComLog;

    boolean greenStyle = true;

    static public ObservableList<String> serverComLogList;

    int dealButtonState = 0;

    public void killGame(){
        System.exit(0);
    }

    public void newLook(){
        root.getStylesheets().clear();
        if(greenStyle){
            root.getStylesheets().add("/styles/style2.css");
            greenStyle = false;
        } else{
            root.getStylesheets().add("/styles/style1.css");
            greenStyle = true;
        }
    }

    public void resetGame(){
        c.p.gameState = 69;
        playerDeal.setDisable(true);
        playerFold.setDisable(true);
        playerAnte.setDisable(false);
        playerPairPlus.setDisable(false);
        resetCards();
        c.p.setAnteBet(0);
        c.p.setPlayBet(0);
        c.p.setTotalMoney(100);
        c.p.setTotalWinnings(0);
        c.printToServerComLog("Game has been reset :)");

        totalMoneyLabel.setText("Total Money: $" + c.p.getTotalMoney());
        totalWinningsLabel.setText("Total Winnings: $" + c.p.getTotalWinnings());
    }
    public void initializeInfo(Client c) {
        this.c = c;
        this.c.setController(this);
        c.serverComLog = serverComLog;
        serverComLogList = c.serverComLogList;
        serverComLog.setItems(serverComLogList);

        totalMoneyLabel.setText("Total Money: $" + c.p.getTotalMoney());
        totalWinningsLabel.setText("Total Winnings: $" + c.p.getTotalWinnings());
    }

    public void sendAnte(){
        if(Integer.parseInt(inputMoney.getText()) >= 5 && Integer.parseInt(inputMoney.getText()) <= 25){
            c.p.setAnteBet(Integer.parseInt(inputMoney.getText()));
            c.p.sendingAnte = true;
            serverComLog.getItems().add("Player has Anted " + Integer.parseInt(inputMoney.getText()));
            playerAnte.setDisable(true);
            playerDeal.setDisable(false);
            c.send(c.p);
        }
        else{
            serverComLog.getItems().add("Please make an ante within 5-25");
        }
    }

    public void sendPairPlus(){
        if(Integer.parseInt(inputMoney.getText()) >= 5 && Integer.parseInt(inputMoney.getText()) <= 25) {
            c.p.setAnteBet(Integer.parseInt(inputMoney.getText()));
            serverComLog.getItems().add("Player has added a PairPlus ante of " + Integer.parseInt(inputMoney.getText()));
            c.p.sendingPairPlus = true;
            playerPairPlus.setDisable(true);
            c.send(c.p);
        }
        else{
            serverComLog.getItems().add("Please make a PairPlus within 5-25");
        }
    }

    public void sendDeal(){
        if(dealButtonState == 0){
            totalMoneyLabel.setText("Total Money: $" + c.p.getTotalMoney());
            totalWinningsLabel.setText("Total Winnings: $" + c.p.getTotalWinnings());

            c.p.gameState = 169;
            playerDeal.setDisable(false);
            playerPairPlus.setDisable(true);
            c.send(c.p);
            dealButtonState = 1;
            playerDeal.setText("See Cards");
        } else if (dealButtonState == 1){
            totalMoneyLabel.setText("Total Money: $" + c.p.getTotalMoney());
            totalWinningsLabel.setText("Total Winnings: $" + c.p.getTotalWinnings());

            changeCards();
            playerDeal.setText("Play");
            serverComLog.getItems().add("Ante again to play against dealers hand");
            playerFold.setDisable(false);
            dealButtonState = 2;
        } else if (dealButtonState == 2){
            totalMoneyLabel.setText("Total Money: $" + c.p.getTotalMoney());
            totalWinningsLabel.setText("Total Winnings: $" + c.p.getTotalWinnings());

            playerDeal.setDisable(true);
            serverComLog.getItems().add("Player has anted " + c.p.getAnteBet() + " to play against dealer");
            showDealerHand();
            c.p.gameState = 269;
            c.send(c.p);
            dealButtonState = 3;

            //show new game options
            playAgain.setVisible(true);
            //quitGame.setVisible(true);
            playerFold.setDisable(true);

        }

    }


    public void sendFold(){
        //im going to impliment it to where you still gotta click through and
        //its going to act as if you would play out the hand so you can see
        //if the fold was correct or not
        //so basically forget ur ante bet so you dont get any money and click through
        c.p.gameResult = 1;
        c.p.fold = true;
        //correct bool?
        //c.p.sendingAnte = true;
        serverComLog.getItems().add("Player has folded");
        serverComLog.getItems().add("Player will not receive any winnings");
        c.send(c.p);

        playerFold.setDisable(true);
        playerDeal.setDisable(true);
        playAgain.setVisible(true);
    }

    public void playAgain() throws IOException {
        //assume that they want to play again so reset game
        playAgain.setVisible(false);
        quitGame.setVisible(false);
        playerFold.setDisable(true);
        dealButtonState = 0;
        playerDeal.setText("Deal");
        playerDeal.setDisable(true);
        playerAnte.setDisable(false);
        playerPairPlus.setDisable(false);

        showAll();
        resetCards();


        //here switch up the screens after play again has run
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/clientEndScreen.fxml"));
        Parent holdLoader = loader.load();  // Load the FXML content into a new Parent
        //do we even need to pass it bacause if they play again it will be
        //back to this screen anyways
        clientEndScreenController controller = loader.getController();
        controller.initializeInfo(c, c.p.getTotalWinnings(), c.p.gameResult, greenStyle); // pass the client to the next controller
        if(greenStyle){
            holdLoader.getStylesheets().add("/styles/style1.css");
        } else if (!greenStyle){
            holdLoader.getStylesheets().add("/styles/style2.css");
        }

        c.p.setAnteBet(0);
        c.p.setPlayBet(0);
        c.p.gameState = 369;
        System.out.println("369");
        c.send(c.p);

        root.getScene().setRoot(holdLoader);
    }

    public void quitGame(){
        Platform.exit();
        System.exit(1);
    }

    public void hideAll(){
        playerCardOne.setVisible(false);
        playerCardTwo.setVisible(false);
        playerCardThree.setVisible(false);
        dealerCardOne.setVisible(false);
        dealerCardTwo.setVisible(false);
        dealerCardThree.setVisible(false);
        playerAnte.setVisible(false);
        playerPairPlus.setVisible(false);
        playerDeal.setVisible(false);
        playerFold.setVisible(false);
        inputMoney.setVisible(false);
    }

    public void showAll(){
        playerCardOne.setVisible(true);
        playerCardTwo.setVisible(true);
        playerCardThree.setVisible(true);
        dealerCardOne.setVisible(true);
        dealerCardTwo.setVisible(true);
        dealerCardThree.setVisible(true);
        playerAnte.setVisible(true);
        playerPairPlus.setVisible(true);
        playerDeal.setVisible(true);
        playerFold.setVisible(true);
        inputMoney.setVisible(true);

    }

    public void resetCards(){
        playerCardOne.setText("card1");
        playerCardTwo.setText("card2");
        playerCardThree.setText("card3");
        dealerCardOne.setText("card1");
        dealerCardTwo.setText("card2");
        dealerCardThree.setText("card3");
    }

    public void changeCards(){
        ArrayList<Card> playerHand = c.p.getPlayerHand();
        playerCardOne.setText(formatCard(playerHand.get(0)));
        playerCardTwo.setText(formatCard(playerHand.get(1)));
        playerCardThree.setText(formatCard(playerHand.get(2)));
    }

    public void showDealerHand(){
        ArrayList<Card> dealerHand = c.p.getDealerHand();

        dealerCardOne.setText(formatCard(dealerHand.get(0)));
        dealerCardTwo.setText(formatCard(dealerHand.get(1)));
        dealerCardThree.setText(formatCard(dealerHand.get(2)));
    }




    private String formatCard (Card inCard){
        if (inCard.getSuit() == 'S') {
            if(inCard.getValue() > 10){
                switch(inCard.getValue()){
                    case 11:
                        return "Jack of Spades";
                    case 12:
                        return "Queen of Spades";
                    case 13:
                        return "King of Spades";
                    case 14:
                        return "Ace of Spades";
                }
            }
            return (inCard.getValue() + " of Spades");
        }
        else if (inCard.getSuit() == 'D') {
            if(inCard.getValue() > 10){
                switch(inCard.getValue()){
                    case 11:
                        return "Jack of Diamonds";
                    case 12:
                        return "Queen of Diamonds";
                    case 13:
                        return "King of Diamonds";
                    case 14:
                        return "Ace of Diamonds";
                }
            }
            return (inCard.getValue() + " of Diamonds");
        }
        else if (inCard.getSuit() == 'H') {
            if(inCard.getValue() > 10){
                switch(inCard.getValue()){
                    case 11:
                        return "Jack of Hearts";
                    case 12:
                        return "Queen of Hearts";
                    case 13:
                        return "King of Hearts";
                    case 14:
                        return "Ace of Hearts";
                }
            }
            return (inCard.getValue() + " of Hearts");
        }
        else if (inCard.getSuit() == 'C') {
            if(inCard.getValue() > 10){
                switch(inCard.getValue()){
                    case 11:
                        return "Jack of Clubs";
                    case 12:
                        return "Queen of Clubs";
                    case 13:
                        return "King of Clubs";
                    case 14:
                        return "Ace of Clubs";
                }
            }
            return (inCard.getValue() + " of Clubs");
        }
        assert(false);
        return "Should be dead code";
    }
}
