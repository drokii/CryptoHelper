package Tests;

import Client.NewsClient;
import NewsServer.NewsServer;
import Shared.NewsPiece;
import javafx.concurrent.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class NewsFeedTest {

    @Test
    void getNews() throws IOException, InterruptedException, ExecutionException {
        NewsServer newsServer = new NewsServer();
        NewsClient newsClient = new NewsClient();
        CompletableFuture<List<NewsPiece>> cv = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {

            while (!cv.isDone()) {
                System.out.println("Calculating...");

                if (newsClient.getNewsPieces() != null) {
                    cv.complete(newsClient.getNewsPieces());

                } else {
                    Thread.sleep(300);
                }

            }

            return null;
        });


        assertNotNull(cv.get());
    }

    @Test
    void refreshNewsList() {

    }

}