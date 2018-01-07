package Client.Menus.Controllers;

import Client.DatabaseClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    @FXML
    public FlowPane contentPane;

    @FXML
    public void initialize() throws IOException {
        FlowPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Client/Menus/fxml/PortfolioView.fxml"));
        contentPane.getChildren().add(pane);
    }
    public MainViewController() {

    }

    public void goToPortfolio(ActionEvent actionEvent) {
    }

    public void goToCoins(ActionEvent actionEvent) {
    }

    public void goToNews(ActionEvent actionEvent) {
    }
}
