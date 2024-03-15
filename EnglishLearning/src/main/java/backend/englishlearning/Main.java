package backend.englishlearning;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to launch the English Learning application.
 */
public class Main extends Application {

    /**
     * The entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create a new instance of the Dictionary class
        Dictionary dictionary = new Dictionary();

        // Create a new instance of the DictionaryCommandline class
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

        // Perform basic dictionary operations and display all words
        dictionaryCommandline.dictionaryBasic(dictionary);
    }
}
