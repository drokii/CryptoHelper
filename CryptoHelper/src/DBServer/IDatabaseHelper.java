package DBServer;

import Shared.Transaction;

public interface IDatabaseHelper {
    boolean isConnected();
    boolean connect();
    boolean logInUser(String username, String password);
    boolean logOutUser(String username, String password);
    boolean logTransaction(Transaction transaction);
    boolean createAccount(String username, String password);
    boolean deleteAccount(String username, String password);

}
