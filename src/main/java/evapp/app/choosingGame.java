package evapp.app;

import evapp.userInterface;
import evapp.changeScene;
import javafx.event.ActionEvent;

/**
 * Lớp ChoosingGame là controller cho việc chọn trò chơi trong ứng dụng.
 */
public class choosingGame {

    /**
     * Phương thức chuyển đến trò chơi Hangman khi người dùng nhấn nút tương ứng.
     *
     * @param event Sự kiện ActionEvent.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void Hangman(ActionEvent event) throws Exception {
        changeScene scene = new changeScene();
        scene.Change("hangman.fxml",userInterface.global);
    }

    /**
     * Phương thức chuyển đến trò chơi Pronunciation khi người dùng nhấn nút tương ứng.
     *
     * @param event Sự kiện ActionEvent.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void pronunciationGame(ActionEvent event) throws Exception {
        changeScene scene = new changeScene();
        scene.Change("pronunciation.fxml",userInterface.global);
    }

    /**
     * Phương thức chuyển đến trò chơi Listen khi người dùng nhấn nút tương ứng.
     *
     * @param event Sự kiện ActionEvent.
     * @throws Exception Ngoại lệ có thể phát sinh.
     */
    public void ListenGame(ActionEvent event) throws Exception {
        changeScene scene = new changeScene();
        scene.Change("listen.fxml",userInterface.global);
    }
}
