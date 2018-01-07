
import Client.DatabaseClient;
import Client.Menus.Controllers.LoginScreenController;
import Client.Menus.Controllers.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public Stage mainStage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client/Menus/fxml/LoginScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        LoginScreenController loginScreenController = loader.getController();
        loginScreenController.setDbClient(new DatabaseClient());

        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("CryptoHelper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
