package evapp.app;

import evapp.changeScene;
import evapp.dict.Word;
import evapp.userInterface;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Objects;

/**
 * Lớp PronunciationGame là controller cho trò chơi đoán từ dựa trên phát âm.
 */
public class PronunciationGame implements Initializable {

    @FXML
    private ImageView life5;
    @FXML
    private ImageView life4;
    @FXML
    private ImageView life3;
    @FXML
    private ImageView life2;
    @FXML
    private ImageView life1;
    @FXML
    private ImageView PronImage;
    @FXML
    private JFXTextField answer_text;
    @FXML
    private JFXTextField pronunciation_text;
    @FXML
    private JFXButton submit;
    @FXML
    private Label final_ans;
    @FXML
    private Label scoreLabel;

    private int lives;
    private Word word;
    private Image image;
    private boolean check;

    private int score;


    /**
     * Phương thức khởi tạo, được gọi khi FXML file được load.
     *
     * @param url URL của FXML file.
     * @param resourceBundle ResourceBundle của FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //new
        score = 0;


        word = getRandomWord();
        System.out.println(word.getWord_spelling());
        lives = 5;
        check = true;
        String pron = "/" + word.getWord_pronunciation() + "/";
        pronunciation_text.setText(pron);
        answer_text.setOnKeyPressed(new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent t) {
                if(t instanceof KeyEvent) {
                    KeyEvent event = (KeyEvent) t;
                    if (event.getCode() == KeyCode.ENTER) {
                        if (check) {
                            try {
                                confirm();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        try {
            image = new Image(new FileInputStream("src/main/resources/pictures/guess.png"));
            PronImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
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
        changeScene.Change("pronunciation.fxml", userInterface.global);
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

    /**
     * Phương thức xử lý sự kiện khi người dùng muốn xác nhận câu trả lời.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào nút "Submit".
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void enter(ActionEvent event) throws Exception {
        if (!check) return;
        confirm();
    }

    /**
     * Phương thức để lấy một từ ngẫu nhiên từ danh sách từ.
     *
     * @return Từ ngẫu nhiên.
     */
    public static Word getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(userInterface.NormalWord.size());
        Word word = userInterface.NormalWord.get(index);
        return word;
    }

    /**
     * Phương thức xác nhận câu trả lời và kiểm tra đúng/sai.
     *
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void confirm() throws Exception {
        if (Objects.equals(answer_text.getText(), "")) return;
        if (answer_text.getText().trim().equals(word.getWord_spelling())) {
            image = new Image(new FileInputStream("src/main/resources/pictures/true.png"));
            PronImage.setImage(image);
            check = false;
            final_ans.setText(word.getWord_spelling());
            final_ans.setTextFill(Color.web("008800"));

            //new
            score +=10;
            updateScoreLabel();
            changeWord();
        } else {
            lives--;
            if (lives == 4) {
                life5.setVisible(false);
                image = new Image(new FileInputStream("src/main/resources/pictures/wrong.png"));
                PronImage.setImage(image);
            } else if (lives == 3) {
                life4.setVisible(false);
                image = new Image(new FileInputStream("src/main/resources/pictures/wrong.png"));
                PronImage.setImage(image);
            } else if (lives == 2) {
                life3.setVisible(false);
                image = new Image(new FileInputStream("src/main/resources/pictures/wrong.png"));
                PronImage.setImage(image);
            } else if (lives == 1) {
                life2.setVisible(false);
                image = new Image(new FileInputStream("src/main/resources/pictures/wrong.png"));
                PronImage.setImage(image);
            } else if (lives == 0) {
                life1.setVisible(false);
                image = new Image(new FileInputStream("src/main/resources/pictures/lose.png"));
                PronImage.setImage(image);
                check = false;
                final_ans.setText(word.getWord_spelling());
                final_ans.setTextFill(Color.web("#FF0000"));
            }
        }
    }


    //new
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    private void changeWord() throws FileNotFoundException {
        word = getRandomWord();

        image = new Image(new FileInputStream("src/main/resources/pictures/true.png"));
        PronImage.setImage(image);
        check = false;
        final_ans.setTextFill(Color.web("008800"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateWordDisplay();
            }
        }));
        timeline.play();
        check = true;
    }

    private void updateWordDisplay() {
        System.out.println(word.getWord_spelling());
        pronunciation_text.setText("/" + word.getWord_pronunciation() + "/");
        answer_text.clear();
        final_ans.setText("");
        try {
            image = new Image(new FileInputStream("src/main/resources/pictures/guess.png"));
            PronImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
