package tests;

import org.junit.*;
import model.*;

public class JUnit {
	
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
		int[] expected = {1, 13};
		
		PokerHand hand = new PokerHand();
		hand.addCard(1, 1);
		hand.addCard(1, 2);
		hand.addCard(1, 3);
		hand.addCard(13, 4);
		hand.addCard(13, 1);
		
		Assert.assertArrayEquals(expected, hand.fullHouse());
	}
}
