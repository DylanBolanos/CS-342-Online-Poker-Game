import java.util.ArrayList;

//TODO edge case of kings to aces straight not implimented
public class ThreeCardLogic {
    public static int straightCheck(ArrayList<Card> hand){
        //checks if the differential of card values 1 and 2 is just one
        if(Math.abs((hand.get(0).getValue() - hand.get(1).getValue())) == 1){
            //if it is just one
            //check if the differential of card values 1 and 3 is two
            if(Math.abs((hand.get(0).getValue() - hand.get(2).getValue())) == 2) {
                return 3;
            }
            //case of 897
            else if(Math.abs((hand.get(0).getValue() - hand.get(2).getValue())) == 1){
                return 3;
            }
        }
        ////checks if the differential of card values 1 and 3 is just one
        else if(Math.abs((hand.get(0).getValue() - hand.get(2).getValue())) == 1){
            //if it is just one
            //check if the differential of card values 1 and 2 is two
            if(Math.abs((hand.get(0).getValue() - hand.get(1).getValue())) == 2){
                return 3;
            }
        }
        //throws an int that doesn't correspond with anything to skip logic
        return 73;
    }

    public static int flushCheck(ArrayList<Card> hand){
        //checks if all three cards are of the same suit
        //for loop for the first card and i+1 for the second card
        //for loop second iteration is just i+1 and i+1+1
        //every loop checks if the suits do not match each other throw an exeption
        for(int i = 0; i <= 1; i++){
            if((hand.get(i)).getSuit() != hand.get(i+1).getSuit()){
                return 73;
            }
        }
        return 4;
    }

    public static int pairCheck(ArrayList<Card> hand){
        //checks if the first cards value is equal to the second
        if(hand.get(0).getValue() == hand.get(1).getValue()){
            //checks if the first cards value is equal to the third
            if(hand.get(0).getValue() == hand.get(2).getValue()){
                //if it is then its a three of a kind
                return 2;
            }
            //if it isnt then its only a pair
            return 5;
        }
        //checks if the first cards value is equal to the third
        else if(hand.get(0).getValue() == hand.get(2).getValue()){
            //checks if the first cards value is equal to the second
            if(hand.get(0).getValue() == hand.get(1).getValue()){
                //if it is then its a three of a kind
                return 2;
            }
            //if it isnt then its only a pair
            return 5;
        }
        //finnaly check if the last two cards are equal to each other
        else if(hand.get(1).getValue() == hand.get(2).getValue()){
            //checks if the first cards value is equal to the second
            if(hand.get(0).getValue() == hand.get(1).getValue()){
                //if it is then its a three of a kind
                return 2;
            }
            //if it isnt then its only a pair
            return 5;
        }
        //if no pairs found throw an exception
        return 73;
    }

    public static int straightFlushCheck(ArrayList<Card> hand){
        //checks if there is a straight and a flush
        if(straightCheck(hand) == 3 && flushCheck(hand) == 4){
            //if there is both return a 1 for a straightFlush
            return 1;
        }
        return 73;
    }

    //evalHand will return an integer value representing the value of the hand passed in
    //0 if the hand just has a high card
    //1 for a straight flush
    //2 for three of a kind
    //3 for a straight
    //4 for a flush
    //5 for a pair
    public static int evalHand(ArrayList<Card> hand){
        if(straightFlushCheck(hand) == 1){
            return 1;
        }
        else if(pairCheck(hand) == 2){
            return 2;
        }
        else if(straightCheck(hand) == 3){
            return 3;
        }
        else if(flushCheck(hand) == 4){
            return 4;
        }
        else if(pairCheck(hand) == 5){
            return 5;
        }
        //returns 73 for not finding any rankings
        return 73;
    }

    //will return the amount won for the PairPlus bet
    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        //evaluates the hands and then will return a num based on winnings for pair plus
        switch (evalHand(hand)) {
            case 1:
                return bet * 40;
            case 2:
                return bet * 30;
            case 3:
                return bet * 6;
            case 4:
                return bet * 3;
            case 5:
                return bet;
        }
        //returns 0 because you lost all hands
        return 0;
    }

    //compareHands will compare the two hands passed in and return an integer based on which hand won:
    //0 if neither hand won
    //1 if the dealer hand won
    //2 if the player hand won
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        //holding ints for convinence
        //just holds the evaluating case for the player and dealer
        int holdDealerCase = evalHand(dealer);
        int holdPlayerCase = evalHand(player);

        //if the player has a greater value then the dealer
        //the dealer has won the round
        if(holdPlayerCase > holdDealerCase){
            return 1;
        }
        //if the players value is less then the dealer
        //the player has won the round
        else if(holdPlayerCase < holdDealerCase){
            return 2;
        }
        //if they are equal to each other then no one wins so we return 0
        return 0;
    }

    public static boolean queenHigh(ArrayList<Card> dealersHand){
        int evalDealer = ThreeCardLogic.evalHand(dealersHand);
        if(evalDealer == 73){
            //checks each card
            for(int i = 0; i < 3; i++) {
                if(dealersHand.get(i).getValue() >= 12){
                    return true;
                }
            }
            return false;
        }

        return true;
    }

}