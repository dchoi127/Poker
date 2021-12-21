package model;

import java.util.ArrayList;

public class PossibleHands {
	private static ArrayList<PokerHand> possibleHands = generateHands(52, 2);
	
	// Returns possibleHands
	public static ArrayList<PokerHand> getPossibleHands() {
		return new ArrayList<PokerHand>(possibleHands);
	}
	
	public static void removePossibleHands(PokerHand p) {
		for (Card card : p.getCards()) {
			
			// Removing hands that include card added
			ArrayList<Integer> indices = new ArrayList<>();
			
			for (int i = 0; i < possibleHands.size(); i++) {
				if (possibleHands.get(i).contains(card)) {
					indices.add(i);
				}
			}
			
			for (int i = indices.size() - 1; i >= 0; i--) {
				possibleHands.remove((int) indices.get(i));
			}
		
		}
		
		for (Card card : PokerHand.getCommunityCards()) {
			
			// Removing hands that include card added
			ArrayList<Integer> indices = new ArrayList<>();
			
			for (int i = 0; i < possibleHands.size(); i++) {
				if (possibleHands.get(i).contains(card)) {
					indices.add(i);
				}
			}
			
			for (int i = indices.size() - 1; i >= 0; i--) {
				possibleHands.remove((int) indices.get(i));
			}
		
		}
	}
	
	/* n is number of cards in deck 
	 * r is number of cards generated per hand
	 * 
	 * I think it is easier to do n = 52 (so values from 1 - 52) 
	 * and then split the suits up (1 - 13 is hearts, 14 - 26 is spades etc).
	 * Then we remove all hands that include the cards in the user's hand in another method?
	 */
	private static ArrayList<PokerHand> generateHands(int n, int r) {
		ArrayList<PokerHand> hands=  new ArrayList<>();
		Card[] hand = new Card[r];
		
		for (int i = 0; i < r; i++) {
			hand[i] = new Card(i, 1);
		}
		
		int i = r - 1;
		while (hand[0].getValue() < n - r + 1) {
			while (i > 0 && hand[i].getValue() == n - r + 1) {
				i--;
			}
			
			PokerHand p = new PokerHand();
			for (int j = 0; j < hand.length; j++) {
				int value = hand[j].getValue();
				int suit = value / 13 + 1;
				
				value = value % 13 + 1;
				
				p.addUserCard(value, suit);
			}
			hands.add(p);
			
			hand[i].setValue(hand[i].getValue() + 1);
			
			while (i < r - 1) {
				hand[i + 1].setValue(hand[i].getValue() + 1);
				i++;
			}
		}
		
		return hands;
	}
	
}
