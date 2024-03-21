package advance;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MeaningfulWordGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to Meaningful Word Sorting Game!");
        System.out.println("Arrange the letters to form a meaningful word.");

        // List of meaningful words
        String[] words = {"HELLO", "WORLD", "JAVA", "PROGRAMMING", "COMPUTER"};

        // Select a random word
        String word = words[random.nextInt(words.length)];

        // Shuffle letters of the word
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            int randomIndex = random.nextInt(letters.length);
            char temp = letters[i];
            letters[i] = letters[randomIndex];
            letters[randomIndex] = temp;
        }

        // Display shuffled letters
        System.out.println("Shuffled letters: " + Arrays.toString(letters));

        // Prompt user to enter the meaningful word
        System.out.print("Enter the meaningful word: ");
        String guess = scanner.nextLine().toUpperCase();

        // Check if the guess is correct
        if (guess.equals(word)) {
            System.out.println("Congratulations! You guessed the word correctly.");
        } else {
            System.out.println("Sorry, the word is incorrect. The correct word is: " + word);
        }

        scanner.close();
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Meaningful Word Game!");
        scanner.close();
    }
}
