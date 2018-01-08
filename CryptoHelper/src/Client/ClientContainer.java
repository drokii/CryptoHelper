package Client;

import CoinServer.Coin;
import Shared.NewsPiece;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientContainer {

    private CoinClient coinClient;
    private NewsClient newsClient;

    public ObservableList<Coin> getCoins() throws InterruptedException, ExecutionException {

        Future<ObservableList<Coin>> waitForResponse = waitForResponse();
        ObservableList<Coin> coins = waitForResponse.get();
        return coins;
    }

    public ObservableList<NewsPiece> getNews() {
        return (ObservableList<NewsPiece>) newsClient.getNewsPieces();
    }

    public ClientContainer(CoinClient coinClient, NewsClient newsClient) {
        this.coinClient = coinClient;
        this.newsClient = newsClient;

    }

    public Future<ObservableList<Coin>> waitForResponse() throws InterruptedException {
        CompletableFuture<ObservableList<Coin>> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            while (!completableFuture.isDone()) {
                System.out.println("Awaiting coins..");

                if (coinClient.getCoins() != null) {
                    completableFuture.complete(FXCollections.observableArrayList(coinClient.getCoins()));
                } else {
                    Thread.sleep(300);

                }

            }
            return null;
        });
        return completableFuture;
    }
}
