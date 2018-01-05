package Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.common.eventbus.Subscribe;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static Network.NetworkDatabase.*;

public class DatabaseClient{

    private Client client;
    // 0 for not initialized, 1 for logged in, 2 for no internet, -1 for bad login
    private int loginStatus = 0;
    public int getLoginStatus() {
        return loginStatus;
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
                    }
                } catch (IOException ex) {
                    loginStatus = 2;
                }
            }
        }.start();

    }

    @Subscribe
    public void handleLogin(int event){
        loginStatus = event;
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
                        loginStatus = 1;
                    } else {
                        loginStatus = -1;
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

    public int logIn(String username, String password) throws InterruptedException, ExecutionException {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.username = username;
        logInRequest.password = password;
        client.sendTCP(logInRequest);

        Future<Integer> waitForResponse = waitForResponse();
        int i = waitForResponse.get();
        return i;

    }



    public Future<Integer> waitForResponse() throws InterruptedException {
        CompletableFuture<Integer> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            while(!completableFuture.isDone()) {
                System.out.println("Calculating...");

                if(loginStatus != 0){
                    completableFuture.complete(loginStatus);
                }else{
                    Thread.sleep(300);

                }

            }

            return null;
        });

        return completableFuture;
    }

}
