package model;

import java.util.ArrayList;

public class PokerHand {
	
	// Final variables represented numerically to compare hands (higher is better)
	private static final int HIGH_CARD = 1;
	private static final int PAIR = 2;
	private static final int TWO_PAIR = 3;
	private static final int THREE_OF_A_KIND = 4;
	private static final int STRAIGHT = 5;
	private static final int FLUSH = 6;
	private static final int FULL_HOUSE = 7;
	private static final int FOUR_OF_A_KIND = 8;
	private static final int STRAIGHT_FLUSH = 9;
	private static final int ROYAL_FLUSH = 10;
	
	
	interface HandRanking {
		int getRanking();
	}
	
	private HandRanking[] ranking = new HandRanking[] {
			new HandRanking() { public int getRanking() { return highCard(); } },
			new HandRanking() { public int getRanking() { return hasPair(); } },
			new HandRanking() { public int getRanking() { return threeOfAKind(); } },
			new HandRanking() { public int getRanking() { return hasStraight(); } },
			new HandRanking() { public int getRanking() { return hasFlush(); } },
			new HandRanking() { public int getRanking() { return fourOfAKind(); } },
			new HandRanking() { public int getRanking() { return straightFlush(); } }
	};

	private ArrayList<Card> cards;

	public PokerHand() {
		cards = new ArrayList<>();
	}

	public boolean addCard(int value, int suit) {
		Card card = new Card(value, suit);

		for (Card c : cards) {
			if (c.equals(card)) {
				return false;
			}
		}

		cards.add(card);
		return true;
	}

	public int highCard() {
		int max = 0;

		for (Card c : cards) {

			// Accounts for Ace high card
			if (c.getValue() == 1) {
				return 1;
			}
			if (c.getValue() > max) {
				max = c.getValue();
			}
		}
		return max;
	}

	// Returns value of highest pair or -1 if does not exist
	public int hasPair() {

		if (countOccur(1) >= 2) {
			return 1;
		}

		for (int i = 13; i > 1; i--) {
			if (countOccur(i) >= 2) {
				return i;
			}
		}

		return -1;
	}

	// Return value of two pairs in array
	// [0, 0] if does not exist
	public int[] twoPair() {
		int count = 0;
		int[] twoPair = new int[2];

		if (countOccur(1) >= 2) {
			twoPair[count++] = 1;
		}

		for (int i = 13; i > 1; i--) {
			if (countOccur(i) >= 2) {
				if (count == 0) {
					twoPair[count++] = i;
				} else {
					twoPair[count] = i;
				}
			}
		}

		return twoPair;
	}

	// Returns value of three of a kind or -1
	public int threeOfAKind() {
		if (countOccur(1) >= 3) {
			return 1;
		}

		for (Card c : cards) {
			if (countOccur(c.getValue()) == 3) {
				return c.getValue();
			}
		}

		return -1;
	}

	// Returns value of high card straight
	public int hasStraight() {
		for (Card c : cards) {
			boolean hasStraight = true;

			// Accounts for Ace high straight
			if (c.getValue() == 1) {
				for (int j = 1; j < 5; j++) {
					if (countOccur(14 - j) == 0) {
						hasStraight = false;
						break;
					}
				}
			} else {
				for (int j = 1; j < 5; j++) {
					if (countOccur(c.getValue() - j) == 0) {
						hasStraight = false;
						break;
					}
				}
			}

			if (hasStraight) {
				return c.getValue();
			}
		}

		return -1;
	}

	// Returns value of highest flush or -1
	public int hasFlush() {
		for (int i = 1; i <= 4; i++) {
			int max = 0, count = 0;
			boolean aceFound = false;

			for (Card c : cards) {
				if (c.getSuit() == i) {
					count++;

					if (c.getValue() == 1) {
						aceFound = true;
					}

					if (c.getValue() > max) {
						max = c.getValue();
					}
				}
			}

			if (count >= 5) {
				if (aceFound) {
					return 1;
				} else {
					return max;
				}
			}
		}

		return -1;
	}

	// Return value of full house in array
	// [0, 0] if does not exist
	public int[] fullHouse() {
		int[] fullHouse = new int[2], twoPair = twoPair();
		int threeOfAKind = threeOfAKind();
		

		if (threeOfAKind != -1 && twoPair[1] != 0) {
			fullHouse[0] = threeOfAKind;
			fullHouse[1] = twoPair[1];
		}

		return fullHouse;
	}

	// Returns value of four of a kind or -1
	public int fourOfAKind() {
		for (Card c : cards) {
			if (countOccur(c.getValue()) >= 4) {
				return c.getValue();
			}
		}

		return -1;
	}
	 
	// Returns value of highest straight flush or - 1
	public int straightFlush() {
		if (hasStraight() != -1 && hasFlush() != -1) {
			for (Card c : cards) {
				boolean hasStraight = true;
				int count = 1, suit = c.getSuit();

				// Accounts for Ace high straight
				if (c.getValue() == 1) {
					for (int j = 1; j < 5; j++) {
						if (countOccur(14 - j) == 0) {
							hasStraight = false;
							break;
						} else {
							for (Card d : cards) {
								if (d.getValue() == 14 - j && d.getSuit() == suit) {
									count++;
									break;
								}
							}
						}
					}
				} else {
					for (int j = 1; j < 5; j++) {
						if (countOccur(c.getValue() - j) == 0) {
							hasStraight = false;
							break;
						} else {
							for (Card d : cards) {
								if (d.getValue() == c.getValue() - j && d.getSuit() == suit) {
									count++;
									break;
								}
							}
						}
					}
				}

				if (hasStraight && count == 5) {
					return c.getValue();
				}
			}
		}
		
		return -1;
	}
	
	// Returns true/false for royal flush
	public boolean royalFlush() {
		if (straightFlush() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// Unfinished
	public int getHandRanking() {
		int handRanking = 1;
		int[] fullHouse = fullHouse(), twoPair = twoPair();
		
		if (royalFlush()) {
			handRanking = ROYAL_FLUSH;
		} else if (fullHouse[0] != 0 && fullHouse[1] != 0) {
			handRanking = FULL_HOUSE;
		} else if (twoPair[0] != 0 && twoPair[1] != 0) {
			handRanking = TWO_PAIR;
		}
		
		return handRanking;
	}
	
	// counts number of times value is in card ArrayList
	private int countOccur(int value) {
		int count = 0;
		for (Card c : cards) {
			if (c.getValue() == value) {
				count++;
			}
		}
		return count;
	}
}
