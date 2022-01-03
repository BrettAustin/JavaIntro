import java.util.Scanner;

public class NumberGuesser
{
    public static boolean shouldPlayAgain()
    {
        System.out.print("Great! Do you want to play again? (y/n): ");
        char response = new Scanner(System.in).nextLine().charAt(0);
        return response == 'y';
    }

    public static void playOneGame()
    {
        System.out.println("Guess a number between 1 and 100.");
        int low = 1;
        int high = 100;
        boolean correctGuess = false;

        while(!correctGuess)
        {
            int guess = getMidpoint(low, high);
            char userResponse = getUserResponseToGuess(guess);

            if (userResponse == 'c'){
                correctGuess = true;
            }
            else if (userResponse == 'h'){
                low = guess + 1;
            }
            else if (userResponse == 'l'){
                high = guess - 1;
            }
            else {
                System.out.printf("Invalid input - valid inputs are (h/l/c)");
            }
        }
    }

    public static int getMidpoint(int low, int high){
        if ((low + high) % 2 == 0)
            return (low + high) / 2;
        else
            return (low + high - 1) / 2;
    }

    public static char getUserResponseToGuess(int guess){
        System.out.print("Is it " + guess +" (h/l/c): ");
        char response = new Scanner(System.in).nextLine().charAt(0);
        return response;
    }

    public static void main(String[] args) {
        do{
            playOneGame();
        }while(shouldPlayAgain());
    }

}