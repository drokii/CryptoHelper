package Client;

import Network.NetworkDatabase;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class DatabaseClient {
    private Client client;

    public DatabaseClient() {
        client = new Client(1000000, 1000000);
        client.start();
        NetworkDatabase.register(client);

        new Thread("connect") {
            public void run() {
                try {
                    client.connect(3000, "localhost", 54565);
                    while (client.isConnected()) {
                        client.update(5000);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
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
                if (object instanceof NetworkDatabase.CreateAccountResponse) {
                    //todo: react to response
                }
                if (object instanceof NetworkDatabase.LogInResponse) {
                    //todo: react to response
                }
                if (object instanceof NetworkDatabase.RemoveAccountResponse) {
                    //todo: react to response
                }
                if (object instanceof NetworkDatabase.SaveTransactionResponse) {
                    //todo: react to response
                }
            }
        }));
    }
}
