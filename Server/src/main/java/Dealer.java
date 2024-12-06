import java.util.ArrayList;

public class Dealer {
    //The no arg constructor will initialize theDeck
    Deck theDeck = new Deck();

    //The data member dealersHand will hold the dealers hand in each game
    ArrayList<Card> dealersHand;

    //Checks if the dealer has at least a high queen
    //consructor for dealer
    //sets his hand to a new hand
    public Dealer() {
        newDealerHand();
    }

    public void newDealerHand(){
        theDeck.newDeck();
        dealersHand = dealHand();
    }

    //dealHand() will return an ArrayList<Card> of three cards removed from theDeck
    public ArrayList<Card> dealHand(){
        //holding array to return later
        ArrayList<Card> returnHand = new ArrayList<>();
        //assuming the list is shuffled go ahead and just get the first 3 values
        //removing them as we grab cards
        for(int i = 0; i < 3; i++) {
            returnHand.add(theDeck.get(0));
            theDeck.removeCard(0);
        }
        return returnHand;
    }

    //Getters
    public ArrayList<Card> getDealersHand(){
        return dealersHand;
    }
    public ArrayList<Card> getTheDeck(){
        return theDeck;
    }

//    public boolean queenHigh(ArrayList<Card> dealersHand){
//        int evalDealer = ThreeCardLogic.evalHand(dealersHand);
//        //there is no valid ranks
//        if(evalDealer == 73){
//            //checks each card
//            for(int i = 0; i < 3; i++) {
//                if(dealersHand.get(i).getValue() >= 12){
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        return true;
//    }
}