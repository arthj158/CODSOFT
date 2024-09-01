
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to The Guess Game!");

        while (playAgain) {
            int correctNumber = random.nextInt(100) + 1; 
            
            int attempts = 0;
            int tries = 3;
            boolean guessed = false;

            System.out.println("The system has picked a number between 1 to 100");
            System.out.println( tries + " tries to guess it");

            while (attempts < tries) {
                System.out.print("Submit your guess: ");
                int playerGuess = scanner.nextInt();
                attempts++;

                if (playerGuess == correctNumber) {
                    System.out.println("your guess was correct");
                    System.out.println("You took "+attempts+" to guess the correct answer");
                    score++;
                    guessed = true;
                    break;
                    
                } else if (playerGuess < correctNumber) {
                    System.out.println("Guess higher than the correct number ");
                } else {
                    System.out.println("Guess lower than the correct number ");
                }
            }

            if (!guessed) {
                System.out.println("No tires left the correct answer is " + correctNumber + ".");
            }
            System.out.println("Your score: " + score);

            System.out.print("do you want to play one more time? (yes or no): ");
            String response = scanner.next();
            playAgain = response.equals("yes");
        }

        System.out.println("Your final score is : " + score);
    }
}

