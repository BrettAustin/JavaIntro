import java.util.Random;

public final class LinkedBag<T extends Comparable <? super T>> implements BagInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public boolean add(T newEntry) {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Make new node reference rest of chain
        // (firstNode is null if chain is empty)
        firstNode = newNode; // New node is at beginning of chain
        numberOfEntries++;

        return true;
    }

    public T remove() {
        T result = null;
        if (firstNode != null) {
            result = firstNode.data;
            firstNode = firstNode.next; // Remove first node from chain
            numberOfEntries--;
        }
        return result;
    }

    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if (nodeN != null) {
            nodeN.data = firstNode.data; // Replace located entry with entry in first node
            firstNode = firstNode.next; // Remove first node
            numberOfEntries--;
            result = true;
        }

        return result;
    }

    public T removeRandom(){
         Random generator = new Random();
         if(isEmpty()){
             return null;
         } else {
             int randomIndex = generator.nextInt(numberOfEntries);

             Node current = firstNode;
             for (int i = 0; i < randomIndex; i++) {
                 current = current.next;
             }
             T result = current.data;
             remove(current.data);
             return result;
         }
    }

    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        Node currentNode = firstNode;

        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            }
            currentNode = currentNode.next;
        }
        return frequency;
    }

    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }

        return found;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public int getCurrentSize() {
        return numberOfEntries;
    }

    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null ) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }

        return result;
    }

    // Locates a given entry within this bag.
    // Returns a reference to the node containing the entry, if located, or null
    // otherwise.
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }

        return currentNode;
    }
    public T getMax() {
        T largestValue = null;
        if (!isEmpty()) {
            largestValue = firstNode.data;
            Node currentNode = firstNode.next;
            while (currentNode != null) {
                T currentValue = currentNode.data;
                if (currentValue.compareTo(largestValue) > 0)
                    largestValue = currentValue;
                currentNode = currentNode.next;
            } // end while
        } // end if
        return largestValue;

    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedBag<?>) {
            LinkedBag<T> otherBag = (LinkedBag<T>) obj;

            if (this.numberOfEntries == otherBag.numberOfEntries) {

                // created a copy of the other bag
                LinkedBag<T> otherCopy = new LinkedBag<T>();
                Node otherCurrent = otherBag.firstNode;
                while (otherCurrent != null) {
                    otherCopy.add(otherCurrent.data);
                    otherCurrent = otherCurrent.next;
                }

                // go through the current bag and compare to the other copy bag
                // when we find a match, remove it from the other copy bag
                Node current = firstNode;
                while (current != null) {
                    T currentData = current.data;

                    // remove fails
                    if (!otherCopy.remove(currentData)) {
                        return false;
                    }

                    current = current.next;
                }

                // otherCopy is now empty!

                // same contents!
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int removeAll(T item) {
        // The method takes a value in as a parameter and removes all occurrences of that value
        int countOfItem = 0;
        if(item==null){ //null passed in
            return countOfItem;
        }
        if (this.isEmpty()){//empty case
            return countOfItem;
        }

        Node nextNodeToCheck = firstNode;

        if(firstNode.next==null){ //for singleton
            if(nextNodeToCheck.data.equals(item)){
                remove();
                countOfItem++;
            } return countOfItem;
        }

        while(!(nextNodeToCheck==null)){

            if(nextNodeToCheck.data.equals(item)){
                countOfItem++;

                Node nodeToChange = nextNodeToCheck;

                if(nextNodeToCheck.next==null){
                    nextNodeToCheck=null;
                } else{
                    nextNodeToCheck=nextNodeToCheck.next;
                }
                remove(nodeToChange.data);

            } else {
                if(nextNodeToCheck.next==null){
                    nextNodeToCheck=null;
                } else{
                    nextNodeToCheck=nextNodeToCheck.next;
                }
            }
        }
        return countOfItem;
    }



    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

}
