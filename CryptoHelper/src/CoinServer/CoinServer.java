package CoinServer;

import Network.NetworkCoin;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.io.IOException;

public class CoinServer {

    CoinRetriever wallet = null;
    ObjectSpace objectSpace;

    private CoinServer() throws IOException {
        Server server = new Server(1000000, 100000);
        NetworkCoin.register(server);
        server.bind(54565);
        assignListeners(server);

        // Register remote wallet.
        wallet = new CoinRetriever();
        objectSpace = new ObjectSpace();
        objectSpace.register(NetworkCoin.COINRETRIEVER, wallet);


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

            }

            @Override
            public void disconnected(Connection connection) {
                objectSpace.removeConnection(connection);
            }


        });

    }


}
