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
    @SerializedName("DisplayMarketName")private Object displayMarketName;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(Double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Integer getOpenBuyOrders() {
        return openBuyOrders;
    }

    public void setOpenBuyOrders(Integer openBuyOrders) {
        this.openBuyOrders = openBuyOrders;
    }

    public Integer getOpenSellOrders() {
        return openSellOrders;
    }

    public void setOpenSellOrders(Integer openSellOrders) {
        this.openSellOrders = openSellOrders;
    }

    public Double getPrevDay() {
        return prevDay;
    }

    public void setPrevDay(Double prevDay) {
        this.prevDay = prevDay;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getDisplayMarketName() {
        return displayMarketName;
    }

    public void setDisplayMarketName(Object displayMarketName) {
        this.displayMarketName = displayMarketName;
    }

}