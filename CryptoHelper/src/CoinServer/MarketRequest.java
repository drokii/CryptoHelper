package CoinServer;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class MarketRequest {

        private Boolean success;
        private String message;
        @SerializedName("result")
        private List<Coin> coin = null;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Coin> getCoin() {
            return coin;
        }

        public void setCoin(List<Coin> coin) {
            this.coin = coin;
        }

    }

