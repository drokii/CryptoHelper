package Client;

import Network.NetworkDatabase;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

import static Network.NetworkDatabase.*;

public class DatabaseClient {
    private Client client;

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
                CreateAccountRequest createAccount = new CreateAccountRequest ();
                createAccount.username = "Pedro";
                createAccount.password = "Test";
                client.sendTCP(createAccount);
            }

            public void received(Connection connection, Object object) {
                if (object instanceof CreateAccountResponse) {
                    if(((CreateAccountResponse) object).success){
                        System.out.println("AccountCreated");
                    } else{
                        System.out.println(((CreateAccountResponse) object).errorMsg);
                    }

                }
                if (object instanceof LogInResponse) {
                    //todo: react to response
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
}
