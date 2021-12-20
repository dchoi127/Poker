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
}
