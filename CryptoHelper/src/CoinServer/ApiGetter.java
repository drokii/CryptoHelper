package CoinServer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiGetter {

    public List<Coin> getCoins() {

        List<Coin> coinList = null;

        try {
            String json = APICall("https://bittrex.com/api/v1.1/public/getmarketsummaries");

            Gson g = new Gson();
            MarketRequest request = g.fromJson(json, MarketRequest.class);

            if(request.getCoin() != null){
                coinList = request.getCoin();
                for (Coin c : request.getCoin()) {
                    System.out.println(c.getBid() + " is the price of " + c.getMarketName());
                }
            }
            else{
                System.out.println("Request failed. Request status: " + request.getSuccess());
            }




        } catch (IOException ex) {

        }
        return coinList;
    }

    private String APICall(String link) throws IOException {

        URL url = new URL(link);
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

        return response.toString();
    }
}
