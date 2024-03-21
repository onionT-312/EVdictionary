package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DictionaryApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();

        // Set action for the search button
        searchButton.setOnAction(e -> {
            // Perform search logic here
            // For example:
            String searchWord = searchField.getText();
            String meaning = performSearch(searchWord); // Assume this method is implemented elsewhere
            resultLabel.setText(meaning);
        });

        // Create layout
        HBox root = new HBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(searchField, searchButton, resultLabel);

        // Create scene
        Scene scene = new Scene(root, 1000, 600);

        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary App");
        primaryStage.show();
    }

    // Method to perform search (to be implemented)
    private String performSearch(String word) {
        // Perform search logic here (e.g., look up the word in the dictionary)
        // Return the meaning of the word
        return "Meaning of " + word;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
