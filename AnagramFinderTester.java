import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {
    	
    	cs314StudentTestsForLetterInventory();
    	
        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver 
                = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }


    private static void cs314StudentTestsForLetterInventory() {
        //constructor tests
    	//test 1
    	LetterInventory l1 = new LetterInventory("a&&*((pp)(*^$#@#%le");
    	if (l1.get('p') == 2) {
    		System.out.println("Passed test 1: contructor.");
    	} else {
    		System.out.println("Failed test 1: contructor.");
    	}
    	//test 2
    	if (l1.size() == 5) {
    		System.out.println("Passed test 2: contructor.");
    	} else {
    		System.out.println("Failed test 2: contructor.");
    	}
    	
    	//get tests
    	//test 1
    	if (l1.get('z') == 0) {
    		System.out.println("\nPassed test 1: get.");
    	} else {
    		System.out.println("\nFailed test 1: get.");
    	}
    	//test 2
    	if (l1.get('a') == 1) {
    		System.out.println("Passed test 2: get.");
    	} else {
    		System.out.println("Failed test 2: get.");
    	}
    	
    	//size tests
    	//test 1
    	l1 = new LetterInventory("orange");
    	if (l1.size() == 6) {
    		System.out.println("\nPassed test 1: size.");
    	} else {
    		System.out.println("\nFailed test 1: size.");
    	}
    	//test 2
    	l1 = new LetterInventory("pear");
    	if (l1.size() == 4) {
    		System.out.println("Passed test 2: size.");
    	} else {
    		System.out.println("Failed test 2: size.");
    	}
    	
    	//isEmpty tests
    	//test 1
    	if (!l1.isEmpty()) {
    		System.out.println("\nPassed test 1: isEmpty.");
    	} else {
    		System.out.println("\nFailed test 1: isEmpty.");
    	}
    	//test 2
    	l1 = new LetterInventory("&&^%$@^!");
    	if (l1.isEmpty()) {
    		System.out.println("Passed test 2: isEmpty.");
    	} else {
    		System.out.println("Failed test 2: isEmpty.");
    	}
    	
    	//toString tests
    	//test 1
    	if (l1.toString().equals("")) {
    		System.out.println("\nPassed test 1: toString.");
    	} else {
    		System.out.println("\nFailed test 1: toString.");
    	}
    	//test 2
    	l1 = new LetterInventory("dragonfruit");
    	if (l1.toString().equals("adfginorrtu")) {
    		System.out.println("Passed test 2: toString.");
    	} else {
    		System.out.println("Failed test 2: toString.");
    	}
    	
    	//add tests
    	//test 1
    	LetterInventory l2 = new LetterInventory("dragon");
    	LetterInventory l3 = new LetterInventory("fruit");
    	if (l2.add(l3).equals(l1)) {
    		System.out.println("\nPassed test 1: add.");
    	} else {
    		System.out.println("\nFailed test 1: add.");
    	}
    	//test 2
    	l2 = new LetterInventory("");
    	l3 = new LetterInventory("");
    	if (l2.add(l3).equals(l2)) {
    		System.out.println("Passed test 2: add.");
    	} else {
    		System.out.println("Failed test 2: add.");
    	}
    	
    	//subtract tests
    	//test 1
    	l1 = new LetterInventory("olives");
    	l2 = new LetterInventory("olive");
    	l3 = new LetterInventory("s");
    	if (l1.subtract(l2).equals(l3)) {
    		System.out.println("\nPassed test 1: subtract.");
    	} else {
    		System.out.println("\nFailed test 1: subtract.");
    	}
    	//test 2
    	if (l2.subtract(l1) == null) {
    		System.out.println("Passed test 2: subtract.");
    	} else {
    		System.out.println("Failed test 2: subtract.");
    	}
    	
    	//equals tests
    	//test 1
    	if (!l1.equals(l2)) {
    		System.out.println("\nPassed test 1: equals.");
    	} else {
    		System.out.println("\nFailed test 1: equals.");
    	}
    	//test 2
    	l1 = new LetterInventory("god");
    	l2 = new LetterInventory("dog");
    	if (l1.equals(l2)) {
    		System.out.println("Passed test 2: equals.");
    	} else {
    		System.out.println("Failed test 2: equals.");
    	}
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0 
                && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual, 
            int testNum, String featureTested) {
        
        System.out.println("Test Number " + testNum + " testing " 
                + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) 
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, 
            boolean displayAnagrams) {
        
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams 
                    = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                /* System.out.println("Number of calls to recursive helper method: " 
                        + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));*/
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " 
                        + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for (List<String> singleAnagram : anagrams) {
            System.out.println(singleAnagram);
        }
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " 
                    + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " 
                        + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " 
                        + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, "
                        + "but either a difference in anagrams or"
                        + " anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams 
                    : "Wrong number of angrams read or expected";
        }
    }
}
