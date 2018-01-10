package DBServer;

import Shared.Transaction;

import java.sql.SQLException;

public interface IDatabaseHelper {
    boolean isConnected();
    boolean connect();
    boolean logInUser(String username, String password);
    boolean logOutUser(String username, String password);
    boolean logTransaction(Transaction transaction);
    boolean createAccount(String username, String password);
    void deleteAccount(String username, String password) throws SQLException;

}
