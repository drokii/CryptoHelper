package Tests;

import DBServer.DatabaseHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseHelperTest {

    DatabaseHelper dbh;

    @BeforeEach
    void setUp() {
        this.dbh = new DatabaseHelper();
    }

    @Test
    void isConnected() {
        dbh.logInUser("A", "B");
        assertTrue(dbh.isConnected());
    }

    @Test
    void connect() {
        assertTrue(dbh.connect());
    }

    @Test
    void connectFail() {
        dbh.setConnectionString("");
        assertFalse(dbh.connect());
    }

    @Test
    void logInUser() {
        assertTrue(dbh.logInUser("A", "B"));
    }

    @Test
    void logInNotExisting() {
        assertFalse(dbh.logInUser("adasd", "asdas"));
    }

    @Test
    void logOutUser() {
        dbh.logInUser("A", "B");
        assertTrue(dbh.logOutUser("A", "B"));
    }

    @Test
    void logTransaction() {
    }

    @Test
    void createAccount() throws SQLException {
        assertTrue(dbh.createAccount("adasd","sdadad"));
        dbh.deleteAccount("adasd","sdadad");
    }

    @Test
    void createAccountExistingFail() {
        assertFalse(dbh.createAccount("Oingo", "Boingo"));

         }

    @Test
    void deleteAccount() throws SQLException {
        dbh.createAccount("adasd","sdadad");
        dbh.deleteAccount("adasd","sdadad");
        assertFalse(dbh.logInUser("adasd","sdadad"));

    }

    @AfterEach
    void conclude() {
        try {
            dbh.logOutUser("A", "B");
            dbh.logOutUser("Oingo", "Boingo");
            dbh.logOutUser("Test", "Test");
        } catch (Exception e) {
            System.out.println("Testing complete");
        }

    }

}