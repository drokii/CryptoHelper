package DBServer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import static Network.NetworkDatabase.*;

public class DatabaseServer {
    IDatabaseHelper databaseHelper;

    private DatabaseServer() throws IOException {
        Server server = new Server(1000000, 100000);
        register(server);
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
                if (object instanceof CreateAccountRequest) {
                    System.out.println("Recieved Packet");
                    String name = ((CreateAccountRequest) object).username;
                    String password = ((CreateAccountRequest) object).password;

                    CreateAccountResponse createAccount = new CreateAccountResponse();
                    createAccount.success = databaseHelper.createAccount(name, password);

                    if (!databaseHelper.isConnected()) {
                        createAccount.errorMsg = "Server connection to the database failed.";
                    }
                    if (!createAccount.success) {
                        createAccount.errorMsg = "Account creation failed. Credentials already exist.";
                    }

                    server.sendToTCP(connection.getID(), createAccount);
                }
                if (object instanceof LogInRequest) {
                    //todo: code to log in the user
                }
                if (object instanceof RemoveAccountRequest) {
                    //todo:code to remove user account
                }
                if (object instanceof SaveTransactionRequest) {
                    //todo: code to save a transaction
                }
            }

            @Override
            public void disconnected(Connection connection) {

            }


        });

    }


}
