package evapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class changeScene {
    public void Change(String path, AnchorPane root) throws Exception {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            System.out.println("Change operating");
            root.getChildren().setAll(anchorPane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
