package CoinServer;

import Network.NetworkCoin;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class CoinServer {

    RemoteWallet wallet =null;

    private CoinServer() throws IOException {
        Server server = new Server();
        server.bind(54555, 54777);
        server.start();
        NetworkCoin.register(server);
        assignListeners(server);
        wallet = new RemoteWallet();
    }

    public static void main(String[] args) throws IOException {
        new CoinServer();
    }

    private void assignListeners(Server server) {
        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {

                System.out.println("A client is connected: " + connection.getID());
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof NetworkCoin.CoinRequest) {

                    System.out.println("Coins requested by " + connection.getID());

                }
            }
        });
    }


}
