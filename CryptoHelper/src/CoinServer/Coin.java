package CoinServer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Coin implements Serializable {

    @SerializedName("MarketName") private String marketName;
    @SerializedName("High") private Double high;
    @SerializedName("Low") private Double low;
    @SerializedName("Volume") private Double volume;
    @SerializedName("Last") private Double last;
    @SerializedName("BaseVolume")  Double baseVolume;
    @SerializedName("TimeStamp") private String timeStamp;
    @SerializedName("Bid") private Double bid;
    @SerializedName("Ask") private Double ask;
    @SerializedName("OpenBuyOrders") private Integer openBuyOrders;
    @SerializedName("OpenSellOrders")private Integer openSellOrders;
    @SerializedName("PrevDay")private Double prevDay;
    @SerializedName("Created")private String created;

    public Coin() {
    }

    public String getMarketName() {
        return marketName;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getLast() {
        return last;
    }

    public Double getBaseVolume() {
        return baseVolume;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public Integer getOpenBuyOrders() {
        return openBuyOrders;
    }

    public Integer getOpenSellOrders() {
        return openSellOrders;
    }

    public Double getPrevDay() {
        return prevDay;
    }

    public String getCreated() {
        return created;
    }



}