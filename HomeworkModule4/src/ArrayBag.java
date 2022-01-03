/**
 * A class of bags whose entries are stored in a fixed-size array.
 *
 * @author Frank M. Carrano, Timothy M. Henry
 * @version 5.0
 */
public final class ArrayBag<T> implements BagInterface<T> {

    private final T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25;
    public static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag whose initial capacity is 25. */
    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty bag having a given capacity.
     *
     * @param desiredCapacity The integer capacity desired.
     */
    public ArrayBag(int desiredCapacity) {
        if (desiredCapacity <= MAX_CAPACITY) {
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity]; // Unchecked cast
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        } else {
            throw new IllegalStateException(
                    "Attempt to create a bag " + "whose capacity exceeds " + "allowed maximum.");
        }
    }

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, or false if not.
     */
    public boolean add(T newEntry) {
        checkIntegrity();
        boolean result = true;
        if (isArrayFull()) {
            result = false;
        } else { // Assertion: result is true here
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        }

        return result;
    }

    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or null.
     */
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }

    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    public boolean remove(T anEntry) {
       // System.out.println("AnEntry is "+anEntry);
        checkIntegrity();
        int index = getIndexOf(anEntry);
        //System.out.println("Index is "+index);
        T result = removeEntry(index);
       // System.out.println("Result is "+result);
        return anEntry.equals(result);
    }
    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in this ba.
     */
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int counter = 0;

        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if this bag contains anEntry, or false otherwise.
     */
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    }

    /** Removes all entries from this bag. */
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    /**
     * Sees whether this bag is empty.
     *
     * @return True if this bag is empty, or false if not.
     */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Gets the current number of entries in this bag.
     *
     * @return The integer number of entries currently in this bag.
     */
    public int getCurrentSize() {
        return numberOfEntries;
    }

    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in this bag.
     */
    public T[] toArray() {
        checkIntegrity();

        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
        // Note: The body of this method could consist of one return statement,
        // if you call Arrays.copyOf
    }
    // Returns true if the array bag is full, or false if not.
    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    }

    // Locates a given entry within the array bag.
    // Returns the index of the entry, if located,
    // or -1 otherwise.
    // Precondition: checkInitialization has been called.
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int index = 0;

        while (!found && (index < numberOfEntries)) {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
            }
            index++;
        }

        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array.

        return where;
    }

    // Removes and returns the entry at a given index within the array.
    // If no such entry exists, returns null.
    // Precondition: 0 <= givenIndex < numberOfEntries.
    // Precondition: checkInitialization has been called.
    private T removeEntry(int givenIndex) {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex]; // Entry to remove
            int lastIndex = numberOfEntries - 1;
            bag[givenIndex] = bag[lastIndex]; // Replace entry to remove with last entry
            bag[lastIndex] = null; // Remove reference to last entry
            numberOfEntries--;
        }

        return result;
    }

    // Throws an exception if this object is not initialized.
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }

    public ResizableArrayBag<T> createResizableArrayBag() {
        ResizableArrayBag<T> newBag = new ResizableArrayBag<>(this.bag);
        return newBag;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayBag<?>) {
            ArrayBag<T> otherBagParam = (ArrayBag<T>) obj;

            ArrayBag<T> otherBag = new ArrayBag<T>(otherBagParam.numberOfEntries);

            for (int i = 0; i < otherBagParam.numberOfEntries; i++) {
                otherBag.add(otherBagParam.bag[i]);
            }

            for (int i = 0; i < numberOfEntries; i++) {
                T item = this.bag[i];
                if (!otherBag.contains(item)) {
                    return false;
                } else {
                    otherBag.remove(item);
                }
            }
            if (otherBag.isEmpty()) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public void replaceAllBad(T oldEntry, T newEntry) {
        while(contains(oldEntry)) {
            int index = getIndexOf(oldEntry);
            bag[index] = newEntry;
        }
    }
    public void replaceAllGood(T oldEntry, T newEntry) {
        for(int i=0; i<numberOfEntries; i++) {
            if(bag[i].equals(oldEntry)) {
                bag[i] = newEntry;
            }
        }
    }


    public int removeAll(T element){
        int numberOfOccurrences =0;
        ArrayBag<T> tempBag = new ArrayBag<>();

        for(int i = 0;i<numberOfEntries;i++){
            if(!(this.bag[i].equals(element))){
                tempBag.add(this.bag[i]);

            } else {
                numberOfOccurrences++;
            }

        }
        if(numberOfOccurrences>0) {
            this.clear();

            for (int i = 0; i < tempBag.numberOfEntries; i++) {
                this.add(tempBag.bag[i]);
            }
        }
        return numberOfOccurrences;
    }






}
