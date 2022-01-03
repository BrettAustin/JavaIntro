/*************
 * Brett Cooper
 * CS 111B-Programming Fundamentals: Java-933
 ****************/

import java.util.Random;
import java.util.Scanner;

public class BlackjackGame 
{
    public static void main(String[] args)
    {
        //these are all the variables I'm goig to use
        int cardOne, cardTwo, otherCards;
        int total;
        char anotherCard ='y';
        String inputHolder;
        Random random = new Random();
        Scanner stdin = new Scanner(System.in);

        //this deals the first hand
        cardOne = random.nextInt(10)+1;
        cardTwo = random.nextInt(10)+1;
        total = cardOne + cardTwo;
        System.out.println("First cards: "+cardOne+", "+cardTwo);
        System.out.println("Total: "+total);

        //As long as this is 'y' it executes
        while (anotherCard == 'y') {
            System.out.print("Do you want another card? (y/n): ");
            inputHolder = stdin.nextLine();
            anotherCard = inputHolder.charAt(0);

            if (anotherCard == 'y') {
                otherCards = random.nextInt(10) + 1;
                total = total + otherCards;
                System.out.println("Card: " + otherCards);
                System.out.println("Total: " + total);
            }
            if (total>21)
            {
                System.out.println("Bust");
                anotherCard = 'n';
            }
        }
    }
}
