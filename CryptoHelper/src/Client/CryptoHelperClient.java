package Client;

import CoinServer.Coin;
import Network.NetworkCoin;
import Shared.IRemoteWallet;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;
import org.omg.CORBA.IRObject;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CryptoHelperClient {

    IRemoteWallet wallet;
    List<Coin> coins = null;
    Client client;

    private CryptoHelperClient() {
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
        new CryptoHelperClient();

    }
}
