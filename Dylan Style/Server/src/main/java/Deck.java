import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    public Deck(){ // constructor that will add all 52 card to the deck and shuffle it
        newDeck();
    }

    public void newDeck(){
        this.clear();
        addSuit('H');
        addSuit('S');
        addSuit('D');
        addSuit('C');
        shuffleDeck();
    }

    public void shuffleDeck(){ // will shuffle the deck
        Collections.shuffle(this);
    }

    private void addSuit(char suit){ // will add an entire suit to the deck
        for(int i = 2; i <= 14; i++){
            this.add(new Card(suit, i));
        }
    }

    public void printDeck(){ // will print the entire deck using the printCard method
        for(int i = 0; i < 52; i++){
            printCard(i);
        }
    }

    public void printCard(int index){ // check to see what the card at a given index is and prints it out
        if(this.get(index).getSuit() == 'H'){
            System.out.println(this.get(index).getValue() + " of Hearts");
        } else if(this.get(index).getSuit() == 'S'){
            System.out.println(this.get(index).getValue() + " of Spades");
        } else if(this.get(index).getSuit() == 'D'){
            System.out.println(this.get(index).getValue() + " of Diamonds");
        } else if(this.get(index).getSuit() == 'C'){
            System.out.println(this.get(index).getValue() + " of Clubs");
        }
    }

    public Card removeCard(int index) { // removes a card at a given index
        Card c = this.get(index);
        this.remove(index);
        return c;
    }

}

