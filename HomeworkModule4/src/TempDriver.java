import java.util.ArrayList;

public class TempDriver {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        System.out.println(arrayList.remove("x"));

        System.out.println(arrayList.toString());



    }
}
