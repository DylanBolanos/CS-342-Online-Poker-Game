import java.io.Serializable;
import java.util.ArrayList;

//Player is not able to "get dealt a hand" when being initialized
//so we will fix this in the initialization of the game
//when a dealer is made and the players initialized
//that's when the dealer will deal a hand
public class Player implements Serializable {

    ArrayList<Card> hand;
    private int totalMoney;
    private int anteBet;
    private int prevAnte;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;

    public Player(){
        totalMoney = 100;
    }

    //Setters for all the variables
    public void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
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
    public void addTotalWinnings(int totalWinnings) {
        this.totalWinnings += totalWinnings;
    }
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    public void addPrevAnte(int a) { prevAnte += a; }
    public void setPrevAnte(int a) { prevAnte = a; }
    public int getPrevAnte() { return prevAnte; }

    //getters for all the variables
    public int getTotalMoney() { return totalMoney;}
    public void setTotalMoney(int money) {totalMoney = money;}
    public int addTotalMoney(int money) {totalMoney += money; return totalMoney;}
    public int getAnteBet() {
        return anteBet;
    }
    public void addAnteBet(int anteBet) { this.anteBet += anteBet; }
    public int getPlayBet() {
        return playBet;
    }
    public int getPairPlusBet() {
        return pairPlusBet;
    }
    public int getTotalWinnings() {
        return totalWinnings;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
}
