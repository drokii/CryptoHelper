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

        URL url = new URL("https://bittrex.com/api/v1.1/public/getmarketsummaries");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String json = response.toString();

        Gson g = new Gson();

        MarketRequest request = g.fromJson(json, MarketRequest.class);

        for (Coin c : request.getCoin()
                ) {
            System.out.println(c.getBid() + " is the price of " + c.getMarketName());

        }


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
