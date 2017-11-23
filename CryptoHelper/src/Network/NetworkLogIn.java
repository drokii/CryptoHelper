package Network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class NetworkLogIn {

    private static Kryo kryo;

    static public void register (EndPoint endPoint) {
        kryo = endPoint.getKryo();
        // This must be called in order to use ObjectSpaces.
        ObjectSpace.registerClasses(kryo);
        // The interfaces that will be used as remote objects must be registered.

        // The classes of all method parameters and return values
        // for remote objects must also be registered.
        kryo.register(String[].class);
    }



}
