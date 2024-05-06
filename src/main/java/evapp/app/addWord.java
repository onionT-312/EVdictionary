package evapp.app;

import evapp.changeScene;
import evapp.dict.Word;
import evapp.userInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Lớp AddWord là controller cho giao diện thêm từ vựng vào cơ sở dữ liệu.
 */
public class addWord implements Initializable {

    @FXML
    private TextArea addword_text;
    @FXML
    private TextArea addword_meaning;
    @FXML
    private TableView<Word> my_word_table;
    @FXML
    private TableColumn<Word, String> my_word;

    Connection connection = null;
    PreparedStatement prepare = null;
    ResultSet resultset = null;

    private ObservableList<Word> word_list;
    public final String addword_path = "jdbc:sqlite:src/main/resources/AddWord.sqlite";

    /**
     * Phương thức khởi tạo, được gọi khi FXML được load.
     *
     * @param url            URL tới FXML file.
     * @param resourceBundle ResourceBundle của FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        word_list = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(addword_path);
            prepare = connection.prepareStatement("SELECT * FROM MyWord");
            resultset = prepare.executeQuery();

            while (resultset.next()) {
                String word = resultset.getString(1);
                String meaning = resultset.getString(2);
                Word w = new Word(word, "");
                w.addMeaning(meaning);
                word_list.add(w);
            }
        } catch (Exception e) {}
        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (Exception e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {}
            }
        }
        my_word.setCellValueFactory(new PropertyValueFactory<Word, String>("word_spelling"));
        my_word_table.setItems(word_list);
    }

    /**
     * Phương thức thêm từ vào cơ sở dữ liệu.
     *
     * @param word    Từ cần thêm.
     * @param meaning Ý nghĩa của từ.
     */
    public void AddtoSQL(String word, String meaning) {
        Connection connect = null;
        PreparedStatement psInsert = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:src/main/resources/AddWord.sqlite");
            psInsert = connect.prepareStatement("INSERT INTO MyWord VALUES (?, ?)");
            psInsert.setString(1, word);
            psInsert.setString(2, meaning);
            psInsert.executeUpdate();
        } catch (Exception e) {}
        finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (Exception e) {}
            }
            if (connect != null) {
                try {
                    connect.close();
                } catch (Exception e) {}
            }
        }
    }

    /**
     * Phương thức xóa từ khỏi cơ sở dữ liệu và khỏi TableView.
     *
     * @param event Sự kiện ActionEvent.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void Delete(ActionEvent event) throws Exception {
        Word w = my_word_table.getSelectionModel().getSelectedItem();
        UI_dictionary.d.delword(w);
        word_list.remove(w);
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(addword_path);
            prepare = connection.prepareStatement("DELETE FROM MyWord WHERE Word = ?");
            prepare.setString(1, w.getWord_spelling());
            prepare.executeUpdate();
        } catch (Exception e) {}
        finally {
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

    }

    /**
     * Phương thức thêm từ vào cơ sở dữ liệu và TableView.
     *
     * @param event Sự kiện ActionEvent.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void Add(ActionEvent event) throws Exception {
        String word = addword_text.getText();
        String meaning = addword_meaning.getText();
        if(word.isBlank() || meaning.isBlank()) return;
        addword_text.setText("");
        addword_meaning.setText("");
        Word w = new Word(word, "");
        w.addMeaning(meaning);
        UI_dictionary.d.addword(w);
        word_list.add(w);
        AddtoSQL(word, meaning);
    }

    /**
     * Phương thức quay lại giao diện tìm kiếm từ.
     *
     * @param event Sự kiện ActionEvent.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void ReturnBack(ActionEvent event) throws Exception {
        changeScene changeScene = new changeScene();
        changeScene.Change("searchingzone.fxml", userInterface.global);
    }
}
