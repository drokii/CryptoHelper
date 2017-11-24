package Network;

import CoinServer.Coin;
import Shared.ICoin;
import Shared.IRemoteWallet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class NetworkCoin {
    static public final short WALLET = 5;
    private static Kryo kryo;

    static public void register (EndPoint endPoint) {
        kryo = endPoint.getKryo();

        ObjectSpace.registerClasses(kryo);
        //Interfaces must be registered
        kryo.register(ICoin.class);
        kryo.register(IRemoteWallet.class);
        //Object values must be registered
        kryo.register(int.class);
        kryo.register(double.class);
        kryo.register(String.class);

        //Requests
        kryo.register(CoinRequest.class);
        kryo.register(CoinRefreshRequest.class);

    }

    static public class CoinRequest{

    }
    static public class CoinRefreshRequest{

    }
}
