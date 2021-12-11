

public class Card {
	public static final int HEARTS = 1;
	public static final int SPADE = 2;
	public static final int CLUBS = 3;
	public static final int DIA = 4;
	
	
	
	
	private int suit;
	private int value;
	
	
	
	
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
}
