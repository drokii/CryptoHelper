package DBServer;

import Shared.Transaction;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;

public class DatabaseHelper implements IDatabaseHelper {

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    private String connectionString = "jdbc:mysql://localhost:3306/cryptohelper?user=root";
    private Connection conn;
    private boolean connected;

    @Override
    public boolean isConnected() {
        return connected;
    }

    public DatabaseHelper() {
        connect();
    }

    @Override
    public boolean connect() {
        try {
            conn = DriverManager.getConnection(connectionString);
            if (conn != null) {
                connected = true;
                System.out.println("Connected to the database");
                return true;
            } else {
                connected = false;
                return false;
            }
        } catch (CommunicationsException ex) {
            System.out.println("Connection to the database failed");
            ex.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean logInUser(String username, String password) {
        Statement stmt = null;
        String checkQuery = "select username, password from account where username ='" + username + "' and password='" + password + "' and loggedin= 0;";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(checkQuery);
            if (rs.isBeforeFirst()) {
                stmt.executeUpdate("update account set loggedin = 1 where username='" + username + "';");
                System.out.println("Login Succesful.");
                stmt.close();
                return true;

            } else {
                stmt.close();
                System.out.println("Credentials do not exist.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed log in. Database connection failed.");
            return false;
        }
    }

    @Override
    public boolean logOutUser(String username, String password) {
        Statement stmt = null;
        String logOutQuery = "update account set loggedin = 0 where username='" + username + "';";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(logOutQuery);
            System.out.println("Logged off.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean logTransaction(Transaction transaction) {
        return false;
    }

    @Override
    public boolean createAccount(String username, String password) {
        Statement stmt = null;
        String insertQuery = "insert into account(username,password,loggedin) values('" + username + "','" + password + "', 0);";
        try {
            if (!logInUser(username, password)) {
                stmt = conn.createStatement();
                stmt.executeUpdate(insertQuery);
                return true;
            } else {
                System.out.println("Failed account creation. Username already exists.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed account creation. Database connection failed.");
            return false;
        }
    }

    @Override
    public void deleteAccount(String username, String password) throws SQLException {
        Statement stmt = null;
        String deleteQuery = "delete from account where username ='" + username + "' and password ='" + password + "';";
        stmt = conn.createStatement();
        stmt.executeUpdate(deleteQuery);

    }


}
