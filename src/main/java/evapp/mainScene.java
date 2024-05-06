package evapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.FileInputStream;
import java.io.InputStream;


public class mainScene extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        Scene scene = new Scene(root);
        InputStream inputStream = new FileInputStream("src/main/resources/pictures/ikon.png");
        Image icon = new Image(inputStream);
        stage.getIcons().add(icon);
        stage.setTitle("EV-APP");
        stage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.show();
    }


    public static void main (String[] args) throws Exception {
        launch(args);
    }
}