


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AnagramSolver {

	private HashMap<String, LetterInventory> currentDictionary;
	
    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
    	if (dictionary == null) {
    		throw new IllegalArgumentException("Violation of precondtion: AnagramSolver"
    				+ " Parameter must not be null.");
    	}
    	//adds all words from dictionary to a map
    	currentDictionary = new HashMap<>();
    	for (String word: dictionary) {
    		currentDictionary.put(word, new LetterInventory(word));
    	}
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
    	if (maxWords < 0 || s == null || !containsEnglishLetter(s)) {
    		throw new IllegalArgumentException("Violation of precondition: getAnagrams."
    				+ " Parameter s must not be null and must contain an Englush letter."
    				+ " Parameter maxWords must be greater than or equal to 0");
    	}
    	//the LetterInventory of s
    	LetterInventory sInventory = new LetterInventory(s);
    	ArrayList<String> possibleWords = new ArrayList<>();
    	//finds initial possible words
    	for (String str: currentDictionary.keySet()) {
    		if (sInventory.subtract(currentDictionary.get(str)) != null) {
    			possibleWords.add(str);
    		}
    	}
    	//list of found anagrams
    	List<List<String>> anagrams = new ArrayList<>();
    	//current anagram
    	ArrayList<String> currentAnagram = new ArrayList<>();
    	findAnagrams(sInventory, possibleWords, anagrams, currentAnagram, maxWords);
    	for (List<String> gram: anagrams) {
    		Collections.sort(gram);
    	}
    	//sorts the list of anagrams
    	AnagramComparator c = new AnagramComparator();
    	Collections.sort(anagrams, c);
    	return anagrams;
    }
    
    /**
     * Helper method for getAragrams that finds all the anagrams of a given LetterInventory.
     */
    private void findAnagrams(LetterInventory currentLetters, ArrayList<String> possibleWords,
    		List<List<String>> anagrams, ArrayList<String> currentAnagram, int maxWords) {
		//base case if no more letters left
		if (currentLetters.isEmpty()) {
			//makes copy of current working anagram and adds to list of found anagrams
			ArrayList<String> foundAnagram = createDeepCopy(currentAnagram);
			anagrams.add(foundAnagram);
		//recursive step
		} else {
			for (int i = 0; i < possibleWords.size(); i++) {
				//tests if current letters contain letters of next possible word
				LetterInventory subtracted = currentLetters.subtract(
						currentDictionary.get(possibleWords.get(i)));
				//ensures next word can be part of current available letters
				//also checks if adding this word will make adding another word impossible
				//due to max word limit
				if (subtracted != null && (maxWords == 0 || !(subtracted.size() > 0 
							&& currentAnagram.size() == maxWords - 1))) {
					//updates the next anagram with the next word
					ArrayList<String> newAnagram = createDeepCopy(currentAnagram);
					newAnagram.add(possibleWords.get(i));
						//updates possible words
						ArrayList<String> newWords = createDeepCopy(
								new ArrayList<>(possibleWords.subList(i, possibleWords.size())));
						//next recursion call
						findAnagrams(subtracted, newWords, anagrams, newAnagram, maxWords);
				}
			}
		}
	}
    
    
    /**
     * Helper method for getAnagrams that creates a deep copy of a given ArrayList.
     * @param ogList, the ArrayList to copy.
     * @return a deep copy of the given ArrayList.
     */
    private ArrayList<String> createDeepCopy(ArrayList<String> ogList) {
    	ArrayList<String> newList = new ArrayList<>();
    	//add elements of parameter to new list
    	for (String word: ogList) {
    		newList.add(word);
    	}
    	return newList;
    }
    
    
    /**
     * Helper method for getAnagrams that finds if the given String contains an English letter.
     * @param s != null.
     * @return true if there is an English letter in the given String or false otherwise.
     */
    private boolean containsEnglishLetter(String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("Violation of precondtion: containsEnglishLetter"
    				+ " Parameter must not be null.");
    	}
    	//finds if any character is in the English alphabet
    	for (int i = 0; i < s.length(); i++) {
    		if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Helper class for AnagramSolver to sort the ArrayLists of anagrams in order.
     */
    private static class AnagramComparator implements Comparator<List<String>> {
    	
    	/**
    	 * Compares two lists returning an integer less than zero if the first list is
    	 * lexicographically greater than the second list, 0 if the lists are equal, and
    	 * an integer greater than zero if the first list is lexicographically less than 
    	 * the second list.
    	 */
        public int compare(List<String> a1, List<String> a2) {
        	//finds if lists are the same size
        	if (a1.size() != a2.size()) {
        		return a1.size() - a2.size();
        	}
        	//finds which list is smaller or bigger
        	for (int i = 0; i < a1.size(); i++) {
        		int result = a1.get(i).compareTo(a2.get(i));
        		if (result != 0) {
        			return result;
        		}
        	}
        	//if lists are equal
        	return 0;
        }
    }
}
