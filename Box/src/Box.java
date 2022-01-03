public class Box<T> {
    private T thing;
    private int count;

    public Box(T object){
        this.thing = object;
        this.count = 1;
    }

    public T getContents(){
        return this.thing;
    }

    public int getCount (){
        return count;
    }

    public void replaceContents(T newThing){
        this.thing = newThing;
        count++;
    }

    public String toString(){
        return thing.toString() + "item. Count: "+getCount();
    }

    public boolean equals(Object otherObject){
        if(otherObject instanceof Box<?>){
            Box<?> comparison = (Box<?>) otherObject;
            boolean sameThing = this.thing.equals(comparison);
            boolean sameCount = this.count==comparison.count;

            return sameThing && sameCount;

        } else {
            return false;
        }
    }

    public boolean contains(Object otherObject){
        Class<?> classOfBoxObject = this.getContents().getClass();
        Class<?> classOfOtherObject = otherObject.getClass();

        if(classOfBoxObject==classOfOtherObject){
            return true;
        } else {
            return false;
        }
    }

    /*

   Add a contains method to the Box class. T
   he method takes in an object and returns true or false based on whether the box currently contains that object.
     */

}
