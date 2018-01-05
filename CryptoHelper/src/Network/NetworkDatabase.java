package Network;

import Shared.Transaction;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class NetworkDatabase {

    private static Kryo kryo;
    static public final short DATABASE = 4;
    static public void register(EndPoint endPoint) {
        kryo = endPoint.getKryo();
        // This must be called in order to use ObjectSpaces.
        ObjectSpace.registerClasses(kryo);
        // The interfaces that will be used as remote objects must be registered.

        // The classes of all method parameters and return values
        // for remote objects must also be registered.
        kryo.register(String[].class);
        kryo.register(String.class);
        kryo.register(boolean.class);
        kryo.register(CreateAccountRequest.class);
        kryo.register(CreateAccountResponse.class);
        kryo.register(LogInRequest.class);
        kryo.register(LogInResponse.class);

    }

    public static class CreateAccountRequest {
        public String username;
        public String password;
    }

    public static class CreateAccountResponse {
        public String errorMsg;
        public boolean success;
    }

    public static class LogInRequest {
        public String username;
        public String password;
    }

    public static class LogInResponse {
        public String errorMsg;
        public boolean success;
    }

    public static class LogOutNotice{
        public String username;
    }

    public static class SaveTransactionRequest {
        public Transaction transaction;
        public String username;

    }

    public static class SaveTransactionResponse{
        public String errorMsg;
        public boolean success;
    }

    public static class RemoveAccountRequest{
        public String username;
        public String password;
    }
    public static class RemoveAccountResponse {
        public boolean success;
    }


}
