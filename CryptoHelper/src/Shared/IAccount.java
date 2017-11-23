package Shared;

public interface IAccount {

    String name = null;
    String password = null;
    int portfolioId = -1;
    String email = null;

    void deleteAccount();
    void editAccount();

}
