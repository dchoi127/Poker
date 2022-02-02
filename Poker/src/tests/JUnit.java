package tests;

import java.util.ArrayList;

import org.junit.*;
import model.*;

public class JUnit {

	@Before
	public void reset() {
		PokerHand.resetCommunity();
	}

	// testTwoPair was wrong, would add to array even with single pair
	@Test
	public void testTwoPair() {
		int[] expected = { 0, 0 };
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(5, 1, d);
		hand.addUserCard(5, 4, d);
		PokerHand.addCommunityCard(3, 2, d);
		PokerHand.addCommunityCard(4, 3, d);
		PokerHand.addCommunityCard(9, 1, d);
		Assert.assertArrayEquals(expected, hand.twoPair());
	}

	@Test
	public void testStraight1() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(2, 1, d);
		PokerHand.addCommunityCard(3, 1, d);
		PokerHand.addCommunityCard(4, 1, d);
		PokerHand.addCommunityCard(5, 1, d);

		Assert.assertEquals(5, hand.hasStraight());
	}

	@Test
	public void testStraight2() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(13, 1, d);
		PokerHand.addCommunityCard(12, 1, d);
		PokerHand.addCommunityCard(11, 1, d);
		PokerHand.addCommunityCard(10, 1, d);

		Assert.assertEquals(1, hand.hasStraight());
	}

	@Test
	public void testFlush1() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(2, 1, d);
		PokerHand.addCommunityCard(3, 1, d);
		PokerHand.addCommunityCard(4, 1, d);
		PokerHand.addCommunityCard(5, 1, d);

		Assert.assertEquals(1, hand.hasFlush());
	}

	@Test
	public void testFlush2() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(10, 1, d);
		hand.addUserCard(2, 1, d);
		PokerHand.addCommunityCard(3, 1, d);
		PokerHand.addCommunityCard(4, 1, d);
		PokerHand.addCommunityCard(5, 1, d);

		Assert.assertEquals(10, hand.hasFlush());
	}

	@Test
	public void testFullHouse() {
		int[] expected = { 1, 13 };

		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(1, 2, d);
		PokerHand.addCommunityCard(1, 3, d);
		PokerHand.addCommunityCard(13, 4, d);
		PokerHand.addCommunityCard(13, 1, d);

		Assert.assertArrayEquals(expected, hand.fullHouse());
	}

	@Test
	public void testFullHouse2() {
		int[] expected = { 0, 0 };
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 3, d);
		hand.addUserCard(1, 1, d);
		PokerHand.addCommunityCard(7, 2, d);
		PokerHand.addCommunityCard(4, 5, d);
		PokerHand.addCommunityCard(7, 2, d);
		Assert.assertArrayEquals(expected, hand.fullHouse());
	}

	@Test
	public void testStraightFlush1() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(2, 1, d);
		PokerHand.addCommunityCard(3, 1, d);
		PokerHand.addCommunityCard(4, 1, d);
		PokerHand.addCommunityCard(5, 1, d);

		Assert.assertEquals(5, hand.straightFlush());
	}

	@Test
	public void testStraightFlush2() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(13, 1, d);
		PokerHand.addCommunityCard(12, 1, d);
		PokerHand.addCommunityCard(11, 1, d);
		PokerHand.addCommunityCard(10, 1, d);

		Assert.assertEquals(1, hand.straightFlush());
	}

	@Test
	public void testStraightFlush3() {
		PokerHand hand = new PokerHand();
		Deck d = new Deck();
		hand.addUserCard(1, 1, d);
		hand.addUserCard(13, 1, d);
		PokerHand.addCommunityCard(12, 1, d);
		PokerHand.addCommunityCard(11, 1, d);
		PokerHand.addCommunityCard(10, 2, d);
		PokerHand.addCommunityCard(2, 1, d);

		Assert.assertEquals(-1, hand.straightFlush());
	}

	@Test
	public void testCompareTo1() {
		int[][] community = { { 1, 2 }, { 12, 1 }, { 4, 2 } };

		PokerHand hand1 = new PokerHand();
		Deck d = new Deck();
		hand1.addUserCard(1, 1, d);
		hand1.addUserCard(10, 1, d);
		PokerHand hand2 = new PokerHand();
		hand2.addUserCard(1, 3, d);
		hand2.addUserCard(8, 1, d);
		addCards(community, d);

		Assert.assertTrue(hand1.compareTo(hand2) > 0);
	}

	@Test
	public void testCompareTo2() {
		int[][] community = { { 1, 2 }, { 1, 1 }, { 1, 3 }, { 2, 1 }, { 2, 2 } };

		PokerHand hand1 = new PokerHand();
		Deck d = new Deck();
		hand1.addUserCard(9, 1, d);
		hand1.addUserCard(10, 1, d);
		addCards(community, d);

		PokerHand hand2 = new PokerHand();
		hand2.addUserCard(9, 3, d);
		hand2.addUserCard(10, 3, d);

		Assert.assertTrue(hand1.compareTo(hand2) == 0);
	}

	@Test
	public void testCompareTo3() {
		int[][] community = { { 10, 1 }, { 11, 1 }, { 12, 1 } };

		PokerHand hand1 = new PokerHand();
		Deck d = new Deck();
		hand1.addUserCard(2, 2, d);
		hand1.addUserCard(2, 3, d);
		addCards(community, d);

		PokerHand hand2 = new PokerHand();
		hand2.addUserCard(3, 2, d);
		hand2.addUserCard(3, 3, d);

		Assert.assertTrue(hand1.compareTo(hand2) < 0);
	}

	@Test
	public void testCompareTo4() {
		int[][] community = { { 6, 1 }, { 10, 1 }, { 10, 2 }, { 1, 1 }, { 1, 2 } };

		PokerHand hand1 = new PokerHand();
		Deck d = new Deck();
		hand1.addUserCard(1, 3, d);
		hand1.addUserCard(3, 1, d);
		addCards(community, d);
		
		Deck d2 = new Deck();
		PokerHand hand2 = new PokerHand();
		hand2.addUserCard(1, 4, d2);
		hand2.addUserCard(7, 1, d2);

		Assert.assertTrue(hand1.compareTo(hand2) == 0);
		hand1.getProbability(d);
	}
	
	@Test
	public void testBruh() {
		int[][] community = { { 10, 3 }, { 5, 3 }, { 9, 2 }, { 5, 2 }, { 11, 3 } };
		PokerHand hand1 = new PokerHand();
		Deck d = new Deck();
		hand1.addUserCard(2, 2, d);
		hand1.addUserCard(7, 1, d);
		addCards(community, d);
		
		System.out.println(hand1.getProbability(d));
		
		
	}
	// DOUBLE CHECK THIS MATH: THIS SHOULD BE EVERY POSSIBLE HAND OF 2 CARDS
	@Test
	public void testGenerateHands1() {
		Deck d = new Deck();
		// PokerHand hand = new PokerHand();
		// hand.addUserCard(1, 1, d);
		ArrayList<PokerHand> hands = d.getPossibleHands();

		for (PokerHand h : hands) {
			System.out.println(h);
		}

		Assert.assertTrue(1326 == hands.size()); // 52! / (50! * 2!)
	}

	// PLAY AROUND WITH THIS AND TEST!!!
	@Test
	public void testProbability1() {
		Deck d = new Deck();
		int[][] community = { { 10, 1 }, { 11, 1 }, { 12, 1 } };

		PokerHand hand = new PokerHand();
		hand.addUserCard(2, 2, d);
		hand.addUserCard(2, 3, d);
		addCards(community, d);
		System.out.println(hand.getProbability(d));
	}

	// Add cards into hand
	private static void addCards(int[][] cards, Deck deck) {
		for (int i = 0; i < cards.length; i++) {
			PokerHand.addCommunityCard(cards[i][0], cards[i][1], deck);
		}
	}
}
