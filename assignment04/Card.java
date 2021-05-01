/**
 * <i>Extends ~ AbstractCard</i>
 * <p>
 * Given a rank and suit, or a simple string ...
 * 		this program will create a card object
 * 		with this.rank and this.suit

 *	@StringInputExamples
		"2S" => RANK.TWO & SUIT.SPADES
		<li>"AH" => RANK.ACE & SUIT.HEARTS
		<li>"garbage string" => RANK.JOKER & SUIT.NONE
 *	@EnumInputExamples
		RANK.TWO, SUIT.SPADES
		<li>RANK.JOKER, SUIT.HEARTS
		<li>RANK.JOKER, SUIT.NONE
		<li>RANK.KING, SUIT.CLUBS

 * @author Karl Damus
 * @version 2.5

 * @see AbstractCard
 * @see RANK 
 * @see SUIT
 */

public class Card extends AbstractCard {

	private static final RANK[] allRanks = new RANK[] {
		RANK.TWO, RANK.THREE, RANK.FOUR, 
		RANK.FIVE, RANK.SIX, RANK.SEVEN, 
		RANK.EIGHT, RANK.NINE, RANK.TEN, 
		RANK.JACK, RANK.KING, RANK.QUEEN, 
		RANK.ACE, RANK.JOKER
	};

	private static final SUIT[] allSuits = new SUIT[] {
		SUIT.SPADES, SUIT.HEARTS, 
		SUIT.CLUBS, SUIT.DIAMONDS,
		SUIT.NONE
	};

	private static final String[] allRanksAsStrings = new String[] {
		"2", "3", "4", "5", "6", "7", "8", "9", "10", 
		"J", "K", "Q", "A", "Joker"
	};

	private static final String[] allSuitsAsStrings = new String[] {
		"S", "H",
		"C", "D"
	};

	/**
	 * * CONSTRUCTORS
	 * 		Card(RANK, SUIT)
	 * 		Card(String)
	 */

	/**
	 * Create a new card object given a RANK and SUIT enum

	 * @param rank the RANK enum of the card
	 * @param suit the SUIT enum of the card

	 * @see #jokerSwap
	 * @see AbstractCard#AbstractCard(RANK, SUIT)
	 */
	public Card(RANK rank, SUIT suit) {
		super(rank, jokerSwap(rank, suit));
	}

	/**
	 * Create a new card object given a single String representation
			of a card. 

	 * @param card (string): the card in string format

	 * @see #getRankFromCardAsString
	 * @see #getSuitFromCardAsString
	 * @see AbstractCard#AbstractCard(RANK, SUIT)
	 */
	public Card(String card) {
		super(getRankFromCardAsString(card), getSuitFromCardAsString(card));
	}

	/**
	 * * METHODS: OBJECTS FROM STRINGS
	 * 		getObjectFromCardAsString(. . .)
	 * 		getRankFromCardAsString(String)
	 * 		getSuitFromCardAsString(String)
	 */

	 /**
	  * <p> This method is the framework for 
	  			{@link #getRankFromCardAsString} 
				and {@link #getSuitFromCardAsString}.
	  * <p> This function works with both ranks and suits. 
	  			It takes a string and turns it into an object,
				either rank or suit.
	  *	<p> If the Object we are looking for is a RANK, then we remove the 
	  			last character of String card. We can do this because the
				last character of the String is <b>always</b> the suit (exception: joker)
		<p> If the Object we are looking for is a SUIT, then we can use
				the last character of String card. 

	  * @param card (string): the card in string format
	  * @param strArray (array): the array where the <i>card</i> can be found
	  * @param objArray (array): the array where the object version of the <i>card</i> can be found
	  * @param isRank (boolean): lets the method know whether to remove the last character (rank) 
	  							 or use the last character (suit)

	  * @return RANK.JOKER or SUIT.NONE; if the card can not be found in objArray
	  			<li> The Object variant of the rank or suit; if found in objArray

	  * @see #getRankFromCardAsString
	  * @see #getSuitFromCardAsString
	  * @see #removeLastCharFromString
	  * @see #getLastCharAsString
	  * @see #getIndex
	  */
	public static Object getObjectFromCardAsString(String card, String[] strArray, Object[] objArray, boolean isRank) {
		String objAsString;
		
		if (isRank) {
			objAsString = removeLastCharFromString(card); // get the rank value by removing the 
		} else {
			objAsString = getLastCharAsString(card);
		}

		int index = getIndex((Object[]) strArray, (Object) objAsString);

		if (index == -1) {
			if (isRank) {
				return RANK.JOKER;
			} else {
				return SUIT.NONE;
			}
		} else {
			return objArray[index];
		}
	}

	/**
	 * Uses {@link #getObjectFromCardAsString}.

	 * @param card (string): the card in string format,
	 * 				will convert to upper case to work
	 * 				with String[] {@link #allRanksAsStrings}

	 * @return RANK.JOKER; if rank not found (based on String card)
	  		   <li> The associated {@link RANK} enum; if found in {@link #allRanks}
	 */
	public static RANK getRankFromCardAsString(String card) {
		card = card.toUpperCase();
		return (RANK) getObjectFromCardAsString(card, allRanksAsStrings, allRanks, true);
	}

	/**
	 * Uses {@link #getObjectFromCardAsString}.

	 * @param card (string): the card in string format, 
	 * 				will convert to upper case to work
	 * 				with String[] {@link #allSuitsAsStrings}
	
	 * @return SUIT.NONE; if suit not found (based on String card)
	  		   <li> The associated {@link SUIT} enum; if found in {@link #allSuits}
	 */
	public static SUIT getSuitFromCardAsString(String card) {
		card = card.toUpperCase();
		return (SUIT) getObjectFromCardAsString(card, allSuitsAsStrings, allSuits, false);
	}

