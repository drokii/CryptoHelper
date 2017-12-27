package DBServer;

import Network.NetworkDatabase;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class DatabaseServer {
    IDatabaseHelper databaseHelper;

    private DatabaseServer() throws IOException {
        Server server = new Server(1000000, 100000);
        NetworkDatabase.register(server);
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
                if (object instanceof NetworkDatabase.CreateAccountRequest) {
                    //todo: code to create an account
                }
                if (object instanceof NetworkDatabase.LogInRequest) {
                    //todo: code to log in the user
                }
                if (object instanceof NetworkDatabase.RemoveAccountRequest) {
                    //todo:code to remove user account
                }
                if (object instanceof NetworkDatabase.SaveTransactionRequest) {
                    //todo: code to save a transaction
                }
            }

            @Override
            public void disconnected(Connection connection) {

            }


        });

    }
}
