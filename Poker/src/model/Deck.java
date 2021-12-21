package model;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
		
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				deck.add(new Card(i, j));
			}
		}
	}
	
	public boolean removeCard(int value, int suit) {
		Card c = new Card(value, suit);
		return deck.remove(c);
	}
	
	/* n is number of cards in deck 
	 * r is number of cards generated per hand
	 * 
	 * I think it is easier to do n = 52 (so values from 1 - 52) 
	 * and then split the suits up (1 - 13 is hearts, 14 - 26 is spades etc).
	 * Then we remove all hands that include the cards in the user's hand in another method?
	 */
	public ArrayList<Card[]> generateHands(int n, int r) {
		ArrayList<Card[]> hands=  new ArrayList<>();
		Card[] hand = new Card[r];
		
		for (int i = 0; i < r; i++) {
			hand[i] = new Card(i, 1);
		}
		
		int i = r - 1;
		while (hand[0].getValue() < n - r + 1) {
			while (i > 0 && hand[i].getValue() == n - r + 1) {
				i--;
			}
			
			Card[] handCopy = new Card[r];
			for (int j = 0; j < hand.length; j++) {
				int value = hand[j].getValue();
				int suit = value / 13 + 1;
				
				value = value % 13 + 1;
				
				handCopy[j] = new Card(value, suit);
			}
			hands.add(handCopy);
			
			hand[i].setValue(hand[i].getValue() + 1);
			
			while (i < r - 1) {
				hand[i + 1].setValue(hand[i].getValue() + 1);
				i++;
			}
		}
		
		return hands;
	}
}
