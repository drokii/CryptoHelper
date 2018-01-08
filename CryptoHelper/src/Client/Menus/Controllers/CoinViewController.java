package Client.Menus.Controllers;

import Client.ClientContainer;
import CoinServer.Coin;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.concurrent.ExecutionException;

public class CoinViewController {

    public TableView coinTable;
    public TableColumn nameCol;
    public TableColumn highCol;
    public TableColumn lowCol;
    public TableColumn volumeCol;
    public TableColumn bidCol;
    public TableColumn askCol;
    public TableColumn openOrdersCol;
    public TableColumn openSellCol;
    public TableColumn openBuyCol;
    public TableColumn createdCol;

    private ClientContainer clientContainer;
    private ObservableList<Coin> coins;


    public void setClientContainer(ClientContainer clientContainer) throws ExecutionException, InterruptedException {
        this.clientContainer = clientContainer;
        coins = clientContainer.getCoins();
        coinTable.setItems(coins);
    }


    public CoinViewController() {
    }

    public void initialize() {
        setColumnProperties();
    }

    private void setColumnProperties(){
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("marketNameProperty"));
        highCol.setCellValueFactory(
                new PropertyValueFactory<>("highProperty"));
        lowCol.setCellValueFactory(
                new PropertyValueFactory<>("lowProperty"));
        volumeCol.setCellValueFactory(
                new PropertyValueFactory<>("volumeProperty"));
        bidCol.setCellValueFactory(
                new PropertyValueFactory<>("bidProperty"));
        askCol.setCellValueFactory(
                new PropertyValueFactory<>("askProperty"));
        openBuyCol.setCellValueFactory(
                new PropertyValueFactory<>("openBuyOrdersProperty"));
        openSellCol.setCellValueFactory(
                new PropertyValueFactory<>("openSellOrdersProperty"));
        createdCol.setCellValueFactory(
                new PropertyValueFactory<>("createdProperty"));
    }

}
