package Tests;

import Client.DatabaseClient;
import DBServer.DatabaseServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseClientTest {
    static private  DatabaseClient dbc;
    static private  DatabaseServer dbs;

    @BeforeAll
    static void setUp() throws IOException {
        dbs = new DatabaseServer();
        dbc = new DatabaseClient();
    }
    @Test
    void getResponseStatus() {
        assertEquals(0, dbc.getResponseStatus());
    }

    @Test
    void logIn() throws ExecutionException, InterruptedException {
        assertEquals(1, dbc.logIn("Test", "Test"));
        dbc.logOut("Test", "Test");
    }
    @Test
    void logInWrongDetails() throws ExecutionException, InterruptedException {
        assertEquals(-1, dbc.logIn("Test23", "Tes24t"));

    }
    @Test
    void waitForResponse() throws InterruptedException, ExecutionException {
        Future<Integer> f = dbc.waitForResponse();
        dbc.setResponseStatus(-1);
        int i2 = f.get();
        assertEquals(-1,  i2);

    }

    @Test
    void createAccountFail() throws ExecutionException, InterruptedException {
        assertEquals(1, dbc.createAccount("Test", "Test"));
    }

    @Test
    void createAccount() throws ExecutionException, InterruptedException {
        assertEquals(true, dbc.createAccount("Tessadt", "Tefsast"));
    }

    @AfterEach
    void afterEach() throws ExecutionException, InterruptedException {
        //dbc.logOut("Test", "Test");
    }

}