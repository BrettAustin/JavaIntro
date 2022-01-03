import java.util.Arrays;
public final class ResizableArrayBag<T> implements BagInterface<T> {

    /**
     * A class that implements a bag of objects by using an array. The bag is never
     * full.
     *
     * @author Frank M. Carrano
     * @author Timothy M. Henry
     * @version 4.0
     */

    public T[] bag; // Cannot be final due to doubling
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25; // Initial capacity of bag
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag whose initial capacity is 25. */
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty bag having a given initial capacity.
     *
     * @param initialCapacity
     *            The integer capacity desired.
     */
    public ResizableArrayBag(int initialCapacity) {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[initialCapacity]; // Unchecked cast
        bag = tempBag;
        numberOfEntries = 0;
        initialized = true;
    }

    /**
     * Creates a bag containing given entries.
     *
     * @param contents
     *            An array of objects.
     */
    public ResizableArrayBag(T[] contents) {
        checkCapacity(contents.length);
        bag = Arrays.copyOf(contents, contents.length);
        numberOfEntries = contents.length;
        initialized = true;
    }

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry
     *            The object to be added as a new entry.
     * @return True.
     */
    public boolean add(T newEntry) {
        checkInitialization();
        if (isArrayFull()) {
            doubleCapacity();
        }

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    }

    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in this bag.
     */
    public T[] toArray() {
        checkInitialization();

        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        } // end for

        return result;
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
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry
     *            The entry to be counted.
     * @return The number of times anEntry appears in this ba.
     */
    public int getFrequencyOf(T anEntry) {
        checkInitialization();
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
     * @param anEntry
     *            The entry to locate.
     * @return True if this bag contains anEntry, or false otherwise.
     */
    public boolean contains(T anEntry) {
        checkInitialization();
        return getIndexOf(anEntry) > -1; // or >= 0
    }

    /** Removes all entries from this bag. */
    public void clear() {
        while (!isEmpty())
            remove();
    }

    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or null.
     */
    public T remove() {
        checkInitialization();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }

    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anEntry
     *            The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    public boolean remove(T anEntry) {
        checkInitialization();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
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
            } // end if
            index++;
        } // end while

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
            bag[givenIndex] = bag[lastIndex]; // Replace entry to remove with
            // last entry
            bag[lastIndex] = null; // Remove reference to last entry
            numberOfEntries--;
        }

        return result;
    }

    // Returns true if the array bag is full, or false if not.
    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    }

    // Doubles the size of the array bag.
    // Precondition: checkInitialization has been called.
    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException(
                    "Attempt to create a bag whose capacity exceeds "
                            + "allowed maximum of " + MAX_CAPACITY);
        }
    }

    // Throws an exception if receiving object is not initialized.
    private void checkInitialization() {
        if (!initialized)
            throw new SecurityException("Uninitialized object used "
                    + "to call an ArrayBag method.");
    }

    public boolean equals(Object obj) {
        if(obj instanceof ResizableArrayBag<?>) {
            ResizableArrayBag<T> otherBagParam = (ResizableArrayBag<T>) obj;
            T[] array = otherBagParam.toArray();

            ResizableArrayBag<T> otherBag = new ResizableArrayBag<T>(otherBagParam.numberOfEntries);
            for(int i=0; i<otherBagParam.numberOfEntries; i++) {
                otherBag.add(otherBagParam.bag[i]);
            }

            for(int i=0; i<numberOfEntries; i++) {
                T item = this.bag[i];
                if(!otherBag.contains(item)) {
                    return false;
                } else {
                    otherBag.remove(item);
                }
            }
            if(otherBag.isEmpty()) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public void trimToSize() {
        if(numberOfEntries>0){ //if there's nothing in it, I will not perform this action
            int newLength = numberOfEntries;
            bag = Arrays.copyOf(bag, newLength); //this should trim the trailing nulls off the end
        }
    }


}

