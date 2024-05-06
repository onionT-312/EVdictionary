package evapp.app;

import evapp.changeScene;
import evapp.dict.Word;
import evapp.userInterface;
import evapp.wordListening;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Random;

/**
 * Lớp ListeningGame là controller cho trò chơi nghe và đoán từ.
 */
public class ListeningGame implements Initializable {

    @FXML
    private Button choice_A;
    @FXML
    private Button choice_B;
    @FXML
    private Button choice_C;
    @FXML
    private Button choice_D;
    @FXML
    private ImageView LisGameImg;
    @FXML
    private Label scoreLabel;

    private int index;
    private boolean check = true;
    private Image image;
    private int score;

    private ArrayList<Word> words;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Các bước khởi tạo ban đầu như đã có trong mã của bạn
        initializeGame();

        score = 0;
    }

    // Phương thức để khởi tạo một đoạn game mới
    private void initializeGame() {
        final int[] ints = new Random().ints(1, userInterface.dictionary.getSizeofDictionary()).distinct().limit(4).toArray();
        words = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            words.add(userInterface.dictionary.takeWord(ints[i]));
        }
        try {
            image = new Image(new FileInputStream("src/main/resources/pictures/question.png"));
            LisGameImg.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        check = true;
        choice_A.setText(words.get(0).getWord_spelling());
        choice_B.setText(words.get(1).getWord_spelling());
        choice_C.setText(words.get(2).getWord_spelling());
        choice_D.setText(words.get(3).getWord_spelling());
        index = new Random().nextInt(4);
        try {
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức xác nhận lựa chọn của người dùng và kiểm tra đáp án.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào một trong 4 nút lựa chọn.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void confirm(ActionEvent event) throws Exception {
        if (!check) return;
        String choice = ((Button) event.getSource()).getAccessibleText();
        int i = 0;
        if (choice.equals("0")) i = 0;
        else if (choice.equals("1")) i = 1;
        else if (choice.equals("2")) i = 2;
        else if (choice.equals("3")) i = 3;

        if (i == index) {
            ButtonNum(index).setStyle("-fx-background-color: #04FF00");
            check = false;
            image = new Image(new FileInputStream("src/main/resources/pictures/true.png"));
            LisGameImg.setImage(image);

            score += 10;
            updateScoreLabel();

            // Sử dụng Timeline để tạm dừng trong khoảng thời gian 1-2 giây trước khi reset lại trò chơi
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(new Random().nextInt(2) + 1), e -> {
                resetChoicesAndMoveToNewWord();
            }));
            timeline.play();
        } else {
            ButtonNum(index).setStyle("-fx-background-color: #04FF00");
            ButtonNum(i).setStyle("-fx-background-color: #FF0000");
            check = false;
            image = new Image(new FileInputStream("src/main/resources/pictures/wrong.png"));
            LisGameImg.setImage(image);
            System.out.println("!!!YOU LOSE!!!");
        }
    }

    private void resetChoicesAndMoveToNewWord() {
        try {
            // Reset lại 4 nút chọn
            choice_A.setStyle("");
            choice_B.setStyle("");
            choice_C.setStyle("");
            choice_D.setStyle("");
            check = true;

            // Chọn từ mới và khởi tạo lại trò chơi
            initializeGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Phương thức trả về nút theo chỉ số.
     *
     * @param i Chỉ số của nút.
     * @return Nút tương ứng với chỉ số.
     */
    private Button ButtonNum(int i) {
        if (i == 0) return choice_A;
        if (i == 1) return choice_B;
        if (i == 2) return choice_C;
        return choice_D;
    }

    /**
     * Phương thức xử lý sự kiện khi người dùng muốn chơi lại.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào nút "Try Again".
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void TryAgain(ActionEvent event) throws Exception {
        changeScene changeScene = new changeScene();
        changeScene.Change("listen.fxml", userInterface.global);
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
     * Phương thức xử lý sự kiện khi người dùng muốn nghe lại từ.
     *
     * @param event Sự kiện ActionEvent khi người dùng nhấn vào nút "Listening".
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void listening(ActionEvent event) throws Exception {
        listen();
    }

    /**
     * Phương thức để nghe từ và hiển thị hình ảnh.
     *
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void listen() throws Exception {
        wordListening wordListening = new wordListening(words.get(index).getWord_spelling(), "en-US");
        wordListening.valueProperty().addListener(
                (observable, oldValue, newValue) -> newValue.start());
        System.out.println(words.get(index).getWord_spelling());
        Thread thread = new Thread(wordListening);
        thread.setDaemon(true);
        thread.start();
    }


    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }
}
