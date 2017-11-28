package CoinServer;

import Network.NetworkCoin;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.io.IOException;

public class CoinServer {

    Wallet wallet = null;
    ObjectSpace objectSpace;

    private CoinServer() throws IOException {
        Server server = new Server(1000000, 100000);
        NetworkCoin.register(server);
        server.bind(54565);
        assignListeners(server);

        // Register remote wallet.
        wallet = new Wallet();
        objectSpace = new ObjectSpace();
        objectSpace.register(NetworkCoin.WALLET, wallet);


        server.start();

    }


    public static void main(String[] args) throws IOException {
        new CoinServer();
    }

    private void assignListeners(Server server) {
        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                objectSpace.addConnection(connection);
                System.out.println("A client is connected from: " + connection.getRemoteAddressTCP());
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof NetworkCoin.CoinRequest) {

                    System.out.println("Coins requested by " + connection.getRemoteAddressTCP());

                }
            }

            @Override
            public void disconnected(Connection connection) {
                objectSpace.removeConnection(connection);
            }
        });
    }


}
