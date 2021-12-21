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
		hand.addUserCard(5, 1);
		hand.addUserCard(5, 4);
		PokerHand.addCommunityCard(3, 2);
		PokerHand.addCommunityCard(4, 3);
		PokerHand.addCommunityCard(9, 1);
		Assert.assertArrayEquals(expected, hand.twoPair());
	}

	@Test
	public void testStraight1() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(2, 1);
		PokerHand.addCommunityCard(3, 1);
		PokerHand.addCommunityCard(4, 1);
		PokerHand.addCommunityCard(5, 1);

		Assert.assertEquals(5, hand.hasStraight());
	}

	@Test
	public void testStraight2() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(13, 1);
		PokerHand.addCommunityCard(12, 1);
		PokerHand.addCommunityCard(11, 1);
		PokerHand.addCommunityCard(10, 1);

		Assert.assertEquals(1, hand.hasStraight());
	}

	@Test
	public void testFlush1() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(2, 1);
		PokerHand.addCommunityCard(3, 1);
		PokerHand.addCommunityCard(4, 1);
		PokerHand.addCommunityCard(5, 1);

		Assert.assertEquals(1, hand.hasFlush());
	}

	@Test
	public void testFlush2() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(10, 1);
		hand.addUserCard(2, 1);
		PokerHand.addCommunityCard(3, 1);
		PokerHand.addCommunityCard(4, 1);
		PokerHand.addCommunityCard(5, 1);

		Assert.assertEquals(10, hand.hasFlush());
	}

	@Test
	public void testFullHouse() {
		int[] expected = { 1, 13 };

		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(1, 2);
		PokerHand.addCommunityCard(1, 3);
		PokerHand.addCommunityCard(13, 4);
		PokerHand.addCommunityCard(13, 1);

		Assert.assertArrayEquals(expected, hand.fullHouse());
	}

	@Test
	public void testFullHouse2() {
		int[] expected = { 0, 0 };
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 3);
		hand.addUserCard(1, 1);
		PokerHand.addCommunityCard(7, 2);
		PokerHand.addCommunityCard(4, 5);
		PokerHand.addCommunityCard(7, 2);
		Assert.assertArrayEquals(expected, hand.fullHouse());
	}

	@Test
	public void testStraightFlush1() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(2, 1);
		PokerHand.addCommunityCard(3, 1);
		PokerHand.addCommunityCard(4, 1);
		PokerHand.addCommunityCard(5, 1);

		Assert.assertEquals(5, hand.straightFlush());
	}

	@Test
	public void testStraightFlush2() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(13, 1);
		PokerHand.addCommunityCard(12, 1);
		PokerHand.addCommunityCard(11, 1);
		PokerHand.addCommunityCard(10, 1);

		Assert.assertEquals(1, hand.straightFlush());
	}

	@Test
	public void testStraightFlush3() {
		PokerHand hand = new PokerHand();
		hand.addUserCard(1, 1);
		hand.addUserCard(13, 1);
		PokerHand.addCommunityCard(12, 1);
		PokerHand.addCommunityCard(11, 1);
		PokerHand.addCommunityCard(10, 2);
		PokerHand.addCommunityCard(2, 1);

		Assert.assertEquals(-1, hand.straightFlush());
	}

	@Test
	public void testCompareTo1() {
		int[][] community = { { 1, 2 }, { 12, 1 }, { 4, 2 } };

		PokerHand hand1 = new PokerHand();
		hand1.addUserCard(1, 1);
		hand1.addUserCard(10, 1);
		PokerHand hand2 = new PokerHand();
		hand2.addUserCard(1, 3);
		hand2.addUserCard(8, 1);
		addCards(community);

		Assert.assertTrue(hand1.compareTo(hand2) > 0);
	}

	@Test
	public void testCompareTo2() {
		int[][] community = { { 1, 2 }, { 1, 1 }, { 1, 3 }, { 2, 1 }, { 2, 2 } };

		PokerHand hand1 = new PokerHand();
		hand1.addUserCard(9, 1);
		hand1.addUserCard(10, 1);
		addCards(community);

		PokerHand hand2 = new PokerHand();
		hand2.addUserCard(9, 3);
		hand2.addUserCard(10, 3);

		Assert.assertTrue(hand1.compareTo(hand2) == 0);
	}

	// DOUBLE CHECK THIS MATH: THIS SHOULD BE EVERY POSSIBLE HAND OF 2 CARDS
	@Test
	public void testGenerateHands1() {
		ArrayList<PokerHand> hands = PossibleHands.getPossibleHands();
		Assert.assertTrue(1326 == hands.size()); // 52! / (20! * 2!)
		
		for (PokerHand hand : hands) {
			System.out.println(hand);
		}
	}
	
	// PLAY AROUND WITH THIS AND TEST!!!
	@Test
	public void testProbability1() {
		int[][] community = { { 8, 1 }, { 7, 1 }, { 6, 1 } };
		
		PokerHand hand = new PokerHand();
		hand.addUserCard(8, 2);
		hand.addUserCard(3, 1);
		addCards(community);
		System.out.println(hand.getProbability());
	}

	// Add cards into hand
	private static void addCards(int[][] cards) {
		for (int i = 0; i < cards.length; i++) {
			PokerHand.addCommunityCard(cards[i][0], cards[i][1]);
		}
	}
}
