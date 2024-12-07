import java.io.Serializable;

public class Card implements Serializable {
    //values for each card
    private char suit;
    private int value;

    //public constructor
    public Card(char suit, int value){
        //S = spades C = Clubs H = Hearts D = Diamonds
        this.suit = suit;
        //10 = 10 Jacks = 11 Queens = 12 Kings = 13 Aces = 14
        this.value = value;
    };

    //no setters needed as we don't need to set a cards value if its already initialized

    //getters
    public char getSuit(){
        return this.suit;
    }
    public int getValue(){
        return this.value;
    }
}
