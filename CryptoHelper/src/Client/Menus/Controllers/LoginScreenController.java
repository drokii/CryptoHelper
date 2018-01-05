package Client.Menus.Controllers;

import Client.DatabaseClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.concurrent.*;

public class LoginScreenController {

    private MainViewController viewController;
    private DatabaseClient dbClient;

    public Button loginbtn;
    public TextField passwordtextfield;
    public TextField usertextfield;
    private int connectionStatus;
    private ExecutorService pool;

    public LoginScreenController() {
        pool = Executors.newFixedThreadPool(4);
    }

    private Callable checkLoginStatus() {
        Callable connectionCheck = new Callable() {
            @Override
            public Integer call() throws InterruptedException {

                int max = 10000;


                for (int i = 0; i < max; i++) {
                    int oldvalue = connectionStatus;
                    int newvalue = dbClient.getConnectionStatus();

                    if (oldvalue != newvalue) {
                        connectionStatus = newvalue;
                        System.out.println("Change Found!");
                        return newvalue;
                    }

                }
                return null;
            }
        };
        return connectionCheck;
    }

    @FXML
    private void logIn(ActionEvent actionEvent) throws InterruptedException, ExecutionException {

        Future<Integer> f1 = pool.submit(checkLoginStatus());
        dbClient.logIn(usertextfield.getText(), passwordtextfield.getText());
        Integer callableResult = f1.get();

        switch (callableResult) {
            case 1:
                try {
                    logInSuccess();
                } catch (IOException e) {
                    logInNoConnection();
                }
                break;
            case 2:
                logInNoConnection();
                break;
            case -1:
                logInFail();
        }


    }

    private void logInSuccess() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Client/Menus/fxml/PortfolioView.fxml"));
        viewController.setScene(parent);
    }

    private void logInFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Wrong Password");
        alert.setContentText("Please rewrite the password and try again");
        alert.showAndWait();
    }

    private void logInNoConnection() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("A connection could not be established with the server");
        alert.setContentText("Check your internet connection and try again later.");

        alert.showAndWait();
        System.exit(0);
    }


    @FXML
    private void createAccount(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Client/Menus/fxml/CreateAccountView.fxml"));
        viewController.setScene(parent);
    }

    public void setDbClient(DatabaseClient dbClient) {
        this.dbClient = dbClient;
        connectionStatus = dbClient.getConnectionStatus();
    }


}
