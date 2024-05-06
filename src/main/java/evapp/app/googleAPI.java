//////package evapp.app;
//////
//////import evapp.API;
//////import javafx.event.ActionEvent;
//////import javafx.event.EventHandler;
//////import javafx.fxml.FXML;
//////import javafx.fxml.Initializable;
//////import javafx.scene.control.Label;
//////import javafx.scene.control.TextArea;
//////import javafx.scene.input.InputEvent;
//////import javafx.scene.input.KeyEvent;
//////
//////import java.net.URL;
//////import java.util.ResourceBundle;
//////
///////**
////// * Lớp GoogleAPI là controller cho giao diện tương tác với API của Google Translate.
////// */
//////public class googleAPI implements Initializable {
//////
//////    @FXML
//////    private TextArea main_text;
//////    @FXML
//////    private TextArea trans_text;
//////    @FXML
//////    private Label main_label;
//////    @FXML
//////    private Label trans_label;
//////
//////    private String language1 = "English";
//////    private String language2 = "Vietnamese";
//////
//////    private API api;
//////    private boolean swapped = false;
//////
//////    /**
//////     * Phương thức khởi tạo, được gọi khi FXML file được load.
//////     *
//////     * @param url URL của FXML file.
//////     * @param resourceBundle ResourceBundle của FXML file.
//////     */
//////    @Override
//////    public void initialize(URL url, ResourceBundle resourceBundle) {
//////        main_label.setText(language1);
//////        trans_label.setText(language2);
//////        main_text.setOnKeyReleased(new EventHandler<InputEvent>() {
//////            @Override
//////            public void handle(InputEvent t) {
//////                if (t instanceof KeyEvent) {
//////                    //KeyEvent event = (KeyEvent) t;
//////                    try {
//////                        findans();
//////                    } catch (Exception e) {}
//////                }
//////            }
//////        });
//////    }
//////
//////    /**
//////     * Phương thức đổi ngôn ngữ hiển thị và dịch của ứng dụng.
//////     *
//////     * @param event Sự kiện ActionEvent khi người dùng nhấn nút đổi ngôn ngữ.
//////     * @throws Exception Ngoại lệ có thể phát sinh.
//////     */
//////    public void swap(ActionEvent event) throws Exception {
////////        String tmp = language1;
////////        language1 = language2;
////////        language2 = tmp;
////////        main_label.setText(language1);
////////        trans_label.setText(language2);
//////        swapped = !swapped;
//////        updateLabel();
//////    }
//////
//////    public void updateLabel() {
//////        if (swapped) {
//////            main_label.setText(language1);
//////            trans_label.setText(language2);
//////            main_text.setPromptText("Insert your word");
//////        } else {
//////            main_label.setText(language2);
//////            trans_label.setText(language1);
//////            main_text.setPromptText("Nhập từ của bạn");
//////        }
//////    }
//////
//////    /**
//////     * Phương thức tìm kiếm và hiển thị kết quả dịch.
//////     *
//////     * @throws Exception Ngoại lệ có thể phát sinh.
//////     */
//////    public void findans() throws Exception {
//////        if (main_text.getText().trim().isEmpty()) return;
//////        String s = main_text.getText();
//////        api = new API(s, language1, language2);
//////        api.valueProperty().addListener((observable, oldValue, newValue) -> trans_text.setText(String.valueOf(newValue)));
//////        System.out.println(s);
//////        Thread th = new Thread(api);
//////        th.setDaemon(true);
//////        th.start();
//////    }
//////}
////
////package evapp.app;
////
////import evapp.API;
////import javafx.event.ActionEvent;
////import javafx.event.EventHandler;
////import javafx.fxml.FXML;
////import javafx.fxml.Initializable;
////import javafx.scene.control.Label;
////import javafx.scene.control.TextArea;
////import javafx.scene.input.InputEvent;
////import javafx.scene.input.KeyEvent;
////
////import java.io.UnsupportedEncodingException;
////import java.net.URL;
////import java.util.ResourceBundle;
////
////public class googleAPI implements Initializable {
////
////    @FXML
////    private TextArea main_text;
////    @FXML
////    private TextArea trans_text;
////    @FXML
////    private Label main_label;
////    @FXML
////    private Label trans_label;
////
////    private String language1 = "English";
////    private String language2 = "Vietnamese";
////
////    private API api;
////
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        main_label.setText(language1);
////        trans_label.setText(language2);
////        main_text.setOnKeyReleased(new EventHandler<InputEvent>() {
////            @Override
////            public void handle(InputEvent t) {
////                if (t instanceof KeyEvent) {
////                    // KeyEvent event = (KeyEvent) t;
////                    try {
////                        findans();
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        });
////    }
////
////    public void swap(ActionEvent event) throws Exception {
////        String tmp = language1;
////        language1 = language2;
////        language2 = tmp;
////        main_label.setText(language1);
////        trans_label.setText(language2);
////        // Đổi prompt text tương ứng với ngôn ngữ mới
////        if (language1.equals("English")) {
////            main_text.setPromptText("Insert your words");
////        } else {
////            main_text.setPromptText("Nhập từ của bạn");
////        }
////    }
////
////    public void findans() throws Exception {
////        if (main_text.getText().trim().isEmpty()) return;
////        String s = main_text.getText();
////        api = new API(s, language1, language2);
////        api.valueProperty().addListener((observable, oldValue, newValue) -> trans_text.setText(String.valueOf(newValue)));
////        System.out.println(s);
////        Thread th = new Thread(api);
////        th.setDaemon(true);
////        th.start();
////    }
////}
//
//package evapp.app;
//
//import evapp.API;
//import evapp.wordListening;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.InputEvent;
//import javafx.scene.input.KeyEvent;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
///**
// * Lớp GoogleAPI là controller cho giao diện tương tác với API của Google Translate.
// */
//public class googleAPI implements Initializable {
//
//    @FXML
//    private TextArea main_text;
//    @FXML
//    private TextArea trans_text;
//    @FXML
//    private Label main_label;
//    @FXML
//    private Label trans_label;
//
//    private String language1 = "English";
//    private String language2 = "Vietnamese";
//    private String promptTextEnglish = "Insert your words";
//    private String promptTextVietnamese = "Nhập từ của bạn";
//
//    private API api;
//
//    /**
//     * Phương thức khởi tạo, được gọi khi FXML file được load.
//     *
//     * @param url URL của FXML file.
//     * @param resourceBundle ResourceBundle của FXML file.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        main_label.setText(language1);
//        trans_label.setText(language2);
//        main_text.setPromptText(promptTextEnglish);
//        main_text.setOnKeyReleased(new EventHandler<InputEvent>() {
//            @Override
//            public void handle(InputEvent t) {
//                if (t instanceof KeyEvent) {
//                    //KeyEvent event = (KeyEvent) t;
//                    try {
//                        findans();
//                    } catch (Exception e) {}
//                }
//            }
//        });
//    }
//
//    /**
//     * Phương thức đổi ngôn ngữ hiển thị và dịch của ứng dụng.
//     *
//     * @param event Sự kiện ActionEvent khi người dùng nhấn nút đổi ngôn ngữ.
//     * @throws Exception Ngoại lệ có thể phát sinh.
//     */
//    public void swap(ActionEvent event) throws Exception {
//        String tmpLanguage = language1;
//        language1 = language2;
//        language2 = tmpLanguage;
//
//        main_label.setText(language1);
//        trans_label.setText(language2);
//
//        if (language1.equals("English")) {
//            main_text.setPromptText(promptTextEnglish);
//        } else {
//            main_text.setPromptText(promptTextVietnamese);
//        }
//    }
//
//    /**
//     * Phương thức tìm kiếm và hiển thị kết quả dịch.
//     *
//     * @throws Exception Ngoại lệ có thể phát sinh.
//     */
//    public void findans() throws Exception {
//        if (main_text.getText().trim().isEmpty()) return;
//        String s = main_text.getText();
//        api = new API(s, language1, language2);
//        api.valueProperty().addListener((observable, oldValue, newValue) -> trans_text.setText(String.valueOf(newValue)));
////        System.out.println(s);
//        Thread th = new Thread(api);
//        th.setDaemon(true);
//        th.start();
//    }
//}
//


