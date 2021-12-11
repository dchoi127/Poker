import java.util.ArrayList;
import java.util.HashMap;
public class PokerHand {
	
	//maybe have this return the card value with the pair instead of boolean
	//since value of the pair is relevant
	public static HashMap<Integer,Integer> hasPair(ArrayList<Card> cards) {
		HashMap<Integer, Integer> result = new HashMap<Integer,Integer>();
		for (Card c: cards) {
			for (Card d: cards) {
				if (c.getValue() == d.getValue() && d != c) {
					result.put(c.getValue(), c.getSuit());
					return result;
				}
			}
		}
		
		return null;
	}
	
	
	public static int highCard(ArrayList<Card> cards) {
		int max = 0;
		for(Card c: cards) {
			if(c.getValue() > max) {
				max = c.getValue();
			}
		}
		return max;
	}
	
	
	
	public static boolean threeOfAKind(ArrayList<Card> cards) {
		for(Card c: cards) {
			if(countOccur(cards, c.getValue()) == 3) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean fourOfAKind(ArrayList<Card> cards) {
		for(Card c: cards) {
			if(countOccur(cards, c.getValue()) == 4) {
				return true;
			}
		}
		
		return false;
	}
	
	
//	public static boolean twoPair(ArrayList<Card> cards) {
//		
//	}
	//counts number of times value is in card ArrayList
	private static int countOccur(ArrayList<Card> cards, int value) {
		int count = 0;
		for(Card c: cards) {
			if(c.getValue() == value) {
				count++;
			}
		}
		return count;
	}
}
