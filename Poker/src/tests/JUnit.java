package tests;

import java.util.ArrayList;

import org.junit.*;
import model.*;

public class JUnit {

	// testTwoPair was wrong, would add to array even with single pair
	@Test
	public void testTwoPair() {
		int[] expected = { 0, 0 };
		PokerHand hand = new PokerHand();
		hand.addCard(5, 1);
		hand.addCard(5, 4);
		hand.addCard(3, 2);
		hand.addCard(4, 3);
		hand.addCard(9, 1);
		Assert.assertArrayEquals(expected, hand.twoPair());
	}

	@Test
	public void testStraight1() {
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(2, 1);
		hand.addCard(3, 1);
		hand.addCard(4, 1);
		hand.addCard(5, 1);

		Assert.assertEquals(5, hand.hasStraight());
	}

	@Test
	public void testStraight2() {
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(13, 1);
		hand.addCard(12, 1);
		hand.addCard(11, 1);
		hand.addCard(10, 1);

		Assert.assertEquals(1, hand.hasStraight());
	}

	@Test
	public void testFlush1() {
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(2, 1);
		hand.addCard(3, 1);
		hand.addCard(4, 1);
		hand.addCard(5, 1);

		Assert.assertEquals(1, hand.hasFlush());
	}

	@Test
	public void testFlush2() {
		PokerHand hand = new PokerHand();
		hand.addCard(10, 1);
		hand.addCard(2, 1);
		hand.addCard(3, 1);
		hand.addCard(4, 1);
		hand.addCard(5, 1);

		Assert.assertEquals(10, hand.hasFlush());
	}

	@Test
	public void testFullHouse() {
		int[] expected = { 1, 13 };

		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(1, 2);
		hand.addCard(1, 3);
		hand.addCard(13, 4);
		hand.addCard(13, 1);

		Assert.assertArrayEquals(expected, hand.fullHouse());
	}

	@Test
	public void testFullHouse2() {
		int[] expected = { 0, 0 };
		PokerHand hand = new PokerHand();
		hand.addCard(1, 3);
		hand.addCard(1, 1);
		hand.addCard(7, 2);
		hand.addCard(4, 5);
		hand.addCard(7, 2);
		Assert.assertArrayEquals(expected, hand.fullHouse());
	}

	@Test
	public void testStraightFlush1() {
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(2, 1);
		hand.addCard(3, 1);
		hand.addCard(4, 1);
		hand.addCard(5, 1);

		Assert.assertEquals(5, hand.straightFlush());
	}

	@Test
	public void testStraightFlush2() {
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(13, 1);
		hand.addCard(12, 1);
		hand.addCard(11, 1);
		hand.addCard(10, 1);

		Assert.assertEquals(1, hand.straightFlush());
	}

	@Test
	public void testStraightFlush3() {
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(13, 1);
		hand.addCard(12, 1);
		hand.addCard(11, 1);
		hand.addCard(10, 2);
		hand.addCard(2, 1);

		Assert.assertEquals(-1, hand.straightFlush());
	}

	@Test
	public void testCompareTo1() {
		int[][] community = { { 1, 2 }, { 12, 1 }, { 4, 2 } };

		PokerHand hand1 = new PokerHand();
		hand1.addCard(1, 1);
		hand1.addCard(10, 1);
		addCards(hand1, community);

		PokerHand hand2 = new PokerHand();
		hand2.addCard(1, 3);
		hand2.addCard(8, 1);
		addCards(hand2, community);

		Assert.assertTrue(hand1.compareTo(hand2) > 0);
	}

	@Test
	public void testCompareTo2() {
		int[][] community = { { 1, 2 }, { 1, 1 }, { 1, 3 }, { 2, 1 }, { 2, 2 } };

		PokerHand hand1 = new PokerHand();
		hand1.addCard(9, 1);
		hand1.addCard(10, 1);
		addCards(hand1, community);

		PokerHand hand2 = new PokerHand();
		hand2.addCard(9, 3);
		hand2.addCard(10, 3);
		addCards(hand2, community);

		Assert.assertTrue(hand1.compareTo(hand2) == 0);
	}

	// DOUBLE CHECK THIS MATH: THIS SHOULD BE EVERY POSSIBLE HAND OF 2 CARDS
	@Test
	public void testGenerateHands1() {
		ArrayList<Card[]> hands = PokerHand.generateHands(52, 2);
		Assert.assertTrue(1326 == hands.size()); // 52! / (20! * 2!)
		
		for (Card[] hand : hands) {
			for (Card c : hand) {
				System.out.print(c + " ");
			}
			System.out.println("");
		}
	}

	// Add cards into hand
	private static void addCards(PokerHand hand, int[][] cards) {
		for (int i = 0; i < cards.length; i++) {
			hand.addCard(cards[i][0], cards[i][1]);
		}
	}
}
