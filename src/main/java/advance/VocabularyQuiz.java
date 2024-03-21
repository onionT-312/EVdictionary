package advance;

import java.util.Scanner;

public class VocabularyQuiz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Vocabulary Quiz!");
        System.out.println("Fill in the blank with the correct word.");

        // Generate question
        String question = "What __ you doing?";
        String[] choices = {"[A] are", "[B] do", "[C] is", "[D] have"};

        // Display question and choices
        System.out.println(question);
        for (String choice : choices) {
            System.out.println(choice);
        }

        // Prompt user for input
        System.out.print("Your choice [A/B/C/D]: ");
        String userChoice = scanner.nextLine();

        // Check answer
        String correctAnswer = "[A]";
        if (userChoice.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is " + correctAnswer);
        }

        scanner.close();
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Vocabulary Quiz!");
        scanner.close();
    }
}
