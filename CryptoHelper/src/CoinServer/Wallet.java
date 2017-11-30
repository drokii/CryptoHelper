package CoinServer;

import Shared.IWallet;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Wallet implements IWallet {

    private void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    @SerializedName("result")
    private List<Coin> coins = null;
    @Override
    public List<Coin> getCoin() {
        return coins;
    }

    public Wallet() throws IOException {
        fillWallet();
    }

    private void fillWallet() throws IOException {
        String json = APICall("https://bittrex.com/api/v1.1/public/getmarketsummaries");

        Gson g = new Gson();
        Type listType = new TypeToken<List<Coin>>() {}.getType();
        this.setCoins(g.fromJson(json, listType));

        if (this.getCoin() != null) {
            System.out.println("API GET Request succesful");
        } else {
            System.out.println("Request failed. Check internet connection or API server status.");
        }
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
        String s = response.toString();
        s = s.substring(s.indexOf('['), s.lastIndexOf(']')+1);

        return s;
    }


    @Override
    public void refreshWallet() throws IOException {
        coins = null;
        String json = APICall("https://bittrex.com/api/v1.1/public/getmarketsummaries");

        Gson g = new Gson();
        this.setCoins(g.fromJson(json, CoinServer.Wallet.class).getCoin());
    }
}

