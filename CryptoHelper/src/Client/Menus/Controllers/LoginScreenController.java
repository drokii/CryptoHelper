package Client.Menus.Controllers;

import Client.ClientContainer;
import Client.CoinClient;
import Client.DatabaseClient;
import Client.NewsClient;
import com.sun.deploy.util.SessionState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LoginScreenController {


    private DatabaseClient dbClient;

    public Button loginbtn;
    public TextField passwordtextfield;
    public TextField usertextfield;

    public LoginScreenController() {
    }


    @FXML
    private void logInSubmit(ActionEvent actionEvent) throws InterruptedException, ExecutionException, IOException {
        int i = dbClient.logIn(usertextfield.getText(), passwordtextfield.getText());
        switch (i) {
            case 1:
                try {
                    goodLoginProceed();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case -1:
                badPasswordDialog();
                break;
            case 2:
                //badConnectionDialog();
                try {
                    goodLoginProceed(); // testing goes faster like this bo
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }


    private void goodLoginProceed() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Client/Menus/fxml/MainView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Cryptohelper");
        stage.setScene(scene);
        stage.show();
        closeWindow();

    }

    private void badPasswordDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Wrong Password or Session in use.");
        alert.setContentText("Please rewrite the password and try again. If you still encounter this error, the account entered may still be in use.");
        alert.showAndWait();
    }

    private void badConnectionDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("A connection could not be established with the server");
        alert.setContentText("Check your internet connection and try again later.");

        alert.showAndWait();
    }


    @FXML
    private void createAccount(ActionEvent actionEvent) throws IOException {
        if (dbClient.getResponseStatus() != 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Client/Menus/fxml/CreateAccountView.fxml"));
            AnchorPane anchorPane = loader.load();
            CreateAccountController createAccountController = loader.getController();
            createAccountController.setDbClient(this.dbClient);

            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Create Account");
            stage.setScene(scene);
            stage.show();
        } else {
            badConnectionDialog();
        }


    }

    private void closeWindow() {
        Stage stage = (Stage) usertextfield.getScene().getWindow();
        stage.close();
    }

    public void setDbClient(DatabaseClient dbClient) {
        this.dbClient = dbClient;
    }

}
