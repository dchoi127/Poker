package model;

import java.util.ArrayList;

public class PokerHand {

	// Final variables represented numerically to compare hands (higher is better)
	private static final int HIGH_CARD = 0;
	private static final int PAIR = 1;
	private static final int TWO_PAIR = 2;
	private static final int THREE_OF_A_KIND = 3;
	private static final int STRAIGHT = 4;
	private static final int FLUSH = 5;
	private static final int FULL_HOUSE = 6;
	private static final int FOUR_OF_A_KIND = 7;
	private static final int STRAIGHT_FLUSH = 8;
	private static final int ROYAL_FLUSH = 9;

	interface HandRanking {
		int getRanking();
	}

	private HandRanking[] ranking = new HandRanking[] { new HandRanking() {
		public int getRanking() {
			return highCard(new ArrayList<Integer>());
		}
	}, new HandRanking() {
		public int getRanking() {
			return hasPair();
		}
	}, new HandRanking() {
		public int getRanking() {
			return -1;
		}
	}, new HandRanking() {
		public int getRanking() {
			return threeOfAKind();
		}
	}, new HandRanking() {
		public int getRanking() {
			return hasStraight();
		}
	}, new HandRanking() {
		public int getRanking() {
			return hasFlush();
		}
	}, new HandRanking() {
		public int getRanking() {
			return -1;
		}
	}, new HandRanking() {
		public int getRanking() {
			return fourOfAKind();
		}
	}, new HandRanking() {
		public int getRanking() {
			return straightFlush();
		}
	} };

	private ArrayList<Card> cards;
	private static ArrayList<Card> community = new ArrayList<>();

	public PokerHand() {
		cards = new ArrayList<>();
	}

	public boolean addUserCard(int value, int suit) {
		Card card = new Card(value, suit);

		for (Card c : cards) {
			if (c.equals(card)) {
				return false;
			}
		}

		for (Card c : community) {
			if (c.equals(card)) {
				return false;
			}
		}

		cards.add(card);

		return true;
	}

	public static boolean addCommunityCard(int value, int suit) {
		Card card = new Card(value, suit);

		for (Card c : community) {
			if (c.equals(card)) {
				return false;
			}
		}

		community.add(card);

		return true;
	}

