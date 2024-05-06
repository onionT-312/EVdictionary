package evapp.app;

import evapp.dict.*;
import evapp.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The UI_dictionary class is the controller for the dictionary user interface component.
 */
public class UI_dictionary implements Initializable {

    @FXML
    private TextArea result;
    @FXML
    private ComboBox search;
    @FXML
    private AnchorPane search_root;

    public static AnchorPane search_global = new AnchorPane();
    public static boolean history_check = false;
    private static String word = "";

    public static Dictionaries d = addDatabase();

    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_global = search_root;
        search_global.setVisible(false);

        for(int i = 0; i < d.getSizeofDictionary();i++) {
            search.getItems().add(d.takeWord(i));
            new AutoCompleteComboBoxListener<>(search);
        }
        if (!word.isBlank()) enter();
    }

    /**
     * Retrieves the word from the database and displays its details.
     */
    public static Dictionaries addDatabase() {
        Dictionaries dict = userInterface.dictionary;
        Connection connection = null;
        PreparedStatement prepare = null;
        ResultSet resultset = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/AddWord.sqlite");
            prepare = connection.prepareStatement("SELECT * FROM MyWord");
            resultset = prepare.executeQuery();

            while (resultset.next()) {
                String word = resultset.getString(1);
                String meaning = resultset.getString(2);
                Word w = new Word(word, "");
                w.addMeaning(meaning);
                dict.addword(w);
            }
        } catch (Exception e) {}
        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (Exception e) {}
            }
            if (prepare != null) {
                try {
                    prepare.close();
                } catch (Exception e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {}
            }
        }
        return dict;
    }

    /**
     * Sets the word to be searched.
     *
     * @param s The word to be searched.
     */
    public static void setWord(String s) {
        word = s;
    }

    /**
     * Displays the details of the entered word.
     */
    public void enter() {
        if (Objects.equals(word, null)) return;
        for(int i = 0; i < d.getSizeofDictionary(); i++) {
            if(word.equals(d.takeWord(i).getWord_spelling())) {
                Word w = d.takeWord(i);
                String ans = w.print();
                result.setText(ans);
                break;
            }
        }
    }

    /**
     * Searches for the selected word and displays its details.
     *
     * @param event The ActionEvent that occurred.
     * @throws Exception If an error occurs.
     */
    public void confirm(ActionEvent event) throws Exception {
        word = (String) search.getSelectionModel().getSelectedItem();
        enter();
    }

    /**
     * Displays the search history.
     *
     * @param event The ActionEvent that occurred.
     * @throws Exception If an error occurs.
     */
    public void history_view(ActionEvent event) throws Exception {
        search_global.setVisible(true);
        changeScene changeScene = new changeScene();
        changeScene.Change("word_history.fxml",search_global);
    }

    /**
     * Opens the view to add a new word to the dictionary.
     *
     * @param event The ActionEvent that occurred.
     * @throws Exception If an error occurs.
     */
    public void addword_view(ActionEvent event) throws Exception {
        changeScene changeScene = new changeScene();
        changeScene.Change("addword.fxml",userInterface.global);
    }

    /**
     * Initiates the listening feature for the selected word.
     *
     * @param event The ActionEvent that occurred.
     * @throws Exception If an error occurs.
     */
    public void listening(ActionEvent event) throws Exception {
        if (Objects.equals(word, null)) return;
        wordListening wordListening = new wordListening(word, "en-US");
        wordListening.valueProperty().addListener(
                (observable, oldValue, newValue) -> newValue.start());
        System.out.println(word);
        Thread thread = new Thread(wordListening);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * An inner class for handling auto-completion in the ComboBox.
     */
    public class AutoCompleteComboBoxListener<T> implements EventHandler<InputEvent> {

        private ComboBox comboBox;
        private StringBuilder sb;
        private ObservableList<T> data;
        private boolean moveCaretToPos = false;
        private int caretPos;

        /**
         * Constructs an AutoCompleteComboBoxListener.
         *
         * @param comboBox The ComboBox to which auto-completion will be applied.
         */
        public AutoCompleteComboBoxListener(final ComboBox comboBox) {
            this.comboBox = comboBox;
            sb = new StringBuilder();
            data = comboBox.getItems();

            this.comboBox.setEditable(true);
            this.comboBox.setOnKeyPressed(new EventHandler<InputEvent>() {
                @Override
                public void handle(InputEvent t) {
                    comboBox.hide();
                }
            });
            this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        }

        /**
         * Handles input events to perform auto-completion.
         *
         * @param inputEvent The input event.
         */
        @Override
        public void handle(InputEvent inputEvent) {
            if(inputEvent instanceof KeyEvent) {
                KeyEvent event = (KeyEvent) inputEvent;
                if (event.getCode() == KeyCode.UP) {
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.DOWN) {
                    if (!comboBox.isShowing()) {
                        comboBox.show();
                    }
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();

                } else if (event.getCode() == KeyCode.DELETE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                } else if (event.getCode() == KeyCode.ENTER) {
                    comboBox.hide();
                    word = (String) search.getSelectionModel().getSelectedItem();
                    enter();
                    return;
                }

                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                        || event.isControlDown() || event.getCode() == KeyCode.HOME
                        || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                    return;
                }
            }

            ObservableList list = FXCollections.observableArrayList();
            if (AutoCompleteComboBoxListener.this.comboBox
                    .getEditor().getText().toLowerCase() == "") {
                list = data;
            } else {
                for (int i=0; i<data.size() & list.size() < 30; i++) {
                    if(data.get(i).toString().toLowerCase().startsWith(
                            AutoCompleteComboBoxListener.this.comboBox
                                    .getEditor().getText().toLowerCase())) {
                        list.add(data.get(i));
                    }
                }
            }
            String t = comboBox.getEditor().getText();

            comboBox.setItems(list);
            comboBox.getEditor().setText(t);
            if(!moveCaretToPos) {
                caretPos = -1;
            }
            comboBox.setVisibleRowCount(Math.min(10,list.size()));
            moveCaret(t.length());
            if(!list.isEmpty()) {
                comboBox.hide();
                comboBox.setVisibleRowCount(Math.min(10,list.size()));
                comboBox.show();
            } else {
                comboBox.hide();
            }
        }
        private void moveCaret(int textLength) {
            if(caretPos == -1) {
                comboBox.getEditor().positionCaret(textLength);
            } else {
                comboBox.getEditor().positionCaret(caretPos);
            }
            moveCaretToPos = false;
        }

    }
}
