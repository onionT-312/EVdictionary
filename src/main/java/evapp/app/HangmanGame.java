package evapp.app;

import evapp.changeScene;
import evapp.dict.*;
import evapp.userInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Lớp HangmanGame là controller cho trò chơi Hangman.
 */
public class HangmanGame implements Initializable{

    @FXML
    ImageView hangman_Image;
    @FXML
    Text guess_text;
    @FXML
    Text lives_text;
    @FXML
    Pane button_pane;

    private int lives;
    private int right;
    private Word w;
    private String hangman_text;
    private String right_text;
    private Image image;

    /**
     * Phương thức khởi tạo, được gọi khi FXML file được load.
     *
     * @param url URL của FXML file.
     * @param resourceBundle ResourceBundle của FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            image = new Image(new FileInputStream("src/main/resources/pictures/Hangman0.jpg"));
            hangman_Image.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lives = 7;
        right = 0;
        guess_text.setText("");
        w = getRandomWord();
        hangman_text = "";
        right_text = "";
        String liveText = "Lives: " + lives;
        lives_text.setText(liveText);
        for (int i = 0; i < w.getWord_spelling().length() * 2; i++) {
            if (i % 2 == 0) {
                hangman_text += "_";
                right_text += w.getWord_spelling().charAt(i/2);
            } else {
                hangman_text += " ";
                right_text += " ";
            }
        }
        String s = w.getWord_spelling().toUpperCase();
        right_text = right_text.toUpperCase();
        if (s.contains("-")) {
            int index = s.indexOf("-");
            while (index != -1) {
                String temp = hangman_text.substring(0, index * 2) + "-"
                        + hangman_text.substring(index * 2 + 1);
                hangman_text = temp;
                guess_text.setText(hangman_text);
                index = s.indexOf('-', index + 1);
                right++;
            }
            if (right == s.length()) {
                button_pane.setDisable(true);
                guess_text.setText(right_text);
            }
        }
        guess_text.setText(hangman_text);
    }

    /**
     * Phương thức lấy ngẫu nhiên một từ từ từ điển.
     *
     * @return Một từ ngẫu nhiên từ từ điển.
     */
    public static Word getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(userInterface.dictionary.getSizeofDictionary());
        Word word = userInterface.dictionary.takeWord(index);
        return word;
    }

    /**
     * Xử lý sự kiện khi người dùng nhấn vào một nút chữ cái để đoán.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào nút.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void clicked(ActionEvent event) throws Exception {
        ((Button) event.getSource()).setDisable(true);
        String character = ((Button)event.getSource()).getText();
        String s = w.getWord_spelling().toUpperCase();
        if (s.contains(character)) {
            int index = s.indexOf(character);
            while (index != -1) {
                String temp = hangman_text.substring(0, index * 2) + character
                        + hangman_text.substring(index * 2 + 1);
                hangman_text = temp;
                guess_text.setText(hangman_text);
                index = s.indexOf(character, index + 1);
                right++;
            }
            if (right == s.length()) {
                button_pane.setDisable(true);
                guess_text.setText(right_text);
                String liveText = "WELL DONE!";
                lives_text.setText(liveText);
                try {
                    image = new Image(new FileInputStream("src/main/resources/pictures/true.png"));
                    hangman_Image.setImage(image);
                } catch (Exception e) {}
            }
        } else {
            lives--;
            try {
                if (lives == 6) image = new Image(new FileInputStream("src/main/resources/pictures/Hangman1.jpg"));
                else if (lives == 5) image = new Image(new FileInputStream("src/main/resources/pictures/Hangman2.jpg"));
                else if (lives == 4) image = new Image(new FileInputStream("src/main/resources/pictures/Hangman3.jpg"));
                else if (lives == 3) image = new Image(new FileInputStream("src/main/resources/pictures/Hangman4.jpg"));
                else if (lives == 2) image = new Image(new FileInputStream("src/main/resources/pictures/Hangman5.jpg"));
                else if (lives == 1) image = new Image(new FileInputStream("src/main/resources/pictures/Hangman6.jpg"));
                else if (lives == 0) image = new Image(new FileInputStream("src/main/resources/pictures/gameover.jpg"));
                hangman_Image.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (lives == 0) {
                button_pane.setDisable(true);
                guess_text.setText(right_text);
                String liveText = "DIE";
                lives_text.setText(liveText);
            } else {
                String liveText = "Lives: " + lives;
                lives_text.setText(liveText);
            }
        }
    }

    /**
     * Phương thức xử lý sự kiện khi người dùng muốn chơi lại.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào nút "Try Again".
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void TryAgain(ActionEvent event) throws Exception {
        changeScene changeScene = new changeScene();
        changeScene.Change("hangman.fxml", userInterface.global);
    }

    /**
     * Phương thức xử lý sự kiện khi người dùng muốn quay lại màn hình chọn trò chơi.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào nút "Return Back".
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void ReturnBack(ActionEvent event) throws Exception {
        changeScene changeScene = new changeScene();
        changeScene.Change("ChooseGame.fxml", userInterface.global);
    }
}
