package NewsServer;

import Network.NetworkNews;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.io.IOException;

public class NewsServer {

    private ObjectSpace objectSpace;
    private NewsFeed feed;
    private RSSFeedReader rssReader;

    private NewsServer() throws IOException {
        Server server = new Server(1000000, 100000);
        NetworkNews.register(server);
        server.bind(54567);
        assignListeners(server);

        rssReader = new RSSFeedReader("https://www.coindesk.com/feed/");
        feed = rssReader.readFeed();
        // Register NewsFeed.
        objectSpace = new ObjectSpace();
        objectSpace.register(NetworkNews.NEWSFEED, feed);

        server.start();

    }


    public static void main(String[] args) throws IOException {
        new NewsServer();
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
}
