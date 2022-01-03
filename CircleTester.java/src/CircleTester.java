

public class CircleTester {
    {
        public static void main(String args[]) //main method
        {
            Circle c1,c2,c3; //object creation
            c1=new Circle();
            c2=new Circle();
            c3=new Circle();
            c1.setRadius(2); //calling the method to set radius
            c1.setX(0); //calling the method to set the coordinate x
            c1.setY(0); //calling the method to set the coordinate y
            c2.setRadius(3);
            c2.setX(1);
            c2.setY(1);
            c3.setRadius(1);
            c3.setX(5);
            c3.setY(5);
            double a1=c1.getArea(); //calling the method getArea
            double a2=c2.getArea();
            double a3=c3.getArea();
            System.out.println("Area of circle 1: "+a1); //printing the area of circle
            System.out.println("Area of circle 2: "+a2);
            System.out.println("Area of circle 3: "+a3);
            boolean b1=c1.doesOverlap(c2); //calling the method doesOverlap
            boolean b2=c1.doesOverlap(c3);
            System.out.println("Does circle 1 and 2 overlap?: "+b1); //printing whether circle overlaps or not
            System.out.println("Does circle 1 and 3 overlap?: "+b2);
        }

}
