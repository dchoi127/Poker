package model;

public class Game {

	PokerHand userHand;
	Deck deck;

//		for (int i = 0; i < 2; i++) {
//			readInCard(userHand, deck);
//		}

//		for (int i = 0; i < 3; i++) {
//			readInCard(null, deck);
//		}
//		
//		for (int i = 0; i < 2; i++) {
//			userHand.getProbability(deck);
//			readInCard(null, deck);
//		}
	
	
	public Game() {
		userHand = new PokerHand();
		deck = new Deck();
	}
	public String probability() {
		return userHand.getProbability(deck);
	}

	// reads in two cards and adds them to the arraylist
	private static void readInCard(PokerHand hand, Deck deck, int value, int suit) {

		if (hand == null) {
			PokerHand.addCommunityCard(value, suit, deck);
		} else {
			hand.addUserCard(value, suit, deck);
		}
	}

	public void readInGUI(int value, int suit, boolean commOrUser) {
		if (commOrUser) {
			readInCard(null, deck, value, suit);
		}else {
			readInCard(userHand, deck, value, suit);
		}
	}

}
