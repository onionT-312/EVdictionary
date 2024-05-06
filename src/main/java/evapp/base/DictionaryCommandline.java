package evapp.base;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public DictionaryCommandline() {
        super();
    }

    public void showAllWords() {
        System.out.printf("%-20s| %-20s| %-20s\n", "No", "English", "Vietnamese");
        for (int i = 0; i < this.getWordsSorted().size(); i++) {
            Word word = this.getWordsSorted().get(i);
            System.out.printf("%-20s| %-20s| %-20s\n", i + 1, word.getWord_target(), word.getWord_explain());
        }
    }

    public void dictionaryBasic() {
        this.insertFromCommandLine();
        showAllWords();
    }

    public void dictionarySearcher(String prefix) {
        ArrayList<Word> searchResults = new ArrayList<>();
        for (Word word : getWordsSorted()) {
            if (word.getWord_target().startsWith(prefix)) {
                searchResults.add(word);
            }
        }
        System.out.println("Search results for prefix \"" + prefix + "\":");
        for (Word word : searchResults) {
            System.out.println(word.getWord_target());
        }
    }

    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");

        while (running) {
            System.out.print("Your action: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exiting application...");
                    running = false;
                    break;
                case 1:
                    // Add word
                    System.out.print("Enter English word: ");
                    String target = scanner.nextLine();
                    System.out.print("Enter Vietnamese explanation: ");
                    String explain = scanner.nextLine();
                    addWord(target, explain);
                    break;
                case 2:
                    // Remove word
                    System.out.print("Enter English word to remove: ");
                    String removeWord = scanner.nextLine();
                    removeWord(removeWord);
                    break;
                case 3:
                    // Update word
                    System.out.print("Enter English word to update: ");
                    String wordToUpdate = scanner.nextLine();
                    System.out.print("Enter new Vietnamese explanation: ");
                    String newExplain = scanner.nextLine();
                    updateWord(wordToUpdate, new Word(wordToUpdate, newExplain));
                    break;
                case 4:
                    // Display all words
                    showAllWords();
                    break;

                //lockup default in dictionary.txt
                case 5:
                    // Lookup word
                    dictionaryLookup();
                    break;

                //update sau
                case 6:
                    // Search words
                    System.out.print("Enter prefix to search: ");
                    String prefix = scanner.nextLine();
                    dictionarySearcher(prefix);
                    break;

                // chua code
//                case 7:
//                    // Game
//                    System.out.println("Choose a game:");
//                    System.out.println("1. Vocabulary Quiz");
//                    System.out.println("2. Meaningful Word Game");
//                    System.out.print("Your choice [1/2]: ");
//                    int gameChoice = scanner.nextInt();
//                    scanner.nextLine(); // Consume newline
//
//                    switch (gameChoice) {
//                        case 1:
//                            VocabularyQuiz.startGame();
//                            break;
//                        case 2:
//                            MeaningfulWordGame.startGame();
//                            break;
//                        default:
//                            System.out.println("Invalid choice.");
//                            break;
//                    }
//                    break;
                case 8:
                    // Import from file
                    insertFromFile("src/main/resources/data/dictionaries.txt");
                    break;
                case 9:
                    // Export to file
                    dictionaryExportToFile("src/main/resources/data/dictionaries_export.txt");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

