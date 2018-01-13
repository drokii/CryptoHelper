package Client;

import CoinServer.Coin;
import Network.NetworkCoin;
import Shared.ICoinRetriever;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.Observer;

import static Network.NetworkCoin.*;

public class CoinClient{

    private ICoinRetriever coinRetriever;

    public ObservableList<Coin> getCoins() {
        return coins;
    }

    public ObservableList<Coin> coins = null;
    private Client client;

    public CoinClient() {
        client = new Client(1000000,1000000);
        client.start();
        register(client);

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
                coinRetriever = ObjectSpace.getRemoteObject(connection, COINRETRIEVER, ICoinRetriever.class);
                ((RemoteObject) coinRetriever).setNonBlocking(false);
                coins = FXCollections.observableArrayList(coinRetriever.getCoin());
                System.out.println("Coins have been recieved.");

            }

            public void received(Connection connection, Object object) {
                if (object instanceof CoinListUpdate) {
                    coins.setAll(coinRetriever.getCoin());
                    notifyAll();
                }
            }
        }));

    }
    public static void main(String[] args) throws IOException {
        new CoinClient();

    }
    public void addCoinListener(ListChangeListener listener){
        coins.addListener(listener);
    }

}