package evapp.app;

import evapp.API;
import evapp.wordListening;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class googleAPI implements Initializable {

    @FXML
    private TextArea main_text;
    @FXML
    private TextArea trans_text;
    @FXML
    private Label main_label;
    @FXML
    private Label trans_label;

    private String language1 = "English";
    private String language2 = "Vietnamese";
    private String promptTextEnglish = "Insert your words";
    private String promptTextVietnamese = "Nhập từ của bạn";

    private API api;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main_label.setText(language1);
        trans_label.setText(language2);
        main_text.setPromptText(promptTextEnglish);
        main_text.setOnKeyReleased(new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent t) {
                if (t instanceof KeyEvent) {
                    try {
                        findans();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void swap(ActionEvent event) {
        String tmpLanguage = language1;
        language1 = language2;
        language2 = tmpLanguage;

        main_label.setText(language1);
        trans_label.setText(language2);

        if (language1.equals("English")) {
            main_text.setPromptText(promptTextEnglish);
        } else {
            main_text.setPromptText(promptTextVietnamese);
        }
    }

    public void findans() {
        if (main_text.getText().trim().isEmpty()) return;
        String s = main_text.getText();
        api = new API(s, language1, language2);
        api.valueProperty().addListener((observable, oldValue, newValue) -> trans_text.setText(String.valueOf(newValue)));
        Thread th = new Thread(api);
        th.setDaemon(true);
        th.start();
    }

    public void listenMain(ActionEvent event) throws Exception {
        String word = main_text.getText();
        String language = main_label.getText().equals("English") ? "en-US" : "vi-VN";
        if (word.trim().isEmpty()) return;
        wordListening wordListener = new wordListening(word, language);
        wordListener.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        newValue.start();
                    }
                });
        System.out.println(word);
        Thread thread = new Thread(wordListener);
        thread.setDaemon(true);
        thread.start();
    }

    public void listenTrans(ActionEvent event) throws Exception {
        String word = trans_text.getText();
        String language = trans_label.getText().equals("English") ? "en-US" : "vi-VN";
        if (word.trim().isEmpty()) return;
        wordListening wordListener = new wordListening(word, language);
        wordListener.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        newValue.start();
                    }
                });
        System.out.println(word);
        Thread thread = new Thread(wordListener);
        thread.setDaemon(true);
        thread.start();
    }

}
