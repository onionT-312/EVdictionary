package backend.englishlearning;

import java.util.Scanner;

/**
 * Manages the input and output operations of the dictionary.
 */
public class DictionaryManagement {
    private Scanner scanner; // Scanner object for reading input

    /**
     * Constructs a new DictionaryManagement object with a Scanner for input.
     */
    public DictionaryManagement() {
        scanner = new Scanner(System.in);
    }

    /**
     * Inserts words into the dictionary from the command line input.
     *
     * @param dictionary The dictionary to insert words into.
     */
    public void insertFromCommandLine(Dictionary dictionary) {
        System.out.print("Enter the number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after nextInt()
        for (int i = 0; i < n; i++) {
            System.out.print("Enter word " + (i + 1) + ": ");
            String target = scanner.nextLine();
            System.out.print("Enter explanation for word " + (i + 1) + ": ");
            String explain = scanner.nextLine();
            dictionary.addWord(new Word(target, explain));
        }
    }

    /**
     * Closes the Scanner object.
     */
    public void closeScanner() {
        scanner.close();
    }
}
