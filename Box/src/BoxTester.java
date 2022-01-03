public class BoxTester {

    public static void main(String[] args) {
        Integer number = new Integer(3);
        Box<Integer> example = new Box<Integer>(number);
        Integer secondNumber = new Integer(5);

        System.out.println(example.contains(secondNumber));

    }
}
