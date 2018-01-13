package Tests;

import CoinServer.Coin;
import CoinServer.CoinRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinRetrieverTest {

    private CoinRetriever coinRetriever;

    @BeforeEach
    void setUp() throws IOException {
        coinRetriever = new CoinRetriever();
    }

    @Test
    void getCoin() {
        assertNotNull(coinRetriever.getCoin());
    }

    @Test
    void refreshWallet() throws IOException {
        List<Coin> list = coinRetriever.getCoin();
        coinRetriever.refreshWallet();
        List<Coin> list2 = coinRetriever.getCoin();
        assertNotEquals(list2, list);
    }

    @Test
    void fillWallet() {
        assertNotNull(coinRetriever.getCoin());
    }
    @Test
    void APICall(){
        
    }

}