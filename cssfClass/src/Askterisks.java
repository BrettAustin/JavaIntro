import java.util.Scanner; //imports the Scanner class

public class Askterisks //the class I'm writing and the name of the program
{
    public static void main(String[] args) //this has to have a main method
    {
        int count; //uninitialized int variable
        char goAgain = 'y'; //char initiailized to y
        Scanner stuff = new Scanner(System.in); //this is bringing in the Scanner class object input and setting the variable to what it gathers from

        while (goAgain != 'n')
        {
            System.out.print("How many?:");
            count = stuff.nextInt();

            for(int i =0; i < count; i++)
            {
               //System.out.print(i);
                for(int j=0;j<=i;j++)
                {
                    System.out.print("*");
                }
                System.out.println("");
            }
            System.out.println(""); //the System class is built into Java --> it doesn't need to be added
            System.out.println("Go again?");
            goAgain = stuff.next().charAt(0);
        }
    }
}