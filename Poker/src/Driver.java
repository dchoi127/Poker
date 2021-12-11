import java.util.Scanner;
import java.util.ArrayList;
public class Driver {
	
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		String bORs; // string to keep track of whether user is big blind or small
		ArrayList<Card> uCards = new ArrayList<Card>(); // user cards
		ArrayList<Card> cCards = new ArrayList<Card>(); // community cards
		
		readInTwo(uCards);
		
		System.out.println("ARE YOU BIG BLIND?  Y:N");
		bORs = input.next().toLowerCase();
		if(bORs.charAt(0) == 'n') {
			preRound(uCards);
		}
		
		System.out.println("--------COMMUNITY CARDS--------");
		commCards(cCards);
	}
	
	
	
	
	public static void commCards(ArrayList<Card> cCards) {
		int value, suit;
		readInTwo(cCards);
		System.out.println("ENTER CARD VALUE: ");
		value = input.nextInt();
		
		System.out.println("ENTER CARD SUIT: \n 1: HEART \n 2: SPADE \n 3: ClUBS \n 4: DIAMONDS");
		suit = input.nextInt();
		cCards.add(new Card(value,suit));
		
	}
	
	//preRound is executed if user is not BIG BLIND (dont think we need to calc stats here since its just pre round)
	public static void preRound(ArrayList<Card> uCards) {
		if(PokerHand.hasPair(uCards)!= null) {
			System.out.println("YOU HAVE A PAIR. YOU SHOULD PROBABLY STAY IN");
		}
	}
	//reads in two cards and adds them to the arraylist
	public static void readInTwo(ArrayList<Card> cards) {
		int value, suit;
		System.out.println("ENTER CARD VALUE: ");
		value = input.nextInt();
		
		System.out.println("ENTER CARD SUIT: \n 1: HEART \n 2: SPADE \n 3: ClUBS \n 4: DIAMONDS");
		suit = input.nextInt();
		
		cards.add(new Card(value,suit));
		
		System.out.println("ENTER CARD VALUE: ");
		value = input.nextInt();
		
		System.out.println("ENTER CARD SUIT: \n 1: HEART \n 2: SPADE \n 3: ClUBS \n 4: DIAMONDS");
		suit = input.nextInt();
		
		cards.add(new Card(value,suit));
	}

}
