import java.io.Serializable;
import java.util.ArrayList;

class PokerInfo implements Serializable {
    ArrayList<Card> playerHand;
    ArrayList<Card> dealerHand;
    private int totalMoney;
    private int anteBet;
    private int prevAnte;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;


    boolean sendingAnte = false;
    boolean sendingPairPlus = false;

    int gameState;
    int gameResult;

    public PokerInfo(){
        totalMoney = 100;
        gameState = 69;
    }
    //Setters
    public void setGameState(int gameState){
        this.gameState = gameState;
    }
    public void setPlayerHand(ArrayList<Card> hand) {
        this.playerHand = hand;
    }
    public void setDealerHand(ArrayList<Card> hand) {
        this.dealerHand = hand;
    }
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
    public void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
    }
    public void setPrevAnte(int prevAnte) {
        this.prevAnte = prevAnte;
    }
    public void setPlayBet(int playBet) {
        this.playBet = playBet;
    }
    public void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }
    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    //Getters
    public int getGameState() {
        return gameState;
    }
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }
    public int getTotalMoney() {
        return totalMoney;
    }
    public int getAnteBet() {
        return anteBet;
    }
    public int getPrevAnte() {
        return prevAnte;
    }
    public int getPlayBet() {
        return playBet;
    }
    public int getPairPlusBet() {
        return pairPlusBet;
    }
    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void addTotalMoney(int money){
        totalMoney += money;
    }

    public void addTotalWinning(int money){
        totalWinnings += money;
    }
}