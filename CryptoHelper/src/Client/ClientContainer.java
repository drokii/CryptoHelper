package Client;

import CoinServer.Coin;
import Shared.NewsPiece;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientContainer{

    private CoinClient coinClient;
    private NewsClient newsClient;
    private ObservableList<Coin> coins;

    public ObservableList<Coin> getCoins() throws InterruptedException, ExecutionException {
        return coins;
    }
    public void addListenerToCoinList(ListChangeListener listener){
        coins.addListener(listener);
    }

    public ObservableList<NewsPiece> getNews() {
        return FXCollections.observableArrayList(newsClient.getNewsPieces());
    }

    public ClientContainer(CoinClient coinClient, NewsClient newsClient) throws ExecutionException, InterruptedException {
        this.coinClient = coinClient;
        this.newsClient = newsClient;

        Future<ObservableList<Coin>> waitForResponse = waitForCoinList();
        coins = waitForResponse.get();


            coinClient.addCoinListener(c -> {
                synchronized (this) {
                    coins = coinClient.getCoins();
                    notifyAll();
                }
            });



    }

    public Future<ObservableList<Coin>> waitForCoinList() throws InterruptedException {
        CompletableFuture<ObservableList<Coin>> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            while (!completableFuture.isDone()) {
                System.out.println("Awaiting coins..");

                if (coinClient.coins != null) {
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
