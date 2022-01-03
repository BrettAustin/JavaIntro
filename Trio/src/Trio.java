public class Trio<T> {
    private T item1, item2, item3;

    public Trio(T object1, T object2, T object3 ){
        this.item1 = object1;
        this.item2 = object2;
        this.item3 = object3;
    }

    public Trio(T object){
        this.item1 = object;
        this.item2 = object;
        this.item3 = object;
    }

    public T getItem1(){
        return item1;
    }

    public T getItem2(){
        return item2;
    }

    public T getItem3(){
        return item3;
    }

    public void setItem1(T newItem1){
        item1 = newItem1;
    }

    public void setItem2(T newItem2){
        item2 = newItem2;
    }

    public void setItem3(T newItem3){
        item3 = newItem3;
    }

    public String toString(){
        return    "Item 1: " + item1.toString() +"n/"
                + "Item 2: " + item2.toString() +"n/"
                + "Item 3: " + item3.toString() +"n/";
    }

    public void replaceAll(T object){
        this.setItem1(object);
        this.setItem2(object);
        this.setItem3(object);
    }

    public boolean hasDuplicates(){
        if (this.item1.equals(item2)) {
            return true;
        } else if (this.item1.equals(item3)){
            return true;
        } else if (this.item2.equals(item3)){
            return true;
        } else {
            return false;
        }
    }

    public int count(T object){
        int count = 0;
        if (this.item1.equals(object)) {
            count++;
        } if (this.item2.equals(object)) {
            count++;
        } if (this.item3.equals(object)) {
            count++;
        }
        return count;
    }


    public boolean equals(Object testTrio) {
        if (testTrio instanceof Trio<?>) {
            Trio<T> otherTrio = (Trio<T>) testTrio;

            //first case, test if both sets are same number
            if (this.count(otherTrio.getItem1()) == 3 && otherTrio.count(this.getItem1()) == 3) {
                return true;
            }
            //second case, test if all three are different, but same sets
            else if (this.count(otherTrio.getItem1()) == 1 && this.count(otherTrio.getItem2()) == 1 && this.count(otherTrio.getItem3()) == 1) {
                return true;
            }
            //third case, two duplicate pairs, and one other number matches
            else if (
                    (this.count(otherTrio.getItem1()) + this.count(otherTrio.getItem2()) + this.count(otherTrio.getItem3()) == 5)) {
                return true;
            }

        }
        return false;
    }

}
