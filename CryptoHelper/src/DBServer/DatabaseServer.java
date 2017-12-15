package DBServer;

import Network.NetworkLogIn;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class DatabaseServer {
    IDatabaseHelper databaseHelper;

    private DatabaseServer() throws IOException {
        Server server = new Server(1000000, 100000);
        NetworkLogIn.register(server);
        server.bind(54565);
        assignListeners(server);
        databaseHelper = new DatabaseHelper();
        server.start();

    }


    public static void main(String[] args) throws IOException {
        new DatabaseServer();
    }

    private void assignListeners(Server server) {
        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                System.out.println("A client is connected from: " + connection.getRemoteAddressTCP());
            }

            @Override
            public void received(Connection connection, Object object) {

            }

            @Override
            public void disconnected(Connection connection) {

            }


        });

    }
}
