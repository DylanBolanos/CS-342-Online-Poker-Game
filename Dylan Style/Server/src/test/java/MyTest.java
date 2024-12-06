import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MyTest {
	//test cases using the pokerinfo class ante bets and setting the hands

	@Test
	public void straightCheckTest() {
		PokerInfo holdInfo =  new PokerInfo();
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('D',7));
		holdHand.add(1,new Card('S',8));
		holdHand.add(2,new Card('H',9));
		holdInfo.setDealerHand(holdHand);

		assertEquals(3, ThreeCardLogic.straightCheck(holdInfo.getDealerHand()),"Straight check 7,8,9 did not pass");
	}

	@Test
	public void flushCheckHeartsTest() {
		PokerInfo holdInfo =  new PokerInfo();
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('H',10));
		holdHand.add(1,new Card('H',5));
		holdHand.add(2,new Card('H',3));
		holdInfo.setDealerHand(holdHand);

		assertEquals(4, ThreeCardLogic.flushCheck(holdInfo.getDealerHand()),"Flush check H,H,H did not pass");
	}

	@Test
	public void pairCheckTest() {
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('D',8));
		holdHand.add(1,new Card('S',8));
		holdHand.add(2,new Card('H',9));
		PokerInfo holdInfo =  new PokerInfo();
		holdInfo.setDealerHand(holdHand);

		assertEquals(5, ThreeCardLogic.pairCheck(holdInfo.getDealerHand()),"Pair check 8,8,9 did not pass");
	}


	@Test
	public void threeOfAKindCheckTest() {
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('D',10));
		holdHand.add(1,new Card('S',10));
		holdHand.add(2,new Card('H',10));
		PokerInfo holdInfo =  new PokerInfo();
		holdInfo.setDealerHand(holdHand);

		assertEquals(2, ThreeCardLogic.pairCheck(holdInfo.getDealerHand()),"Pair check for three of a kind 10,10,10 did not pass");
	}

	@Test
	public void straightFlushCheckTest() {
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('D',7));
		holdHand.add(1,new Card('D',8));
		holdHand.add(2,new Card('D',9));
		PokerInfo holdInfo =  new PokerInfo();
		holdInfo.setDealerHand(holdHand);

		assertEquals(1, ThreeCardLogic.straightFlushCheck(holdInfo.getDealerHand()),"Straight Flush check 7,8,9 did not pass");
	}

	@Test
	public void evalHandTest() {
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('D',12));
		holdHand.add(1,new Card('S',6));
		holdHand.add(2,new Card('H',12));
		PokerInfo holdInfo =  new PokerInfo();
		holdInfo.setDealerHand(holdHand);

		assertEquals(5, ThreeCardLogic.evalHand(holdInfo.getDealerHand()),"Eval hand D12,S6,H12 did not pass");
	}

	@Test
	public void evalPPWinningsTest() {
		ArrayList<Card> holdHand = new ArrayList<>();
		holdHand.add(0,new Card('H',8));
		holdHand.add(1,new Card('H',9));
		holdHand.add(2,new Card('H',10));
		PokerInfo holdInfo =  new PokerInfo();
		holdInfo.setAnteBet(20);
		holdInfo.setDealerHand(holdHand);

		assertEquals(800, ThreeCardLogic.evalPPWinnings(holdInfo.getDealerHand(),holdInfo.getAnteBet()),"Eval hand H8,H9,H10 did not pass");
	}

	@Test
	public void compareHandsTest() {
		PokerInfo holdInfo =  new PokerInfo();
		ArrayList<Card> holdPlayerHand = new ArrayList<>();
		holdPlayerHand.add(0,new Card('D',12));
		holdPlayerHand.add(1,new Card('S',6));
		holdPlayerHand.add(2,new Card('H',12));
		holdInfo.setPlayerHand(holdPlayerHand);

		ArrayList<Card> holdDealerHand = new ArrayList<>();
		holdDealerHand.add(0,new Card('D',12));
		holdDealerHand.add(1,new Card('D',6));
		holdDealerHand.add(2,new Card('D',4));
		holdInfo.setDealerHand(holdDealerHand);

		assertEquals(1, ThreeCardLogic.compareHands(holdInfo.getDealerHand(),holdInfo.getPlayerHand()),"Eval hand D12,S6,H12 did not pass");
	}


}