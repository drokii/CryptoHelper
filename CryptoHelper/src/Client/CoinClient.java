package Client;

import CoinServer.Coin;
import Network.NetworkCoin;
import Shared.ICoinRetriever;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;

import java.io.IOException;
import java.util.List;

public class CoinClient {

    ICoinRetriever coinRetriever;
    private List<Coin> coins = null;
    public List<Coin> getCoins() {
        return coins;
    }

    Client client;

    public CoinClient() {
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
                System.out.println("Client connected to Coin Server");
                coinRetriever = ObjectSpace.getRemoteObject(connection, NetworkCoin.COINRETRIEVER, ICoinRetriever.class);
                ((RemoteObject) coinRetriever).setNonBlocking(false);
                coins = coinRetriever.getCoin();
                System.out.println("Coins have been recieved.");

            }

            public void received(Connection connection, Object object) {

            }
        }));

    }
    public static void main(String[] args) throws IOException {
        new CoinClient();

    }


}
