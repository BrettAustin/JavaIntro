import java.util.Arrays;

/**
 * An Array implementation of FrontBackCappedListInterface interface. 
 * Entries in a list have positions that begin with 0.
 * Entries can only be removed or added to the beginning (front) or end (back)
 * of the list. Entries can be accessed from any position. The size of the list
 * is limited ("capped"). When the limit is reached, no more entries can be
 * added.
 *
 * @author Brett Cooper, Jean Lam, Gustavo Medina
 */


public class ArrayFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {
    private T[] list;
    private int numberOfElements;

    /**
     *
     * Creates an empty list set by value of maxCapacity
     * @param maxCapacity The capacity of list x
     *
     */

    public ArrayFrontBackCappedList(int maxCapacity) {

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[maxCapacity];
        list = tempList;
        numberOfElements = 0;

    }

    /**
     *
     * The String representation of the list. 
     * Which consists of its size and capacity. 
     *
     * @return The size and capacity of the list
     */
    @Override
    public String toString() {
        String size = "size=" + this.size() + ";";
        String capacity = "capacity=" + list.length + ";";
        String arrayString = Arrays.toString(this.toArray());
        return size + " " + capacity + " " + arrayString;

    }

    /**
     * Adds a new entry to the end of the list if the list is not full.
     * Entries currently in the list are unaffected.
     * If the entry is added, the list size is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     * @return true if the object was added, false if the list was full and thus the object was not added
     */
    public boolean addBack(T newEntry) {

        boolean results = true;

        if (isFull()) {
            results = false;
        } else {
            list[numberOfElements] = newEntry;
            numberOfElements++;
        }
        return results;

    }

    /**
     * Adds a new entry to the beginning of the list if the list is not full.
     * If the entry is added, entries currently in the list are shifted down and
     * the list size is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     * @return true if the object was added, false if the list was full and thus the object was not added
     */
    public boolean addFront(T newEntry) {

        if (isFull()) {
            return false;
        } else {
            // Have to shift from the back (move forward)
            for (int i = numberOfElements - 1; i >= 0; i--) {
                list[i + 1] = list[i];
            }
            list[0] = newEntry;
            numberOfElements++;
            return true;

        }

    }

    /**
     * Removes an entry from the beginning of the list.
     * Entries currently in the list are shifted up.
     * If an entry is removed, the list size is decreased by 1.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
    public T removeFront() {

        T removedItem = null;

        if (isEmpty()) {
            removedItem = null;
        } else {
            removedItem = list[0];
            list[0] = null;
            numberOfElements--;
            for (int i = 0; i < numberOfElements; i++) {
                list[i] = list[i + 1];
            }
        }
        return removedItem;

    }

    /**
     * Removes an entry from the end of the list.
     * Entries currently in the list are unaffected.
     * If an entry is removed, the list size is decreased by 1.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
    public T removeBack() {

        T removedItem = null;

        if (isEmpty()) {
            removedItem = null;
        } else {
            // get index
            removedItem = list[numberOfElements - 1];
            list[numberOfElements - 1] = null;
            numberOfElements--;
        }
        return removedItem;
    }

    /**
     * Determines whether an entry is in the list.
     *
     * @param anEntry the object to search for in the list.
     * @return true if the list contains the entry, false otherwise
     */
    public boolean contains(T anEntry) {

        boolean isThere = false;
        for (int i = 0; i < numberOfElements; i++) {
            if (list[i].equals(anEntry)) {
                isThere = true;
                break;
            }
        }
        return isThere;
    }

    /**
     * Determines the position in the list of a given entry. If the entry appears
     * more than once, the first index is returned.
     *
     * @param anEntry the object to search for in the list.
     * @return the first position the entry that was found or -1 if the object is
     *         not found.
     */
    public int indexOf(T anEntry) {

        int index = -1;

        for (int i = 0; i < numberOfElements; i++) {
            if (list[i].equals(anEntry)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Determines the position in the list of a given entry. If the entry appears
     * more than once, the last index is returned.
     *
     * @param anEntry the object to search for in the list.
     * @return the last position the entry that was found or -1 if the object is not
     *         found.
     */
    public int lastIndexOf(T anEntry) {
        int index = -1;

        for (int i = 0; i < numberOfElements; i++) {
            if (list[i].equals(anEntry)) {
                index = i;
            }
        }
        return index;
    }
    /**
     * Retrieves the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of the desired
     *                      entry.
     * @return A reference to the indicated entry or null if the index is out of
     *         bounds.
     */
    public T getEntry(int givenPosition) {

        // empty list
        if (isEmpty()) {
            return null;
            // nonempty list
        } else {
            // check if valid position
            if (!(givenPosition >= 0 && givenPosition < numberOfElements)) {
                return null;
            } else {
                for (int i = 0; i < numberOfElements; i++) {
                    if (i == givenPosition) {
                        return list[i];
                    }
                }
                return null;
            }
        }

    }

    /** Removes all entries from this list. */
    public void clear() {
        removeBack();
        numberOfElements = 0;
    }

    /**
     * Checks whether this list is empty.
     *
     * @return True if the list is empty, or false if the list contains one or more
     *         elements.
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Checks whether this list is full.
     *
     * @return True if the list is full, or false otherwise.
     */
    public boolean isFull() {
        return numberOfElements >= list.length;
    }

    /**
     * Gets the length of this list.
     *
     * @return The integer number of entries currently in the list.
     */
    public int size() {
        return numberOfElements;
    }

    /**
     * Creates an array of the list's elements
     *
     * @return The array of the elements
     */
    public T[] toArray() {

        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfElements]; // Unchecked cast

        for (int i = 0; i < numberOfElements; i++) {
            result[i] = list[i];
        }

        return result;

    }
}
