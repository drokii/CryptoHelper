package Client;

import CoinServer.Coin;
import Network.NetworkCoin;
import Shared.IRemoteWallet;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;

import java.io.IOException;
import java.util.List;

public class CoinGetterClient {

    IRemoteWallet wallet;
    private List<Coin> coins = null;

    public List<Coin> getCoins() {
        return coins;
    }

    Client client;

    private CoinGetterClient() {
        client = new Client(1000000,1000000);
        client.start();
        NetworkCoin.register(client);

        new Thread("connect") {
            public void run () {
                try {
                    client.connect(3000, "localhost", 54565);
                    while(client.isConnected()){
                        client.update(5000);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();

        client.addListener(new Listener.ThreadedListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                wallet = ObjectSpace.getRemoteObject(connection, NetworkCoin.WALLET, IRemoteWallet.class);
                ((RemoteObject) wallet).setNonBlocking(false);
                coins = wallet.getCoin();
            }

            public void received(Connection connection, Object object) {

            }
        }));

    }
    public static void main(String[] args) throws IOException {
        new CoinGetterClient();

    }
}