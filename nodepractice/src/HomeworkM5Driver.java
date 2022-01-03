import java.util.*;

public class HomeworkM5Driver {

    private static boolean allTestsPassed = true;

    public static void printAtInterval(Node<String> firstNode, int interval) {
        if(firstNode==null){
            System.out.println("");
        } else if (firstNode.next==null){
            System.out.print(firstNode.getData());
        } else {
            Node nextNodeToPrint = firstNode;
            while(!(nextNodeToPrint==null)){
                System.out.print(nextNodeToPrint.getData()+" ");
                for(int i = 0; i<interval;i++){
                    if(nextNodeToPrint.next==null){
                        nextNodeToPrint = null;
                        break;
                    }
                    nextNodeToPrint = nextNodeToPrint.next;
                }
            }
        }

    }

    public static boolean isDoublyLinkedPalindromeList(DoubleNode<Integer> firstNode, DoubleNode<Integer> lastNode)  {

        if(firstNode==null || lastNode==null){
            return true;
        }

        int lengthOfChain = 0;

        DoubleNode currentNode = firstNode;

        if(!(currentNode.data==null)){
            lengthOfChain++;
        }
        while(!(currentNode.next==null)){
            lengthOfChain++;
            currentNode = currentNode.next;
        }

        if(lengthOfChain==1){
            return true;
        }
        System.out.println("length of chain "+lengthOfChain);
        int requiredComparisions = lengthOfChain/2;

        DoubleNode startCompare = firstNode;
        DoubleNode endComapre = lastNode;
        for(int i =0;i<requiredComparisions;i++) {
            if(!(startCompare.data.equals(endComapre.data))){
                return false;
            }
            startCompare = startCompare.next;
            endComapre = endComapre.previous;
        }
        return true;
    }



