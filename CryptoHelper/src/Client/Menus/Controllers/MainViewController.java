package Client.Menus.Controllers;

import Client.ClientContainer;
import Client.CoinClient;
import Client.DatabaseClient;
import Client.NewsClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainViewController {
    @FXML
    public FlowPane contentPane;
    private ClientContainer clientContainer;

    @FXML
    public void initialize() throws IOException {
        FlowPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Client/Menus/fxml/PortfolioView.fxml"));
        contentPane.getChildren().setAll(pane);
    }

    public MainViewController() throws ExecutionException, InterruptedException {
        CoinClient coinClient = new CoinClient();
        NewsClient newsClient = new NewsClient();
        this.clientContainer = new ClientContainer(coinClient, newsClient);

    }

    public void goToPortfolio(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Client/Menus/fxml/PortfolioView.fxml"));
        FlowPane pane = loader.load();
        PortfolioViewController portfolioViewController = loader.getController();
        portfolioViewController.setClientContainer(clientContainer);

        contentPane.getChildren().setAll(pane);
    }

    public void goToCoins(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Client/Menus/fxml/CoinView.fxml"));
        FlowPane pane = loader.load();
        CoinViewController coinViewController = loader.getController();
        coinViewController.setClientContainer(clientContainer);

        contentPane.getChildren().setAll(pane);

    }

    public void goToNews(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Client/Menus/fxml/NewsView.fxml"));
        FlowPane pane = loader.load();
        NewsViewController newsViewController = loader.getController();
        newsViewController.setClientContainer(clientContainer);

        contentPane.getChildren().setAll(pane);
    }
}
