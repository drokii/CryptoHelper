package Client;

import CoinServer.Coin;
import Shared.IRemoteWallet;

import java.io.IOException;
import java.util.List;

public class ClientWallet implements IRemoteWallet{

    private List<Coin> coins;

    public ClientWallet(List<Coin> coins) {
        this.coins = coins;
    }

    @Override
    public void refreshWallet() throws IOException {

    }

    @Override
    public List<Coin> getCoin() {
        return coins;
    }

}
