package Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.util.Observer;

import static Network.NetworkDatabase.*;

public class DatabaseClient{

    private Client client;
    private SimpleIntegerProperty sip;
    private int connectionStatus = 0;

    Observer observer;

    public int getConnectionStatus() {
        return connectionStatus;
    }


    public static void main(String[] args) throws IOException {
        new DatabaseClient();

    }

    public DatabaseClient() {

        client = new Client(1000000, 1000000);
        addListenersToClient();
        client.start();
        register(client);

        new Thread("connect") {
            public void run() {
                try {
                    client.connect(3000, "localhost", 54565);
                    while (client.isConnected()) {
                        client.update(1000);
                        if(connectionStatus != 1){
                            connectionStatus = 0;
                        }
                    }
                } catch (IOException ex) {
                    connectionStatus = 2;
                }
            }
        }.start();

    }

    private void addListenersToClient() {
        client.addListener(new Listener.ThreadedListener(new Listener() {
            @Override
            public void connected(Connection connection) {

            }

            public void received(Connection connection, Object object) {
                if (object instanceof CreateAccountResponse) {
                    if (((CreateAccountResponse) object).success) {
                        System.out.println("AccountCreated");

                    } else {
                        System.out.println(((CreateAccountResponse) object).errorMsg);
                    }
                    //todo: incorporate this with an actual decent reaction

                }
                if (object instanceof LogInResponse) {
                    if (((LogInResponse) object).success) {
                        connectionStatus = 1;
                    } else {
                        connectionStatus = -1;
                        System.out.println(((LogInResponse) object).errorMsg);
                    }
                }
                if (object instanceof RemoveAccountResponse) {
                    //todo: react to response
                }
                if (object instanceof SaveTransactionResponse) {
                    //todo: react to response
                }
            }
        }));
    }

    public void logIn(String username, String password) {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.username = username;
        logInRequest.password = password;
        client.sendTCP(logInRequest);
    }

}
