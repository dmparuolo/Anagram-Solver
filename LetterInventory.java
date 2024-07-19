public class LetterInventory {

	private int size;
	private final int ALPHABET_SIZE;
	private int[] frequencies;
	private final char CHAR_TO_INDEX;
	
	/**
	 * Creates a new LetterInventory object that takes in a String, finds the frequency
	 * of each letter in the String, storing it in an array, and finds the number of different
	 * letters in the String.
	 * @param phrase != null.
	 */
	public LetterInventory (String phrase) {
		if (phrase == null) {
			throw new IllegalArgumentException("Violation of precondition: LetterInventory."
					+ " Parameter must not be null.");
		}
		size = 0;
		//size of alphabet
		ALPHABET_SIZE = 26;
		frequencies = new int[ALPHABET_SIZE];
		//the ASCII value distance from the letters to there index in the array
		CHAR_TO_INDEX = 97;
		String newPhrase = phrase.toLowerCase();
		//increments frequency of letters and increments numLetters when new letters are added
		for (int i = 0; i < newPhrase.length(); i++) {
			char letter = newPhrase.charAt(i);
			if ('a' <= letter && letter <= 'z') {
				//increments frequencies
				frequencies[letter - CHAR_TO_INDEX] += 1;
				size++;
			}
		}
	}
	
	/**
	 * Finds the frequency of a letter in this LetterInventory and returns it.
	 * @param letter must be an English letter.
	 * @return the frequency of a given letter in this LetterInventory.
	 */
	public int get(char letter) {
		//converts char to lower case
		char newLetter = ("" + letter).toLowerCase().charAt(0);
		if (!('a' <= newLetter && newLetter <= 'z')) {
			throw new IllegalArgumentException("Violation of precondtion, get."
					+ " Parameter must be a letter of the English alphabet");
		}
		return frequencies[newLetter - CHAR_TO_INDEX];
	}
	
	/**
	 * Finds the number of letters in this LetterInventory.
	 * @return the number of letters in this LetterInventory.
	 */
	public int size() {
		//reset size
	    size = 0;
	    //find new size
	    for (int i = 0; i < frequencies.length; i++) {
	    	size += frequencies[i];
	    }
	    return size;
	}
	
	/**
	 * Finds if this LetterInventory is empty.
	 * @return true if this LetterInventory is empty, false otherwise.
	 */
	public boolean isEmpty() {
		//finds if empty
		return size == 0;
	}
	
	/**
	 * Creates a String form of every letter in this LetterInventory in alphabetical order.
	 * @return a String representation of this LetterInventory.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//adds chars to string builder
		for (int i = 0; i < frequencies.length; i++) {
			for (int j = 0; j < frequencies[i]; j++) {
				sb.append((char) (CHAR_TO_INDEX + i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * Creates a new LetterInventory containing both letters from this LetterInventory and another
	 * LetterInventory.
	 * @param otherInventory != null.
	 * @return a new LetterInventory containing the frequencies of this LetterInventory and the
	 * frequencies of the letters of the given LetterInventory added together.
	 */
	public LetterInventory add(LetterInventory otherInventory) {
		if (otherInventory == null) {
			throw new IllegalArgumentException("Violation of precondtion: add."
					+ " Parameter must not be null");
		}
		LetterInventory newInventory = new LetterInventory("");
		//adds frequencies from this inventory and the given inventory to new inventory
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			newInventory.frequencies[i] = frequencies[i] + otherInventory.frequencies[i];
			//increments size of new inventory
			newInventory.size += newInventory.frequencies[i];
		}
		return newInventory;
	}
	
	/**
	 * Creates a new LetterInventory containing the letters from another LetterInventory
	 * subtracted from this LetterInventory.
	 * @param otherInventory != null.
	 * @return a new LetterInventory containing the letter frequencies of the given
	 * LetterInventory from this LetterInventory's letter frequencies.
	 */
	public LetterInventory subtract(LetterInventory otherInventory) {
		if (otherInventory == null) {
			throw new IllegalArgumentException("Violation of precondtion: add."
					+ " Parameter must not be null");
		}
		LetterInventory newInventory = new LetterInventory("");
		//subtracts frequencies from the given inventory from this inventory
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			newInventory.frequencies[i] = frequencies[i] - otherInventory.frequencies[i];
			//ensures the frequency is not less than 0
			if (newInventory.frequencies[i] < 0) {
				return null;
			}
			//increments size of new inventory
			newInventory.size += newInventory.frequencies[i];
		}
		return newInventory;
	}
	
	/**
	 * Finds if two LetterInventory's have the same letter frequencies.
	 * Return true if both LetterInventory's have the same letter frequencies
	 * and false otherwise.
	 */
	public boolean equals(Object other) {
		//finds if given Object is null or if it is a LetterInventory
		if (other == null || !(other instanceof LetterInventory)) {
			return false;
		}
		//gets array of other frequencies
		int[] otherFrequencies = ((LetterInventory) other).frequencies;
		//compares frequencies and finds if any are different
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			if (frequencies[i] != otherFrequencies[i]) {
				return false;
			}
		}
		return true;
	}
}
