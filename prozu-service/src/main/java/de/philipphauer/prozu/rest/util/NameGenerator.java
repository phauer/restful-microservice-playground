package de.philipphauer.prozu.rest.util;

import java.util.Random;

public class NameGenerator {

	private static String[] firstnames = { "Paul", "Peter", "Emma", "Norbert", "Inge", "Tim", "Horst", "Lene", "Julia",
			"Lea", "Johannes", "Günther", "Leo" };
	private static String[] surnames = { "Köhler", "Müller", "Bauer", "Hand", "Schmidt", "Fischer", "Angler",
			"Henckel", "Persch", "Fliege", "Hammer" };
	private static Random random = new Random();

	public static String generateName() {
		int firstnameIndex = randomInt(0, firstnames.length);
		String firstname = firstnames[firstnameIndex];

		int lastnameIndex = randomInt(0, surnames.length);
		String lastname = surnames[lastnameIndex];

		return firstname + " " + lastname;
	}

	private static int randomInt(int minInclusive, int maxExclusive) {
		return random.nextInt(maxExclusive - minInclusive) + minInclusive;
	}
}
