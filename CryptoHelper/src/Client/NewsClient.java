package Client;

import Network.NetworkNews;
import Shared.INewsFeed;
import Shared.NewsPiece;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;

import java.io.IOException;
import java.util.List;

public class NewsClient {
    INewsFeed newsFeed;

    private List<NewsPiece> newsPieces;

    public List<NewsPiece> getNewsPieces() {
        return newsPieces;
    }
    private Client client;

    public NewsClient() {
        client = new Client(1000000,1000000);
        client.start();
        NetworkNews.register(client);

        new Thread("connect") {
            public void run() {
                try {
                    client.connect(3000, "localhost", 54569);
                    while (client.isConnected()) {
                        client.update(5000);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();

        client.addListener(new Listener.ThreadedListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                newsFeed = ObjectSpace.getRemoteObject(connection, NetworkNews.NEWSFEED, INewsFeed.class);
                ((RemoteObject) newsFeed).setNonBlocking(false);
                newsPieces = newsFeed.getNews();
            }

            public void received(Connection connection, Object object) {

            }
        }));

    }
    public static void main(String[] args) throws IOException {
        new NewsClient();
    }
}
