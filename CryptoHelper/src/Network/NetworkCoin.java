package Network;

import CoinServer.Coin;
import Shared.IRemoteWallet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.util.ArrayList;

public class NetworkCoin {
    static public final short WALLET = 5;
    private static Kryo kryo;

    static public void register (EndPoint endPoint) {
        kryo = endPoint.getKryo();

        ObjectSpace.registerClasses(kryo);
        //Interfaces must be registered
        kryo.register(IRemoteWallet.class);
        //Object values must be registered
        kryo.register(int.class);
        kryo.register(double.class);
        kryo.register(String.class);
        kryo.register(ArrayList.class);
        kryo.register(Coin.class);

        //Requests
        kryo.register(CoinRequest.class);
        kryo.register(CoinRefreshRequest.class);

    }

    static public class CoinRequest{

        public CoinRequest() {
        }
    }
    static public class CoinRefreshRequest{

        public CoinRefreshRequest() {
        }
    }
}