	// included an exclude param
	public int highCard(ArrayList<Integer> exclude) {
		int max = 0;
		ArrayList<Card> cards = mergeCommunity();

		for (Card c : cards) {
			if (exclude.contains(c.getValue())) {
				continue;
			}
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
	// puts lower value pair in first idx and higher pair in second
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

		// sorting
		if (twoPair[0] > twoPair[1]) {
			int temp = twoPair[1];
			twoPair[1] = twoPair[0];
			twoPair[0] = temp;
		}
		if (twoPair[0] == 0) {
			twoPair[1] = 0;
		}

		return twoPair;
	}

	// Returns value of three of a kind or -1
	public int threeOfAKind() {
		ArrayList<Card> cards = mergeCommunity();

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
		ArrayList<Card> cards = mergeCommunity();

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
		ArrayList<Card> cards = mergeCommunity();

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
		ArrayList<Card> cards = mergeCommunity();

		for (Card c : cards) {
			if (countOccur(c.getValue()) >= 4) {
				return c.getValue();
			}
		}

		return -1;
	}

	// Returns value of highest straight flush or - 1
	public int straightFlush() {
		ArrayList<Card> cards = mergeCommunity();

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

	// Need to check/test
	public int getHandRanking() {
		int handRanking = 0;
		int[] fullHouse = fullHouse(), twoPair = twoPair();

		if (royalFlush()) {
			return ROYAL_FLUSH;
		}

		for (int i = ranking.length - 1; i >= 0; i--) {
			if (ranking[i].getRanking() != -1) {
				return i;
			}
			if (i == FULL_HOUSE && fullHouse[0] != 0 && fullHouse[1] != 0) {
				return i;
			}
			if (i == TWO_PAIR && twoPair[0] != 0 && twoPair[1] != 0) {
				return i;
			}
		}

		return handRanking;
	}

	// compareTo method
	// returns > 0 if curr obj has a better hand than other
	// returns 0 if there is a tie
	// returns < 0 if curr obj has a worse hand than other
	public int compareTo(PokerHand other) {
		if (getHandRanking() > other.getHandRanking()) {
			return 1;
		} else if (getHandRanking() < other.getHandRanking()) {
			return -1;
		} else {
			int index = getHandRanking(); // if they're equal get the index
			if (index == TWO_PAIR) {
				int[] curr = twoPair();
				int[] check = other.twoPair();

				if (curr[1] == check[1]) {
					if (curr[0] == check[0]) {
						ArrayList<Integer> exclude = new ArrayList<Integer>();
						int val = highCard(exclude) - other.highCard(exclude);

						while (val == 0 && exclude.size() != cards.size()) {
							exclude.add(highCard(exclude));
							val = highCard(exclude) - other.highCard(exclude);
						}

						return val;
					} else {
						return curr[0] > check[0] ? 1 : -1;
					}
				} else {
					return curr[1] > check[1] ? 1 : -1;
				}

			} else if (index == FULL_HOUSE) {
				int[] curr = fullHouse();
				int[] check = other.fullHouse();

				if (curr[0] == check[0]) {
					if (curr[1] == check[1]) {
						ArrayList<Integer> exclude = new ArrayList<Integer>();
						int val = highCard(exclude) - other.highCard(exclude);

						while (val == 0 && exclude.size() != cards.size()) {
							exclude.add(highCard(exclude));
							val = highCard(exclude) - other.highCard(exclude);
						}

						return val;
					} else {
						return curr[1] > check[1] ? 1 : -1;
					}
				} else {
					return curr[0] > check[0] ? 1 : -1;
				}

			} else {
				int curr = ranking[index].getRanking();
				int check = ranking[index].getRanking();

				if (curr == check) { // if they have the same val, then check high card and exclude shared card
					ArrayList<Integer> exclude = new ArrayList<Integer>();
					int val = highCard(exclude) - other.highCard(exclude);

					while (val == 0 && exclude.size() != cards.size()) {
						exclude.add(highCard(exclude));
						val = highCard(exclude) - other.highCard(exclude);
					}

					return val;
				} else {
					return curr > check ? 1 : -1;
				}
			}
		}
	}

	public String toString() {
		String result = "";

		for (Card c : cards) {
			result += c + " ";
		}

		return result;
	}

	public boolean contains(Card card) {
		for (Card c : cards) {
			if (c.equals(card)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	// Returns number of hands user would win as a percentage
	public float getProbability() {
		PossibleHands.removePossibleHands(this);
		ArrayList<PokerHand> possibleHands = PossibleHands.getPossibleHands();
		int betterHands = 0, totalHands = possibleHands.size();

		for (PokerHand p : possibleHands) {
			if (this.compareTo(p) > 0) {
				betterHands++;
			}
		}

		float percentage = (float) betterHands / (float) totalHands;
		return percentage;
	}

	public static void resetCommunity() {
		community = new ArrayList<>();
	}

	public static ArrayList<Card> getCommunityCards() {
		return community;
	}

	// counts number of times value is in card ArrayList
	private int countOccur(int value) {
		ArrayList<Card> cards = mergeCommunity();
		int count = 0;

		for (Card c : cards) {
			if (c.getValue() == value) {
				count++;
			}
		}
		return count;
	}

	private ArrayList<Card> mergeCommunity() {
		ArrayList<Card> cards = new ArrayList<>();

		for (Card c : this.cards) {
			cards.add(c);
		}

		for (Card c : community) {

			for (Card d : cards) {
				if (d.equals(c)) {
					break;
				}
			}

			cards.add(c);
		}

		return cards;
	}
}
