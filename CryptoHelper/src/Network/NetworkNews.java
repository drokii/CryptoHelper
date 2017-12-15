package Network;

import Shared.INewsFeed;
import Shared.NewsPiece;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import java.util.ArrayList;

public class NetworkNews {
    static public final short NEWSFEED = 6;
    private static Kryo kryo;

    static public void register (EndPoint endPoint) {
        kryo = endPoint.getKryo();

        ObjectSpace.registerClasses(kryo);
        //Interfaces must be registered
        kryo.register(INewsFeed.class);
        kryo.register(NewsPiece.class);
        //Object values must be registered
        kryo.register(int.class);
        kryo.register(double.class);
        kryo.register(String.class);
        kryo.register(ArrayList.class);

    }
}
