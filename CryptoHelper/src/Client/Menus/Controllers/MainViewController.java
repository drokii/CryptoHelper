package Client.Menus.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    public static Stage stage;

    public MainViewController(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Parent parent) throws IOException {
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
}
