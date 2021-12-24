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

	public boolean addUserCard(int value, int suit, Deck deck) {
		Card card = new Card(value, suit);

		/* 
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
		*/
		
		cards.add(card);
		deck.removeCard(card);	
		
		return true;
	}

	public static boolean addCommunityCard(int value, int suit, Deck deck) {
		Card card = new Card(value, suit);
		
		/*
		for (Card c : community) {
			if (c.equals(card)) {
				return false;
			}
		}
		*/

		community.add(card);
		deck.removeCard(card);	

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
				twoPair[count++] = i;
				if (count == 2) {
					break;
				}
			}
		}

		// sorting
		int temp = twoPair[1];
		twoPair[1] = twoPair[0];
		twoPair[0] = temp;

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
						exclude.add(curr[0]);
						exclude.add(curr[1]);
						
						int val = highCard(exclude) - other.highCard(exclude);
						
						if (val == 0) {
							return 0;
						}
						if (highCard(exclude) == 1) {
							return 1;
						}
						if (other.highCard(exclude) == 1) {
							return -1;
						}

						return val;
					} else {
						if (curr[0] == 1) {
							return 1;
						}
						if (check[0] == 1) {
							return -1;
						}
						return curr[0] > check[0] ? 1 : -1;
					}
				} else {
					if (curr[1] == 1) {
						return 1;
					}
					if (check[1] == 1) {
						return -1;
					}
					return curr[1] > check[1] ? 1 : -1;
				}

			} else if (index == FULL_HOUSE) {
				int[] curr = fullHouse();
				int[] check = other.fullHouse();

				if (curr[0] == check[0]) {
					if (curr[1] == check[1]) {
						return 0;
					} else {
						if (curr[1] == 1) {
							return 1;
						}
						if (check[1] == 1) {
							return -1;
						}
						return curr[1] > check[1] ? 1 : -1;
					}
				} else {
					if (curr[0] == 1) {
						return 1;
					}
					if (check[0] == 1) {
						return -1;
					}
					return curr[0] > check[0] ? 1 : -1;
				}

			} else {
				int curr = ranking[index].getRanking();
				int check = other.ranking[index].getRanking();

				if (curr == check) { // if they have the same val, then check high card and exclude shared card
					ArrayList<Integer> exclude = new ArrayList<Integer>();
					
					if (index == FOUR_OF_A_KIND) {
						for (int i = 0; i < 4; i++) {
							exclude.add(curr);
						}
					} else if (index == THREE_OF_A_KIND) {
						for (int i = 0; i < 3; i++) {
							exclude.add(curr);
						}
					} else if (index == PAIR) {
						for (int i = 0; i < 2; i++) {
							exclude.add(curr);
						}
					}
					
					int val = highCard(exclude) - other.highCard(exclude);

					while (val == 0 && exclude.size() < 5) {
						int excluded = highCard(exclude);
						
						for (int i = 0; i < countOccur(excluded); i++) {
							exclude.add(excluded);
						}
						
						if (exclude.size() < 5) {
							val = highCard(exclude) - other.highCard(exclude);
						}
					}
					
					if (val == 0) {
						return 0;
					}
					if (highCard(exclude) == 1) {
						return 1;
					}
					if (other.highCard(exclude) == 1) {
						return -1;
					}
					
					return val;
				} else {
					if (curr == 1) {
						return 1;
					}
					if (check == 1) {
						return -1;
					}
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
	public float getProbability(Deck deck) {
		ArrayList<PokerHand> possibleHands = deck.getPossibleHands();
		int betterHands = 0, worseHands = 0, evenHands = 0, totalHands = possibleHands.size();

		for (PokerHand p : possibleHands) {
			int temp = this.compareTo(p);
			
			if (temp > 0) {
				betterHands++;
			} else if (temp < 0) {
				worseHands++;
			} else {
				evenHands++;
			}
		}
		
		System.out.println("You beat " + betterHands + " hands. Lose to " + worseHands + " hands. Tie with " + evenHands + " hands.");
		
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
			cards.add(c);
		}

		return cards;
	}
}