	/**
	 * * METHODS: STRINGS FROM OBJECTS
	 * 		getStringFromObj(. . .)
	 * 		getStringFromRank(RANK)
	 * 		getStringFromSuit(SUIT)
	 */

	 /**
	  * The opposite of {@link #getObjectFromCardAsString}. 
	  		<p> This method takes a {@link RANK} or {@link SUIT} enum 
			  	and turns it back into a String.

	  * @param obj (object): the RANK or SUIT to convert
	  * @param objArray (array): the array where the <i>obj</i> can be found
	  * @param strArray (array): the array where the String version of the obj can be found

	  * @return "Joker"; if the obj can not be found in objArray
				The String variant of the rank or suit; if found in objArray
	  */
	public static String getStringFromObj(Object obj, Object[] objArray, String[] strArray) {
		int index = getIndex(objArray, obj);

		if (index == -1) {
			return "Joker";
		} else {
			return strArray[index];
		}
	}

	public static String getStringFromRank(RANK rank) {
		return getStringFromObj(rank, allRanks, allRanksAsStrings);
	}

	public static String getStringFromSuit(SUIT suit) {
		return getStringFromObj(suit, allSuits, allSuitsAsStrings);
	}

	/**
	 * * HELPER METHODS
	 * 		removeLastCharFromString(String)
	 * 		getLastCharAsString(String)
	 * 		getIndex(Object[], Object)
	 * 		jokerSwap(RANK, SUIT)
	 */

	/**
     * Removes the last character from a string, 
	 * 		used to get the rank of the card by itself
	 * 
     * @param string the string to be altered
	 * 
     * @return the string w/out last character ex: (strings => string)
	 * 
	 * @see #getObjectFromCardAsString
     */
    public static String removeLastCharFromString(String string) {
        return string.substring(0, string.length() - 1);
    }

	/**
     * Get the last character from a string, 
	 * 		used to get the suit of the card by itself
	 * 
     * @param string the string to be altered
	 * 
     * @return the last character of @param string as a String
	 * 
	 * @see #getObjectFromCardAsString
     */
	public static String getLastCharAsString(String string) {
		return string.substring(string.length() - 1);
	}

	/**
	 * Get the index of an obj in an array.
	 * <p> This method is used to find 
	 * 		corresponding String-Obj 
	 * 		and Obj-String values.
	 * <p> This method is also used to
	 * 		evaluate the hierarchy of cards
	 * 		the lower the index, the lesser the value
	 * 
	 * @param array the array we are searching in
	 * @param obj the object we are looking for
	 * 
	 * @return the index of obj; if found
	 * 			<li> -1; if obj not found
	 * 
	 * @see #getObjectFromCardAsString
	 * @see #compareTo
	 */
	public static int getIndex(Object[] array, Object obj) {
		for (int i = 0; i < array.length; i++) {
			if (((Object) obj).equals((Object) array[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Small helper function used by {@link #NewCard2(RANK, SUIT)}. 
	 * <p> Method simply ensures that a joker card
	 * 		has the correct suit (NONE) associated with it.
	 * 
	 * @param rank the rank of the obj
	 * @param suit the suit of the obj
	 * 
	 * @return SUIT.NONE; if card is a joker (overwrite)
	 * 			<li> suit; if card is NOT a joker (no change)
	 * 
	 * @see #NewCard2(RANK, SUIT)
	 */
	public static SUIT jokerSwap(RANK rank, SUIT suit) {
		if (rank == RANK.JOKER) {
			return SUIT.NONE;
		} else {
			return suit;
		}
	}

	/**
	 * OVERRIDEN METHODS
	 * 		compareTo(AbstractCard)
	 * 		toString()
	 */

	/**
	 * Let <i>card1</i> and <i>card2</i> be in the example
	 * 		card1.compareTo(card2)
	 * 
	 * @Hierarchy 
	 * 			<i>Diamonds, Clubs, Hearts, 
	 * 			Spades, 2, ..., Jack, 
	 * 			King, Queen, Ace, Joker</i>
	 * @return
	 * 		1 if card1 > card2<li> 
	 * 		-1 if card1 < card2<li> 
	 * 		0 if card1 == card2
	 * 
	 * @see AbstractCard#compareTo
	 */
	public int compareTo(AbstractCard card) {
		// value of cards based on index in array
		int suitValueOfThis = getIndex(allSuits, this.suit); 
		int rankValueOfThis = getIndex(allRanks, this.rank);
		int suitValueOfCard = getIndex(allSuits, card.suit);
		int rankValueOfCard = getIndex(allRanks, card.rank);

		if (suitValueOfThis > suitValueOfCard) {
			return 1;
		} else if (suitValueOfThis < suitValueOfCard) {
			return -1;
		} else {
			// both cards have the same suit
			if (rankValueOfThis > rankValueOfCard) {
				return 1;
			} else if (rankValueOfThis < rankValueOfCard) {
				return -1;
			} else {
				// both cards are exactly the same
				return 0;
			}
		}
	}

	/**
	 * Return a card object in a readable format. 
	 * 
	 * @return "Joker"; if RANK == JOKER or SUIT == NONE
	 * 			<li> else; this.rank + this.suit
	 * 
	 * @see #getStringFromRank
	 * @see #getStringFromSuit
	 */
	public String toString() {
		if (this.rank == RANK.JOKER || this.suit == SUIT.NONE) {
			return "Joker";
		} else {
			return getStringFromRank(this.rank) + getStringFromSuit(this.suit);
		}
	}

}