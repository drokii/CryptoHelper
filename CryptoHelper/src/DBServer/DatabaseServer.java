package DBServer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import static Network.NetworkDatabase.*;

public class DatabaseServer {
    IDatabaseHelper databaseHelper;

    public DatabaseServer() throws IOException {
        Server server = new Server(1000000, 100000);
        register(server);
        server.bind(54568);
        assignListeners(server);
        databaseHelper = new DatabaseHelper();
        if (!databaseHelper.connect()) {
            System.out.println("Database offline. Exiting.");
            System.exit(0);
        }
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
                    System.out.println("Recieved create account request");
                    String name = ((CreateAccountRequest) object).username;
                    String password = ((CreateAccountRequest) object).password;

                    CreateAccountResponse response = new CreateAccountResponse();
                    response.success = databaseHelper.createAccount(name, password);

                    if (!databaseHelper.isConnected()) {
                        response.errorMsg = "Server connection to the database failed.";
                    }
                    if (!response.success) {
                        response.errorMsg = "Account creation failed. Credentials already exist.";
                    }

                    server.sendToTCP(connection.getID(), response);
                }
                if (object instanceof LogInRequest) {
                    System.out.println("Recieved log in packet");
                    String name = ((LogInRequest) object).username;
                    String password = ((LogInRequest) object).password;

                    LogInResponse response = new LogInResponse();
                    response.success = databaseHelper.logInUser(name, password);

                    if (!databaseHelper.isConnected()) {
                        response.errorMsg = "Server connection to the database failed.";
                    }
                    if (!response.success) {
                        response.errorMsg = "Password is incorrect or Session in use";
                    }

                    server.sendToTCP(connection.getID(), response);

                }
                if (object instanceof LogOutNotice) {
                    System.out.println("Recieved log out packet");
                    String name = ((LogOutNotice) object).username;
                    String password = ((LogOutNotice) object).password;
                    databaseHelper.logOutUser(name,password);
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
