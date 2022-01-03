import java.util.*;

public class HomeworkM3Driver {

    private static boolean allTestsPassed = true;

    public static void main(String[] args) {

        System.out.println("-----------------------------TESTING Comparable AND compareTo-----------------------------");
        Person p1 = new Person("Chip", "Munk", 123);			Person p2 = new Person("Trina", "Woods", 234);
        Person p3 = new Person("Trina", "Forest", 345);			Person p4 = new Person("holly", "wood", 456);
        Person p5 = new Person("Holly", "McRel", 567);			Person p6 = new Person("chip", "munk", 678);
        Person p7 = new Person("Holly", "Wood", 789);			Person p8 = new Person("anne", "teak", 890);
        Person p9 = new Person("Terry", "Bull", 901); 			Person p10 = new Person("Eddy", "Bull", 902);
        Person p11 = new Person("anne", "teak", 111);
        Person[] people =  {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
        Person[] sortedPeople = {p10, p9, p3, p5, p1, p6, p11, p8, p4, p7, p2};
        System.out.println("Before sorting, array order is:");
        System.out.println(Arrays.toString(people));
        Arrays.sort(people);
        System.out.println("\nArray is now sorted.");
        System.out.println("Expected array: " + Arrays.toString(sortedPeople));
        System.out.println("  Actual array: " + Arrays.toString(people));
        if(!Arrays.equals(people,  sortedPeople)) {
            allTestsPassed = false;
            System.out.println("***Test failed: array not sorted correctly.");
        }


        System.out.println("\n\n-----------------------------TESTING containsDuplicates(BagInterface) METHOD-----------------------------");
        // parameter 1: the contents being added to the bag
        // parameter 2: the expected result (true if the bag contains duplicates, false otherwise)
        // parameter 3: a description of the test
        testBagContainsDuplicates(new String[] {}, 							   false, "empty bag");
        testBagContainsDuplicates(new String[] {"hi"}, 						   false, "singleton bag");
        testBagContainsDuplicates(new String[] {"hi", "HI"}, 				   false, "bag with no duplicates");
        testBagContainsDuplicates(new String[] {"a", "b", "c", "d", "e", "f"}, false, "bag with no duplicates");
        testBagContainsDuplicates(new String[] {"a", "b", "d", "e", "c", "c"}, true,  "bag with duplicates both added last");
        testBagContainsDuplicates(new String[] {"a", "c", "b", "d", "a"}, 	   true,  "bag with duplicates added first and last");
        testBagContainsDuplicates(new String[] {"a", "b", "b", "c"}, 		   true,  "bag with duplicates added in the middle");
        testBagContainsDuplicates(new String[] {"a", "a", "b", "c", "e", "f"}, true,  "bag with duplicates added first");


        System.out.println("\n\n-----------------------------TESTING createListContainingChar METHOD-----------------------------");
        // parameter 1: the contents of the ListInterface
        // parameter 2: the target character
        // parameter 3: the contents of the expected answer ListInterface
        // parameter 4: a description of the test
        testCreateList(new String[] {}, 'z',
                new String[] {},
                "empty list");
        testCreateList(new String[] {"gab"}, 'a',
                new String[] {"gab"},
                "singleton list with a word that has the target char");
        testCreateList(new String[] {"zip"}, 'e',
                new String[] {},
                "singleton list with a word that does not have the target char");
        testCreateList(new String[] {"bid", "sing", "ennui", "i", "icky"}, 'i',
                new String[] {"bid", "sing", "ennui", "i", "icky"},
                "list with all elements containing the target character");
        testCreateList(new String[] {"bid", "sing", "ennui", "i", "icky"}, 'z',
                new String[] {}, "list with no elements containing the target character");
        testCreateList(new String[] {"apple", "anna", "bob", "ANIMAL", "canal", "frog", "cat"}, 'a',
                new String[] {"apple", "anna",                  "canal",         "cat"},
                "list with elements that contain the target at beginning, middle, and end of the list");


        System.out.println("\n\n-----------------------------TESTING lastPosition METHOD-----------------------------");
        // parameter 1: the contents of the List
        // parameter 2: the target word
        // parameter 3: a description of the test
        testLastPosition(new String[] {}, 											"frank", 			"empty list");
        testLastPosition(new String[] {"hyde"}, 									"frank", 			"singleton list that doens't contain the target");
        testLastPosition(new String[] {"square"}, 									"square", 			"singleton list that contains the target");
        testLastPosition(new String[] {"apple", "teal", "carrot"}, 					"ball", 			"target not on the list");
        testLastPosition(new String[] {"dog", "cat", "bird"}, 						new String("bird"), "target on the list once at the end of the list");
        testLastPosition(new String[] {"bell", "gourd", "sound", "age", "friend"}, 	"age", 				"target on the list once in the middle of the list");
        testLastPosition(new String[] {"x", "a", "d", "x", "e", "c", "b", "x"}, 	"x", 				"target on the list more than once with last occurrence being in last position on the list");
        testLastPosition(new String[] {"x", "b", "d", "x", "b", "c", "b", "x"}, 	"b", 				"target on the list more than once with last occurrence in the middle");
        testLastPosition(new String[] {"f", "b", "g", "b", "v", "e", "c", "s"}, 	"f", 				"target on the list once at the beginning of the list");


        System.out.println("\n\n-----------------------------TESTING equivalentLists METHOD-----------------------------");
        // parameter 1: the contents of the ListInterface
        // parameter 2: the contents of the List
        // parameter 3: the expected result (true means equivalent, false means not)
        // parameter 4: a description of the test
        testEquivalentLists(new Integer[] {},
                new Integer[] {},
                true, "two empty lists");
        testEquivalentLists(new Integer[] {4},
                new Integer[] {4},
                true, "two singleton lists with the same element");
        testEquivalentLists(new Integer[] {1, 3, 1, 5, 7},
                new Integer[] {1, 3, 1, 5, 7},
                true, "equivalent lists (odd length)");
        testEquivalentLists(new Integer[] {1, 3, 1, 5, 7, 6},
                new Integer[] {1, 3, 1, 5, 7, 6},
                true, "equivalent lists (even length)");
        testEquivalentLists(new Integer[] {4},
                new Integer[] {7},
                false, "two singleton lists with different elements");
        testEquivalentLists(new Integer[] {},
                new Integer[]{1, 3, 1, 5, 7},
                false, "ListInterface is empty, List is not");
        testEquivalentLists(new Integer[] {1, 3, 1, 5, 7},
                new Integer[]{},
                false, "List is empty, ListInterface is not");
        testEquivalentLists(new Integer[] {1},
                new Integer[]{1, 3, 1, 5, 7},
                false, "difference sizes; the first elements match");
        testEquivalentLists(new Integer[] {1, 3, 1, 5},
                new Integer[] {1, 3, 1, 5, 7},
                false, "ListInterface has one fewer element than List");
        testEquivalentLists(new Integer[] {2, 4, 7, 6, 3},
                new Integer[] {2, 4, 7, 6},
                false, "List has one fewer element than ListInterface");
        testEquivalentLists(new Integer[] {1, 2, 3},
                new Integer[] {4, 2, 3},
                false, "only the first elements are different");
        testEquivalentLists(new Integer[] {1, 2, 3},
                new Integer[] {1, 6, 7},
                false, "only the first elements are the same");
        testEquivalentLists(new Integer[] {1, 2, 3},
                new Integer[] {1, 3, 2},
                false, "same elements but different order");
        testEquivalentLists(new Integer[] {2, 3, 5, 4},
                new Integer[] {3, 2, 5, 4},
                false, "last two elements are the same");


        System.out.println("\n\n-----------------------------TESTING EXTRA CREDIT prioritizeMaximumValue METHOD-----------------------------");
        // parameter 1: the contents of the List
        // parameter 2: the expected contents of the list after the max is prioritized
        // parameter 3: a description of the test
        testPrioritizeMax(new Integer[] {},
                new Integer[] {},
                "empty list");
        testPrioritizeMax(new Integer[] {4},
                new Integer[] {4},
                "singleton list");
        testPrioritizeMax(new Integer[] {4, 3, 2, 5, 7},
                new Integer[] {7, 4, 3, 2, 5},
                "maximum at the end of the list");
        testPrioritizeMax(new Integer[] {1, 3, 9, 5, 7},
                new Integer[] {9, 1, 3, 5, 7},
                "maximum in the middle of the list");
        testPrioritizeMax(new Integer[] {8, 3, 4, 5, 2},
                new Integer[] {8, 3, 4, 5, 2},
                "maximum at the beginning of the list");
        testPrioritizeMax(new Integer[] {-4, -3, -2, -5},
                new Integer[] {-2, -4, -3, -5},
                "maximum is negative");


        System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
        if(allTestsPassed) {
            System.out.println("----------Summary---------- All automated tests have passed. Be sure to manually review your code for style.");
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
     */
    public static void testBagContainsDuplicates(String[] wordArray, boolean expectedResult, String testDescription) {
        BagInterface<String> wordBag;
        Random generator = new Random();
        if(generator.nextBoolean()) {
            wordBag = new ArrayBag<String>();
        } else {
            wordBag = new LinkedBag<String>();
        }
        for(String word : wordArray) {
            wordBag.add(word);
        }
        Object[] beforeBagArray = wordBag.toArray();
        Arrays.sort(beforeBagArray);

        boolean actualResult = containsDuplicates(wordBag);

        Object[] afterBagArray = wordBag.toArray();
        Arrays.sort(afterBagArray);

        System.out.println("\nDuplicates? Test of: " +  Arrays.toString(beforeBagArray));
        System.out.println("Expected = " + expectedResult + "\n  Actual = " + actualResult);

        if(actualResult!=expectedResult) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED for: " + testDescription);
            System.out.println("Error: Method did not return expected result.");
        }
        if(!Arrays.equals(beforeBagArray, afterBagArray)) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED for: " + testDescription);
            System.out.println("Error: The bag contents were changed.");
            System.out.println("Bag contents before containsDuplicates invoked: " + Arrays.toString(beforeBagArray));
            System.out.println("Bag contents after  containsDuplicates invoked: " + Arrays.toString(afterBagArray));
        }
    }
    public static void testCreateList(String[] wordArray, char c, String[] answerArray, String testDescription) {
        ListInterface<String> wordList = new AList<String>();
        for(String word : wordArray) {
            wordList.add(word);
        }
        ListInterface<String> answerList = new AList<String>();
        for(String word : answerArray) {
            answerList.add(word);
        }

        Object[] beforeListArray = wordList.toArray();
        Object[] answerListArray = answerList.toArray();

        ListInterface<String> resultList = createListInterfaceContainingChar(wordList, c);
        Object[] resultListArray = resultList.toArray();

        System.out.println("\nParameter list:\t\t\t " + Arrays.toString(beforeListArray));
        System.out.println("Expected list of words with '" + c + "': " + Arrays.toString(answerListArray));
        System.out.println("  Actual list of words with '" + c + "': " + Arrays.toString(resultListArray));

        if(!Arrays.equals(answerListArray,  resultListArray)) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED for: " + testDescription);
        }
        if(!Arrays.equals(beforeListArray, wordList.toArray())) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED: The parameter list was changed.");
            System.out.println("Parameter list after method:     " + Arrays.toString(wordList.toArray()));
        }
    }
    public static void testLastPosition(String[] wordArray, String target, String testDescription) {
        List<String> originalList = new ArrayList<String>(Arrays.asList(wordArray));
        int resultPosition = lastPosition(originalList, target);
        int correctPosition = originalList.lastIndexOf(target);

        System.out.println("\nParameter list:\t\t" + originalList);
        System.out.println(" Expected last index of \"" + target + "\": " + correctPosition);
        System.out.println("   Actual last index of \"" + target + "\": " + resultPosition);

        if(resultPosition!=correctPosition) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED for: " + testDescription);
        }
    }
    public static void testEquivalentLists(Integer[] listInterfaceContents, Integer[] listArrayContents, boolean expectedResult, String testDescription) {
        ListInterface<Integer> listInterface = new AList<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int num : listInterfaceContents) {
            listInterface.add(num);
        }
        for(int num : listArrayContents) {
            list.add(num);
        }

        Object[] listInterfaceArrayBefore = listInterface.toArray();

        boolean actualResult = equivalentLists(listInterface, list);

        Object[] listInterfaceArrayAfter =listInterface.toArray();
        List<Integer> listAfter = new ArrayList<Integer>(list);

        System.out.println("\nListInterface:\t " + Arrays.toString(listInterfaceArrayBefore));
        System.out.println("List:         \t " + list);
        System.out.println("Expected result: " + expectedResult);
        System.out.println("Actual result:   " + actualResult);

        if(actualResult!=expectedResult) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED for: " + testDescription);
        }
        if(!Arrays.equals(listInterfaceArrayBefore, listInterfaceArrayAfter)) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED: ListInterface contents changed. Now contains: " +  Arrays.toString(listInterfaceArrayAfter));
        }
        if(!list.equals(listAfter)) {
            allTestsPassed = false;
            System.out.println("**********TEST FAILED: List contents changed. Now contains: " +  listAfter);
        }

    }
    public static void testPrioritizeMax(Integer[] array, Integer[] result, String testDescription) {
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
        List<Integer> originalList = new ArrayList<Integer>(list);
        prioritizeMaximumValue(list);
        List<Integer> resultList = new ArrayList<Integer>(Arrays.asList(result));

        System.out.println("\nOriginal list: \t " + originalList);
        System.out.println("Expected result: " + resultList);
        System.out.println("Actual result:   " + list);

        if(!resultList.equals(list)){
            allTestsPassed = false;
            System.out.println("**********TEST FAILED for: " + testDescription);
        }
    }


    //Write a complete method from the client perspective that determines if a bag contains any duplicate items.

    public static boolean containsDuplicates(BagInterface<String> wordBag){
        if(!wordBag.isEmpty()){//checks that it's not empty to start
            BagInterface<String> tempBag = new ArrayBag<String>();

            while(!wordBag.isEmpty()){
                String wordToCheck = wordBag.remove();
                tempBag.add(wordToCheck);
                boolean itemInBag = wordBag.contains(wordToCheck);

                if (itemInBag) {
                    while(!tempBag.isEmpty()){
                        String stringToPutBack = tempBag.remove();
                        wordBag.add(stringToPutBack);
                    }
                    return true;
                }
            }
            while(!tempBag.isEmpty()){
                String stringToPutBack = tempBag.remove();
                wordBag.add(stringToPutBack);
            }
            return false;
        } return false;  //if there's nothing in the bag - it doesn't have any duplicated
    }


    public static ListInterface<String> createListInterfaceContainingChar(ListInterface<String> wordList, char key) {
        ListInterface<String> newList = new AList<String>();

        for(int i = 1; i<=wordList.getLength();i++){
            String currentWord = wordList.getEntry(i);
            if(currentWord.indexOf(key)>=0){
                newList.add(currentWord);
            }
        }
        return newList;
    }



    public static int lastPosition(List<String> wordList, String targetWord) {
        int counter = 0;
        List<String> tempList = new ArrayList<>();

        if(wordList.contains(targetWord)){


            while(wordList.contains(targetWord)) {
                tempList.add(wordList.remove(counter));
                counter++;
            }

        }else {
            counter -=1; //this means is not on the list
        }

        while(!tempList.isEmpty()){
            String replacementString = tempList.get(tempList.size()-1);
            wordList.add(0,replacementString);
        }
        return counter;
    }




    public static boolean equivalentLists(ListInterface<Integer> numberListInterface, List<Integer> numberList) {
        if(!numberListInterface.isEmpty() && numberList.isEmpty()){
            if(numberListInterface.getLength()==numberList.size()){
                int listInterfacePosition  = 1;
                int listPosition  = 0;
                for(int i = 1; i<=numberListInterface.getLength();i++){
                    if(!(numberListInterface.getEntry(listInterfacePosition)==numberList.get(listPosition))){
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }




    public static void prioritizeMaximumValue(List<Integer> numberList){
        if(!numberList.isEmpty()) {
            int maxValue = numberList.get(0);
            int maxPosition = 0;
            for(int i =1; i<numberList.size();i++ ){
                int currentValue = numberList.get(i);
                if(currentValue>maxValue){
                    maxValue = currentValue;
                    maxPosition = i;
                }
            }
            numberList.remove(maxPosition);
            numberList.set(0,maxValue);
        } else {
            System.out.println("The list is empty.");
        }
    }
}
