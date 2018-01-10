package CoinServer;

import com.google.gson.annotations.SerializedName;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;


public class Coin implements Serializable {

    @SerializedName("MarketName")
    private String marketName;
    @SerializedName("High")
    private Double high;
    @SerializedName("Low")
    private Double low;
    @SerializedName("Volume")
    private Double volume;
    @SerializedName("Last")
    private Double last;
    @SerializedName("BaseVolume")
    Double baseVolume;
    @SerializedName("TimeStamp")
    private String timeStamp;
    @SerializedName("Bid")
    private Double bid;
    @SerializedName("Ask")
    private Double ask;
    @SerializedName("OpenBuyOrders")
    private Integer openBuyOrders;
    @SerializedName("OpenSellOrders")
    private Integer openSellOrders;
    @SerializedName("PrevDay")
    private Double prevDay;
    @SerializedName("Created")
    private String created;


    private SimpleStringProperty marketNameProperty;
    private SimpleDoubleProperty highProperty;
    private SimpleDoubleProperty lowProperty;
    private SimpleDoubleProperty volumeProperty;
    private SimpleDoubleProperty askProperty;
    private SimpleDoubleProperty bidProperty;
    private SimpleDoubleProperty openBuyOrdersProperty;
    private SimpleDoubleProperty openSellOrdersProperty;
    private SimpleStringProperty createdProperty;

    public Coin() {
    }

    // Gson has no direct way to de-marshall into properties for use in JavaFX Ui functions
    // This method sets the fields to properties
    public void setProperties() {
        this.marketNameProperty = new SimpleStringProperty(marketName);
        this.highProperty = new SimpleDoubleProperty(high);
        this.lowProperty = new SimpleDoubleProperty(low);
        this.volumeProperty = new SimpleDoubleProperty(volume);
        this.createdProperty = new SimpleStringProperty(created);
        this.askProperty = new SimpleDoubleProperty(ask);
        this.bidProperty = new SimpleDoubleProperty(bid);
        this.openBuyOrdersProperty = new SimpleDoubleProperty(openBuyOrders);
        this.openSellOrdersProperty = new SimpleDoubleProperty(openSellOrders);
    }

    public String getMarketNameProperty() {
        return marketNameProperty.get();
    }

    public double getHighProperty() {
        return highProperty.get();
    }


    public double getLowProperty() {
        return lowProperty.get();
    }

    public double getVolumeProperty() {
        return volumeProperty.get();
    }


    public double getAskProperty() {
        return askProperty.get();
    }


    public double getBidProperty() {
        return bidProperty.get();
    }


    public double getOpenBuyOrdersProperty() {
        return openBuyOrdersProperty.get();
    }


    public double getOpenSellOrdersProperty() {
        return openSellOrdersProperty.get();
    }


    public String getCreatedProperty() {
        return createdProperty.get();
    }


}