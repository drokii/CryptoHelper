package Shared;

import CoinServer.Coin;

import java.io.IOException;
import java.util.List;

public interface IWallet {

    void refreshWallet() throws IOException;
    List<Coin> getCoin();

}
