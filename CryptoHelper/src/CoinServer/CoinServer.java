package CoinServer;

import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinServer {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
        server.bind(54555, 54777);

    }

    private static void assignListeners(Server server) {
//        server.addListener(new Listener() {
//         public void received (Connection connection, Object object) {
//               if (object instanceof SampleRequest) {
//                  SampleRequest request = (SampleRequest)object;
//                  System.out.println(request.text);
//
//                  SampleResponse response = new SampleResponse();
//                  System.out.println(response.text);
//                       connection.sendTCP(response);
//               }
//            }
//         });
    }


}
