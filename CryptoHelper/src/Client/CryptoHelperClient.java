package Client;

import CoinServer.Coin;
import Network.NetworkCoin;
import Shared.IRemoteWallet;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.io.IOException;
import java.util.List;

public class CryptoHelperClient {

    IRemoteWallet wallet = null;
    List<Coin> coins = null;

    private CryptoHelperClient() {
        //TODO: GET THIS WORKING
        Client client = new Client();
        client.start();

        NetworkCoin.register(client);
        wallet = ObjectSpace.getRemoteObject(client, NetworkCoin.WALLET, IRemoteWallet.class);
        //coins = wallet.getCoin();
        if( coins != null){
            System.out.println("RMI works");
        }

    }
    public static void main(String[] args) throws IOException {
        new CryptoHelperClient();
    }
}
