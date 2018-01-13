package CoinServer;

import Network.NetworkCoin;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CoinServer {

    CoinRetriever coinRetriever = null;
    ObjectSpace objectSpace;
    Server server;

    private CoinServer() throws IOException {
        server = new Server(100000, 100000);
        NetworkCoin.register(server);
        server.bind(54565);
        assignListeners(server);

        // Register remote coinRetriever.
        coinRetriever = new CoinRetriever();
        objectSpace = new ObjectSpace();
        objectSpace.register(NetworkCoin.COINRETRIEVER, coinRetriever);

        server.start();
        sendCoinUpdates(60*10^4).start();

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

    private javax.swing.Timer sendCoinUpdates(int delay){
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    coinRetriever.refreshWallet();
                    server.sendToAllTCP(new NetworkCoin.CoinListUpdate());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
       return new javax.swing.Timer(delay, taskPerformer);

    }

}
