package model;

public class Card {
	public static final int HEARTS = 1;
	public static final int SPADE = 2;
	public static final int CLUBS = 3;
	public static final int DIA = 4;

	private int suit;
	private int value;
	
	// Values go from 1 - 13 (1 is Ace)
	public Card(int value, int suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public int getSuit() {
		return suit;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setSuit(int suit) {
		this.suit = suit;
	}
	
	public String toString() {
		return "Value: " + value + ", Suit: " + suit;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		
		Card c = (Card) obj;
		return suit == c.suit && value == c.value;
	}
}
