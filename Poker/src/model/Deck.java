package model;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;
	private ArrayList<Card> removedCards;

	public Deck() {
		removedCards = new ArrayList<>();
		deck = new ArrayList<>();

		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				deck.add(new Card(i, j));
			}
		}
	}

	public void removeCard(Card c) {
		deck.remove(c);
		removedCards.add(c);
	}

	// Returns possibleHands
	public ArrayList<PokerHand> getPossibleHands() {
		return new ArrayList<PokerHand>(generateHands(52, 2));
	}

	/*
	 * public void removePossibleHands(Card card) { // Removing hands that include
	 * card added ArrayList<Integer> indices = new ArrayList<>();
	 * 
	 * for (int i = 0; i < possibleHands.size(); i++) { if
	 * (possibleHands.get(i).contains(card)) { indices.add(i); } }
	 * 
	 * for (int i = indices.size() - 1; i >= 0; i--) { possibleHands.remove((int)
	 * indices.get(i)); } }
	 */

	/*
	 * n is number of cards in deck r is number of cards generated per hand
	 * 
	 * I think it is easier to do n = 52 (so values from 1 - 52) and then split the
	 * suits up (1 - 13 is hearts, 14 - 26 is spades etc). Then we remove all hands
	 * that include the cards in the user's hand in another method?
	 */
	private ArrayList<PokerHand> generateHands(int n, int r) {
		ArrayList<PokerHand> hands = new ArrayList<>();
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

				p.addUserCard(value, suit, new Deck());
			}
			hands.add(p);

			hand[i].setValue(hand[i].getValue() + 1);

			while (i < r - 1) {
				hand[i + 1].setValue(hand[i].getValue() + 1);
				i++;
			}
		}
		
		// Removing hands that include removed cards
		for (Card card : removedCards) {
			
			ArrayList<Integer> indices = new ArrayList<>();
			
			for (int j = 0; j < hands.size(); j++) {
				if (hands.get(j).contains(card)) {
					indices.add(j);
				}
			}
			
			for (int j = indices.size() - 1; j >= 0; j--) {
				hands.remove((int) indices.get(j));
			}
		
		}
		
		return hands;
	}

}
