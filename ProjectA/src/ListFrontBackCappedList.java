
/*Extra Credit*/

//okay to import the 3 classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

    private List<T> list; // initialize to type ArrayList<T> in the ListFrontBackCappedList constructor
    // private int numberOfElements; //okay not to use numberOfElements?
    private int maxCapacityOfList = 0; // okay to keep declare another data instance variable

    // constructor
    public ListFrontBackCappedList(int maxCapacity) {
        ArrayList<T> tempList;
        tempList = new ArrayList<T>(maxCapacity);
        maxCapacityOfList = maxCapacity;
        list = tempList;

    }

    public String toString() {
        String size = "size=" + this.size() + ";";
        String capacity = "capacity=" + maxCapacityOfList+";";
        return size + " " + capacity + "    " + Arrays.toString(list.toArray());
    }

    // completed - check isFull
    public boolean addFront(T newEntry) {
        if (isFull()) {
            return false;
        } else {
            list.add(0, newEntry);
            return true;
        }
    }

    // completed - check isFull
    public boolean addBack(T newEntry) {
        if (isFull()) {
            return false;
        } else {
            list.add(newEntry);
            return true;
        }

    }

    // completed
    public T removeFront() {
        if (isEmpty()) {
            return null;
        } else {
            return list.remove(0);
        }
    }

    // completed- ok to use list.size()
    public T removeBack() {
        if (isEmpty()) {
            return null;
        } else {
            // index = size -1 because of 0 indexing
            int lastIndex = (this.size() - 1);
            return list.remove(lastIndex);
        }
    }

    // question: ok to use list.size()
    public T getEntry(int givenPosition) {
        // number of elements - 1 = index of element
        // empty list
        if (isEmpty()) {
            return null;
            // nonempty list
        } else {
            // check if valid position
            if (!(givenPosition >= 0 && givenPosition < this.size())) {
                return null;
            } else {
                return list.get(givenPosition);

            }
        }
    }

    // question: is it okay to add an additional instance data variable
    public boolean isFull() {
        return list.size() >= maxCapacityOfList;
    }

    // method in arraylist -completed
    public void clear() {
        list.clear();

    }

    // method in arraylist -completed
    public int indexOf(T anEntry) {
        return list.indexOf(anEntry);
    }

    // method in arraylist -completed
    public int lastIndexOf(T anEntry) {
        return list.lastIndexOf(anEntry);
    }

    // method in arraylist -completed
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }

    // question: method in arraylist- completed
    public int size() {
        return list.size();
    }

    // method in arraylist- completed
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