    public static void main(String[] args) {




        System.out.println("\n-----------------------------TESTING printAtInterval METHOD-----------------------------");
        System.out.println("-----Note: there are no automated tests for this method. You must visually compare the outputs.");
        // parameter 1: an array that represents the chain contents;
        // 				the element at array index 0 is the contents of firstNode in the chain;
        //				for example: {"a", "b", "c"} represents the chain a->b->c
        // parameter 2: the interval at which to print
        // parameter 3: the expected output
        testPrintAtInterval(new String[] {"a", "b", "c", "d"}, 		2, 	"a c");
        testPrintAtInterval(new String[] {"a", "b", "c", "d"}, 		3, 	"a d");
        testPrintAtInterval(new String[] {"a", "b", "c", "d"}, 		4, 	"a");
        testPrintAtInterval(new String[] {"c", "d", "e", "f", "g"}, 2, 	"c e g");
        testPrintAtInterval(new String[] {"c", "d", "e", "f", "g"}, 3, 	"c f");
        testPrintAtInterval(new String[] {"a", "b", "c", "d", "e", "f", "g", "h"}, 3, "a d g");
        testPrintAtInterval(new String[] {"q", "r", "s", "t"}, 		5, 	"q");
        testPrintAtInterval(new String[] {"q", "r", "s", "t"}, 		1, 	"q r s t");
        testPrintAtInterval(new String[] {}, 						1, 	"");
        testPrintAtInterval(new String[] {"x"}, 					1, 	"x");
        testPrintAtInterval(new String[] {"x"}, 					3,	"x");
        testPrintAtInterval(new String[] {"x"}, 					4, 	"x");


        System.out.println("\n-----------------------------TESTING removeAll IN LinkedBag METHOD-----------------------------");
        System.out.println("-----Note: the expected and actual bag contents do not have to have the same order- only the same contents.");

        // parameter 1: the contents of the LinkedBag
        // parameter 2: the item to remove all of
        // parameter 3: a description of the test
        testRemoveAll(new Integer[] {}, 			1, 		"empty bag");

        testRemoveAll(new Integer[] {1}, 			1, 		"singleton bag with matching element");
        testRemoveAll(new Integer[] {2}, 			1, 		"singleton bag without matching element");

        testRemoveAll(new Integer[] {2, 2, 2}, 		2, 		"odd sized bag with all matches");
        testRemoveAll(new Integer[] {3, 3, 3, 3}, 	3, 		"even sized bag with all matches");

        testRemoveAll(new Integer[] {3, 4, 5}, 		5, 		"bag with one match added last");
        testRemoveAll(new Integer[] {2, 3, 4, 5}, 	5, 		"bag with one match added last");
        testRemoveAll(new Integer[] {3, 4, 5}, 		3, 		"bag with one match added first");
        testRemoveAll(new Integer[] {3, 4, 5}, 		4, 		"bag with one match added in the middle");

        testRemoveAll(new Integer[] {1, 1, 2, 4, 1, 5, 1}, 			1, 	"bag with some matches");
        testRemoveAll(new Integer[] {1, 1, 3, 1, 1, 4, 1, 5, 1, 1}, 1, 	"bag with some matches");

        testRemoveAll(new Integer[] {1, 1, 3, 1, 4, 1, 5, 1}, 		6,	"bag with no matches");

        testRemoveAll(new String[] {"a", "b", "c"}, 				new String("a"), 	"bag with Strings");

        System.out.println("\nTesting efficiency: The elapsed time should be probably < 100.");
        System.out.println("If the elapsed time is much higher than that, you might revisit your code to see if you have a nested loop.");
        LinkedBag<Integer> bigBag = new LinkedBag<Integer>();
        for(int i=0; i<100000; i++) {
            bigBag.add(99);
        }
        for(int i=0; i<100000; i++) {
            bigBag.add(43);
        }
        long startTimeBag = System.currentTimeMillis();
        bigBag.removeAll(99);
        long stopTimeBag = System.currentTimeMillis();
        System.out.println("Elapsed time = " + (stopTimeBag - startTimeBag));


        System.out.println("\n-----------------------------TESTING QUESTION: LLIST GET MAX-----------------------------");
        // parameter 1: the contents to add to an originally empty list
        // parameter 2: a description of the test
        testGetMax(new Integer[]{1, 2, 1, 4, 3}, 	"max in the middle");
        testGetMax(new Integer[]{1, 2, 1, 4, 3, 1}, "max in the middle");
        testGetMax(new Integer[]{1, 1, 4, 3, 1, 4}, "duplicate max");
        testGetMax(new Integer[]{1, 4, 3, 1, 4, 6}, "max at the end");
        testGetMax(new Integer[]{9, 1, 2, 1, 4, 3}, "max at the beginning");
        testGetMax(new Integer[]{7}, 				"singleton");
        testGetMax(new Integer[]{}, 				"empty list");
        testGetMax(new String[]{"a", "m", "z"}, 		  	"list with Strings (earlier letters are considered smaller)");
        testGetMax(new String[]{"z", "e", "b", "r", "a"}, 	"list with Strings (earlier letters are considered smaller)");
        testGetMax(new String[]{"l", "a", "w", "n"}, 		"list with Strings (earlier letters are considered smaller)");


        System.out.println("\n-----------------------------TESTING QUESTION: LLIST ADD ALL-----------------------------");
        // parameter 1: the contents of the LList
        // parameter 2: the contents of the array to add to the end of the LList
        // parameter 3: a description of the test
        testAddAll(new String[]{}, 					new String[]{"peach"}, 			"add a 1-element array to an empty list");
        testAddAll(new String[]{}, 					new String[]{"banana", "date", "grape", "eggplant",	"jicama", "grape"}, 	"add multiple elements to an empty list");
        testAddAll(new String[]{"apple", "peach"}, 	new String[]{"banana", "date", "grape", "eggplant",	"jicama", "grape" }, 	"add an array to a non-empty list");
        testAddAll(new String[]{"peach"}, 			new String[]{}, 				"add an empty array to a non-empty list");
        testAddAll(new String[]{}, 					new String[]{}, 				"add an empty array to an empty list");
        testAddAll(new Integer[]{1, 2, 3}, 			new Integer[]{4, 5, 6}, 		"test with Integers");

        // Efficiency Test 1
        LList<Integer> bigList = new LList<Integer>();
        Integer[] bigArray = new Integer[100000];
        for(int i=0; i<bigArray.length; i++) {
            bigArray[i] = 99;
        }
        System.out.println("\nTesting Efficiency Part 1: Adding all elements to an initially empty list.");
        System.out.println("The elapsed time should be probably < 100.");
        System.out.println("If the elapsed time is much higher than that, you might revisit your code to see if you have a nested loop.");
        long startTimeList = System.currentTimeMillis();
        bigList.addAll(bigArray);
        long stopTimeList= System.currentTimeMillis();
        System.out.println("Elapsed time = " + (stopTimeList - startTimeList));

        // Efficiency Test 2
        bigList = new LList<Integer>();
        for(int i=0; i<50000; i++) {
            bigList.add(1, 1);
        }
        bigArray = new Integer[50000];
        for(int i=0; i<bigArray.length; i++) {
            bigArray[i] = 99;
        }
        System.out.println("\nTesting Efficiency Part 2: Adding all elements to a non-empty list.");
        System.out.println("The elapsed time should be probably < 100.");
        System.out.println("If the elapsed time is much higher than that, you might revisit your code to see if you have a nested loop.");
        startTimeList = System.currentTimeMillis();
        bigList.addAll(bigArray);
        stopTimeList= System.currentTimeMillis();
        System.out.println("Elapsed time = " + (stopTimeList - startTimeList));


        System.out.println("\n-----------------------------TESTING QUESTION EXTRA CREDIT: DOUBLE NODE PALINDROME-----------------------------");
        // parameter 1: the contents of the doubly-linked chain
        // parameter 2: the expected result (true if the chain is a palindrome, false otherwise)
        // parameter 3: a description of the test
        testPalindrome(new Integer[] {4, 3, 4, 3, 4}, 		true, 	"odd length palindrome");
        testPalindrome(new Integer[] {1, 2, 2, 1}, 			true, 	"even length palindrome");
        testPalindrome(new Integer[] {5, 6}, 				false, 	"even length non-palindrome");
        testPalindrome(new Integer[] {1, 3, 2, 1}, 			false, 	"even length non-palindrome");
        testPalindrome(new Integer[] {3, 4, 3, 4}, 			false, 	"even length non-palindrome");
        testPalindrome(new Integer[] {4, 3, 3, 2, 4}, 		false, 	"odd length non-palindrome");
        testPalindrome(new Integer[] {4, 2, 1, 2, 3}, 		false, 	"odd length non-palindrome");
        testPalindrome(new Integer[] {4, 3, 2, 1, 0, 3, 4}, false, 	"odd length non-palindrome");
        testPalindrome(new Integer[] {}, 					true, 	"empty palindrome");
        testPalindrome(new Integer[] {5}, 					true, 	"singleton palindrome");

        System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
        if(allTestsPassed) {
            System.out.println("----------Summary---------- \nAll automated tests have passed. \nBe sure to manually look at the output f the printInterval method- there are no automated tests for that method.\nAlso be sure to manually review your code for style and efficiency.");
        } else {
            System.out.flush();
            System.err.println("**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
        }

    }


    /*----------------------------------------------------------------------------------------------------------*/
    /* TESTER METHODS */
    /*----------------------------------------------------------------------------------------------------------*/
    /*
     * The methods below are designed to help support the tests cases run from main. You don't
     * need to use, modify, or understand these methods. You can safely ignore them. :)
     *
     * Also, you can ignore the use of generics in the tester methods. These methods use
     * generics at a level beyond which we use in our class. I only use them here to make this a robust
     * and useful testing file. You are NOT required to understand the use of generics in this way.
     */

    public static void testPrintAtInterval(String[] chainContents, int interval, String expectedOutput) {
        List<Node<String>> nodeList = new ArrayList<Node<String>>();
        Node<String> firstNode = null;
        for(String s : chainContents) {
            nodeList.add(new Node<String>(s));
        }
        for(int i=0; i<nodeList.size()-1; i++) {
            nodeList.get(i).next = nodeList.get(i+1);
        }
        if(!nodeList.isEmpty()) {
            firstNode = nodeList.get(0);
        }

        System.out.print("\nChain: \t\t\t");
        for(int i=0; i<chainContents.length; i++) {
            System.out.print(chainContents[i]);
            if(i<chainContents.length-1) {
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println("Print at interval: \t" + interval);
        System.out.println("Expected output:\t" + expectedOutput);
        System.out.print("Actual output:  \t");
        try {
            printAtInterval(firstNode, interval);
        } catch(NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("\n**********TEST FAILED with an a NullPointerException.");
            System.out.println("       A NullPointerException was caught. This is an error because the code should not crash in that way.");
            allTestsPassed = false;
        }
    }
    public static <T extends Comparable<? super T>> void testRemoveAll(T[] valuesToAdd, T valueToRemove, String testDescription) {
        LinkedBag<T> originalBag = new LinkedBag<T>();
        LinkedBag<T> resultsBag = new LinkedBag<T>();
        for(T valueToAdd : valuesToAdd) {
            originalBag.add(valueToAdd);
            if(!valueToAdd.equals(valueToRemove)) {
                resultsBag.add(valueToAdd);
            }
        }
        int beforeRemoveSize = originalBag.getCurrentSize();
        int expectedAfterSize = resultsBag.getCurrentSize();

        int expectedNumberRemoved = beforeRemoveSize - expectedAfterSize ;

        Arrays.sort(valuesToAdd);

        T[] expectedAfterArray = resultsBag.toArray();
        System.out.println("\nBag before removing " + valueToRemove + ":\t " + Arrays.toString(valuesToAdd) + "\tSize=" + beforeRemoveSize);
        System.out.println("Expected bag after:\t " + Arrays.toString(expectedAfterArray) + "\tSize=" + resultsBag.getCurrentSize());

        int actualNumberRemoved = -1;
        try {
            actualNumberRemoved = originalBag.removeAll(valueToRemove);
        } catch(NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("\n**********TEST FAILED with an a NullPointerException.");
            System.out.println("       A NullPointerException was caught. This is an error because the code should not crash in that way.\n");
            allTestsPassed = false;
        }

        T[] actualAfterArray = originalBag.toArray();

        System.out.println("  Actual bag after:\t " + Arrays.toString(actualAfterArray) + "\tSize=" + originalBag.getCurrentSize());
        System.out.println("Expected number removed = " + expectedNumberRemoved);
        System.out.println("  Actual number removed = " + actualNumberRemoved);

        Arrays.sort(actualAfterArray);
        Arrays.sort(expectedAfterArray);
        if(expectedNumberRemoved!=actualNumberRemoved) {
            System.out.println("**********TEST FAILED for: " + testDescription);
            System.out.println("          Error: Incorrect return value (the number removed).");
            allTestsPassed = false;
        }
        if(originalBag.getCurrentSize()!=expectedAfterSize) {
            System.out.println("**********TEST FAILED for: " + testDescription);
            System.out.println("          Error: Updated bag is the incorrect size.");
            allTestsPassed = false;
        }
        if(!Arrays.equals(actualAfterArray,  expectedAfterArray)) {
            System.out.println("**********TEST FAILED for: " + testDescription);
            System.out.println("          Error: Updated bag does not have the expected contents.");
            allTestsPassed = false;
        }
    }
    public static <T extends Comparable<? super T>> void testGetMax(T[] valuesToAdd, String testDescription) {
        LList<T> list = new LList<T>();
        ArrayList<T> arrayList = new ArrayList<T>();
        for(T valueToAdd : valuesToAdd) {
            list.add(valueToAdd);
            arrayList.add(valueToAdd);
        }
        if(list.isEmpty()) {
            try {
                System.out.println("\nList contents: " + Arrays.toString(valuesToAdd));
                System.out.println("Expected max = " + null);
                T actualMax = list.getMax();

                System.out.println("Actual   max = " + actualMax);

                if(actualMax!=null) {
                    System.out.println("**********TEST FAILED for: " + testDescription);
                    allTestsPassed = false;
                }
            } catch(Exception ex) {
                if(ex.getClass().equals(NullPointerException.class)) {
                    System.out.println("**********TEST FAILED for: " + testDescription);
                    System.out.println("          Error: exception type should not be NullPointerException");
                    allTestsPassed = false;
                }
            }
        } else { // list is NOT empty
            T expectedMax = Collections.max(arrayList);
            T actualMax = list.getMax();

            System.out.println("\nList contents: " + Arrays.toString(valuesToAdd));
            System.out.println("Expected max = " + expectedMax);
            System.out.println("Actual   max = " + actualMax);

            if (!expectedMax.equals(actualMax)) {
                System.out.println("**********TEST FAILED for: " + testDescription);
                allTestsPassed = false;
            }
            if (!Arrays.equals(list.toArray(), arrayList.toArray())) {
                System.out.println("**********TEST FAILED for: " + testDescription);
                System.out.println("          Error: getMax method should not change the contents of the list.");
                System.out.println("          Contents after invoking getMax: " + Arrays.toString(list.toArray()));
                allTestsPassed = false;
            }
        }
    }
    public static <T extends Comparable<? super T>> void testAddAll(T[] initialListContents, T[] arrayToAdd, String testDescription) {
        LList<T> originalList = new LList<T>();
        LList<T> resultList = new LList<T>();
        for(T valueToAdd : initialListContents) {
            originalList.add(valueToAdd);
            resultList.add(valueToAdd);
        }
        for(T valueToAdd : arrayToAdd) {
            resultList.add(valueToAdd);
        }
        originalList.addAll(arrayToAdd);
        System.out.println("\nInitial List: \t\t " +  Arrays.toString(initialListContents));
        System.out.println("Array to add to the end: " + Arrays.toString(arrayToAdd));
        System.out.println("Expected result: \t " + Arrays.toString(resultList.toArray()));
        System.out.println("Actual   result: \t " + Arrays.toString(originalList.toArray()));


        if(!originalList.equals(resultList)) {
            System.out.println("**********TEST FAILED for: " + testDescription);
            allTestsPassed = false;
        }
    }
    public static void testPalindrome(Integer[] chainContents, boolean expectedResult, String testDescription) {
        ArrayList<DoubleNode<Integer>>nodeList = new ArrayList<DoubleNode<Integer>>();
        for(Integer valueToAdd : chainContents) {
            nodeList.add(new DoubleNode<Integer>(valueToAdd));
        }
        Collections.reverse(nodeList);
        DoubleNode<Integer> firstNode = null;
        DoubleNode<Integer> lastNode = null;
        if(!nodeList.isEmpty()) {
            firstNode = nodeList.get(0);
            lastNode = nodeList.get(nodeList.size()-1);
            if(nodeList.size()>1) {
                for (int i=0; i<nodeList.size(); i++) {
                    if (i==0 ) {
                        nodeList.get(i).next = nodeList.get(i+1);
                    } else if (i==nodeList.size()-1) {
                        nodeList.get(i).previous = nodeList.get(i-1);
                    } else {
                        nodeList.get(i).next = nodeList.get(i+1);
                        nodeList.get(i).previous = nodeList.get(i-1);
                    }
                }
            }
        }

        System.out.println("\nChain contents: " +  Arrays.toString(chainContents));
        boolean actualResult = isDoublyLinkedPalindromeList(firstNode, lastNode);
        System.out.println("Palindrome? Expected result = " + expectedResult);
        System.out.println("Palindrome?   Actual result = " + actualResult);
        if (actualResult!=expectedResult) {
            System.out.println("**********TEST FAILED for: " + testDescription);
            allTestsPassed = false;
        }
    }
}
