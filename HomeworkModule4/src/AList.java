import java.util.*;

public class AList<T extends Comparable<? super T>> implements ListInterface<T>, Comparable<AList<T>> {

    /**
     * A class that implements the ADT list by using a resizable array. Entries in a
     * list have positions that begin with 1. Duplicate entries are allowed.
     *
     * @author Frank M. Carrano
     * @author Timothy M. Henry
     * @version 4.0
     */

    private T[] list; // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    public static final int MAX_CAPACITY = 10000;

    public AList() {
        this(DEFAULT_CAPACITY);
    }

    public AList(int initialCapacity) {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            // Is initialCapacity too big?
            checkCapacity(initialCapacity);
        }

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Comparable[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;
    }

    public void add(T newEntry) {
        add(numberOfEntries + 1, newEntry);
    }

    // Precondition: The array list has room for another entry.
    public void add(int newPosition, T newEntry) {
        checkInitialization();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) { // checks that it's a valid position
            if (newPosition <= numberOfEntries) // if we're not adding to the end of the list, we need to make room
                makeRoom(newPosition);
            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add

        } else {
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
        }
    }

    public T remove(int givenPosition) {
        checkInitialization();
        if (validPosition(givenPosition)) { // make sure it's a valid position
            assert !isEmpty(); // we know this because of the check on the valid position
            T result = list[givenPosition]; // Get entry to be removed

            // Move subsequent entries towards entry to be removed, unless it is last in list
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    } // end remove

    public void clear() {
        checkInitialization();

        // Clear entries but retain array; no need to create a new array
        for (int index = 1; index <= numberOfEntries; index++) {
            list[index] = null;
        }

        numberOfEntries = 0;
    }

    public T replace(int givenPosition, T newEntry) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    public T getEntry(int givenPosition) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    public T[] toArray() {
        checkInitialization();

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
        }

        return result;
    }

    public boolean contains(T anEntry) {
        checkInitialization();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)) {
            if (anEntry.equals(list[index])) {
                found = true;
            }
            index++;
        }

        return found;
    }

    public int getLength() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public boolean moveToEnd() {
        if (numberOfEntries == 1) { // no changes made for a singleton list
            return true;
        } else if (!isEmpty()) { // changes made for lists larger than size 1
            T firstItem = remove(1);
            add(firstItem);
            return true;
        } else { // empty list
            return false;
        }
    }

    private boolean validPosition(int position) {
        return position >= 1 && position <= numberOfEntries;
    }

    public void swap(int firstPosition, int secondPosition) {
        if (validPosition(firstPosition) && validPosition(secondPosition)) {
            T temp = list[firstPosition];
            list[firstPosition] = list[secondPosition];
            list[secondPosition] = temp;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to swap operation.");
        }
    }

    public ListInterface<T> getAllLessThan(T entry) {
        AList<T> lessThanList = new AList<>();
        for (int i = 1; i <= numberOfEntries; i++) {
            T currentItemOnList = list[i];
            if (currentItemOnList.compareTo(entry) < 0) { // currentItemOnList is SMALLER THAN entry
                lessThanList.add(currentItemOnList);
            }
        }
        return lessThanList;
    }


    // Doubles the capacity of the array list if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); // Is capacity too big?
            list = Arrays.copyOf(list, newCapacity + 1);
        }
    }

    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries + 1;
    // numberOfEntries is list's length before addition;
    // checkInitialization has been called.
    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            list[index + 1] = list[index];
        }
    }

    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    // numberOfEntries is list's length before removal;
    // checkInitialization has been called.
    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    }

    // Throws an exception if this object is not initialized.
    private void checkInitialization() {
        if (!initialized)
            throw new SecurityException("AList object is not initialized properly.");
    }

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException(
                    "Attempt to create a list " + "whose capacity exceeds " + "allowed maximum.");
    }


    public int removeAll(T element) {
        int numberOfOccurrences =0;
        AList<T> tempBag = new AList<>();

        for(int i = 1;i<=numberOfEntries;i++){
            if(!(this.list[i].equals(element))){
                tempBag.add(this.list[i]);

            } else {
                numberOfOccurrences++;
            }

        }
        if(numberOfOccurrences>0) {
            this.clear();

            for (int i = 1; i <= tempBag.numberOfEntries; i++) {
                this.add(tempBag.list[i]);
            }
        }
        return numberOfOccurrences;
    }


    //Two lists should be considered logically equivalent ("the same") if they contain the same (equivalent) elements in the same order.
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AList<?>){
            AList<T> otherBag = (AList<T>) obj;
            if(this.numberOfEntries== otherBag.numberOfEntries){

            for(int i = 1;i<=numberOfEntries;i++){
                if (!list[i].equals(otherBag.list[i])){
                    return false;
                }
            } return true;
        }
        }
        return false;
    }

    @Override
    public int compareTo(AList<T> otherList) {
        if(!(this.numberOfEntries==otherList.numberOfEntries)){
            return this.numberOfEntries-otherList.numberOfEntries;
        }
        for(int i = 1;i<=this.numberOfEntries;i++){
            int elementComparison = this.list[i].compareTo(otherList.list[i]);
            if(!(elementComparison==0)){
                return elementComparison;
            }
        }
        return 0;
    }

}
