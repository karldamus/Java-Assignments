public class Testing {
	public static void main(String[] args) {

		AbstractCard[] cardsA = {
			new OtherCard("QD"),new OtherCard("9D"),new OtherCard("JD"),new OtherCard("AD")
		};
		AbstractCard[] cardsB = {
			new OtherCard("QD"),new Card("9D"),new Card("JD"),new OtherCard("AD")
		};
		AbstractCard[] cardsC = {
			new OtherCard("QD"),new Card("9D"),new Card("JD"),new OtherCard("AD")
		};

		test(cardsA, "(A)", 1);
		test(cardsB, "(B)", 1);
		// test(cardsC, "(C)", 2);
	}

	/**
	 * @param cards deck of cards
	 * @param caseValue string representation (simply for output)
	 * @param version can be 1 or 2, see assignment specification
	 * 
	 * @see #sort(AbstractCard[], int)
	 * @see #print(AbstractCard[])
	 */
	public static void test(AbstractCard[] cards, String caseValue, int version) {
		System.out.println("Testing Case: " + caseValue);
		print(cards);
		cards = sort(cards, version);
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
	}

	/**
	 * 
	 * @param cards the set of cards
	 * @param version can be 1 or 2, see assignment specification
	 * 
	 * @return sorted deck of cards
	 * 
	 * @see #print(AbstractCard[])
	 */
	public static AbstractCard[] sort(AbstractCard[] cards, int version) {
		boolean compare = true;
		boolean swapped = false;

		do {
			swapped = false;
			for (int i = 1; i < cards.length; i++) {

				// set up boolean values based on the version number
				if (version == 1) {
					compare = (cards[i-1].compareTo(cards[i]) > 0);
				}
				if (version == 2) {
					compare = (cards[i].compareTo(cards[i-1]) < 0);
				}

				if (compare) {
					AbstractCard tempCard = cards[i];
					// do the swap
					cards[i] = cards[i-1]; 
					cards[i-1] = tempCard;

					swapped = true;
				}
			}

			print(cards);

		} while (swapped == true);

		return cards;
	}

	/**
	 * Print an array of cards in a readable format
	 * @param cards the set of cards to print
	 */
	public static void print(AbstractCard[] cards) {
		System.out.println();
		System.out.print("[");
		for (int i = 0; i < cards.length; i++) {
			if (i < cards.length-1) {
				System.out.print(cards[i] + ", ");
			} else {
				System.out.print(cards[i] + "]");
			}
		}
	}
}
