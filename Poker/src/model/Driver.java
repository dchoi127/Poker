package model;

import java.util.Scanner;

public class Driver {	
	
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
//		String bORs; // string to keep track of whether user is big blind or small
		PokerHand userHand = new PokerHand(); // user cards
//		PokerHand cCards = new PokerHand(); // community cards
		Deck deck = new Deck();
		
		for (int i = 0; i < 2; i++) {
			readInCard(userHand, deck);
		}
		

//		 System.out.println("ARE YOU BIG BLIND?  Y:N");
//		 bORs = input.next().toLowerCase();
//		 if (bORs.charAt(0) == 'n') {
//			preRound(uCards);
//		 }

		System.out.println("--------COMMUNITY CARDS--------");

		for (int i = 0; i < 3; i++) {
			readInCard(null, deck);
		}
		
		for (int i = 0; i < 2; i++) {
			userHand.getProbability(deck);
			readInCard(null, deck);
		}
		
		userHand.getProbability(deck);
	}

//	public static void commCards(PokerHand cCards) {
//		int value, suit;
//		readInTwo(cCards);
//		System.out.println("ENTER CARD VALUE: ");
//		value = input.nextInt();
//
//		System.out.println("ENTER CARD SUIT: \n 1: HEART \n 2: SPADE \n 3: ClUBS \n 4: DIAMONDS");
//		suit = input.nextInt();
//		cCards.addCard(value, suit);
//
//	}

	// preRound is executed if user is not BIG BLIND (dont think we need to calc
	// stats here since its just pre round)
//	public static void preRound(PokerHand uCards) {
//		if (uCards.hasPair() != -1) {
//			System.out.println("YOU HAVE A PAIR. YOU SHOULD PROBABLY STAY IN");
//		}
//	}

	// reads in two cards and adds them to the arraylist
	public static void readInCard(PokerHand hand, Deck deck) {
		int value, suit;
		System.out.println("ENTER CARD VALUE: ");
		value = input.nextInt();

		System.out.println("ENTER CARD SUIT: \n 1: HEART \n 2: SPADE \n 3: ClUBS \n 4: DIAMONDS");
		suit = input.nextInt();

		if (hand == null) {
			PokerHand.addCommunityCard(value, suit, deck);
		} else {
			hand.addUserCard(value, suit, deck);
		}	
	}

}
