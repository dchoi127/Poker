package model;

import java.util.Scanner;
import java.math.BigDecimal;

public class Pay {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of players: ");
		int numPlayers = scan.nextInt();
		System.out.print("Enter buy in: ");
		BigDecimal buyIn = new BigDecimal(scan.nextDouble());
		BigDecimal[] database = new BigDecimal[numPlayers];
		String[] database2 = new String[numPlayers];
		BigDecimal endTotal = new BigDecimal(0);
		int loserIndex = 0;
		BigDecimal loserAmount = new BigDecimal(numPlayers).multiply(buyIn);
		BigDecimal totalAmount = new BigDecimal(numPlayers).multiply(buyIn);

		for (int i = 1; i <= numPlayers; i++) {
			System.out.print("Enter player " + i + " name: ");
			database2[i - 1] = scan.next();
			System.out.print("Enter " + database2[i - 1] + " end balance: ");
			database[i - 1] = new BigDecimal(scan.nextDouble());
			endTotal = endTotal.add(database[i - 1]);
			if (database[i - 1].compareTo(loserAmount) < 1) {
				loserAmount = database[i - 1];
				loserIndex = i - 1;
			}
		}

		if (endTotal.compareTo(totalAmount) != 0) {
			System.out.println("Error: end balance incorrect!");
		} else {
			for (int i = 0; i < numPlayers; i++) {
				if (i != loserIndex && database[i].compareTo(buyIn) < 0) {
					System.out.println(
							database2[i] + " pays " + database2[loserIndex] + " $" + buyIn.subtract(database[i]));
				} else if (i == loserIndex) {
					System.out.println(database2[i] + " pays everyone");
				} else {
					System.out.println(database2[i] + " receives $" + database[i].subtract(buyIn) + " from "
							+ database2[loserIndex]);
				}
			}
		}

		scan.close();
	}
}
