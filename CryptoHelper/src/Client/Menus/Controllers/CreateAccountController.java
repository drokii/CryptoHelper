package Client.Menus.Controllers;

import Client.DatabaseClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class CreateAccountController {
    @FXML
    public Button submitBtn;

    public DatabaseClient dbClient;

    public void setDbClient(DatabaseClient dbClient) {
        this.dbClient = dbClient;
    }

    @FXML
    private TextField usertextfield;
    @FXML
    private TextField passwordtextfield;

    public CreateAccountController() {
    }

    public void createAccountSubmit(ActionEvent actionEvent) throws ExecutionException, InterruptedException {
        int i = dbClient.createAccount(usertextfield.getText(), passwordtextfield.getText());
        switch (i) {
            case 1:
                accountCreated();
                closeWindow();

                break;
            case -1:
                accountAlreadyExistsDialog();
                break;
            case 2:
                noConnection();
                closeWindow();
                break;

        }
    }

    private void accountAlreadyExistsDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Account already exists");
        alert.setContentText("Please choose another username for your account.");
        alert.showAndWait();
    }

    private void accountCreated() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account created");
        alert.setHeaderText("Account has been successfully created!");
        alert.setContentText("You may now log in with your credentials.");
        alert.showAndWait();
    }

    private void noConnection() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("A connection could not be established with the server");
        alert.setContentText("Check your internet connection and try again later.");

        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) usertextfield.getScene().getWindow();
        stage.close();
    }
}
