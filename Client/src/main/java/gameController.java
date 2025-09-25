import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
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

public class gameController {

    Client c;
    //PokerInfo p;

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
    ListView<String> serverComLog;
    static public ObservableList<String> serverComLogList;

    int dealButtonState = 0;

    public void initializeInfo(Client c) {
        this.c = c;
        this.c.setController(this);
        //p = c.p;
        //c.send(p);
        c.serverComLog = serverComLog;
        serverComLogList = c.serverComLogList;
        serverComLog.setItems(serverComLogList);
    }

    public void sendAnte(){
        if(Integer.parseInt(inputMoney.getText()) >= 5 && Integer.parseInt(inputMoney.getText()) <= 25){
            c.p.setAnteBet(Integer.parseInt(inputMoney.getText()));
            c.p.sendingAnte = true;
            playerAnte.setDisable(true);
            playerDeal.setDisable(false);
            c.send(c.p);
        }
    }

    public void sendPairPlus(){
        c.p.setAnteBet(Integer.parseInt(inputMoney.getText()));
        c.p.sendingPairPlus = true;
        playerPairPlus.setDisable(true);
        c.send(c.p);
    }

    public void sendDeal(){
        if(dealButtonState == 0){
            c.p.gameState = 169;
            playerDeal.setDisable(false);
            playerPairPlus.setDisable(true);
            c.send(c.p);
            dealButtonState = 1;
            playerDeal.setText("See Cards");
        } else if (dealButtonState == 1){
            changeCards();
            playerDeal.setText("Play");
            dealButtonState = 2;
        } else if (dealButtonState == 2){
            playerDeal.setDisable(true);
            showDealerHand();
            c.p.gameState = 269;
            //implement logic for playing the game THIS IS WHERE I AM LEAVING OFF FOR NOW
            c.send(c.p);
            dealButtonState = 3;


            // hide all objects and ask if they wanna play or quit
            //hideAll();
            // show new game options
            playAgain.setVisible(true);
            quitGame.setVisible(true);
        }

    }

    public void playAgain(){
        playAgain.setVisible(false);
        quitGame.setVisible(false);
        dealButtonState = 0;
        playerDeal.setText("Deal");
        playerDeal.setDisable(true);
        playerAnte.setDisable(false);
        playerPairPlus.setDisable(false);

        showAll();
        resetCards();
        c.p.setAnteBet(0);
        c.p.setPlayBet(0);
        c.p.gameState = 369;
        System.out.println("369");
        c.send(c.p);
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
//        System.out.println(formatCard(playerHand.get(0)));
//        System.out.println(formatCard(playerHand.get(1)));
//        System.out.println(formatCard(playerHand.get(2)));

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
//
//    public void test(){
//        System.out.println("TRYING TO CONNECT TO MFER");
//        c = new Client(null, "127.0.0.1", 5555);
//        c.start();
//        System.out.println("CONNECTED NWORDS");
//
//    }
//
//    public void test1(){
//        c.send(c.p);
//        System.out.println("SENT POKER INFO");
//    }

}

