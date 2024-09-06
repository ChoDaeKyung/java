package himedia.practice;

public interface AccountBook {
    int printAccount();
    String getNowDateTime();
    void addList() throws RuntimeException;
    void howMuch(int money);
    void displayList();
    void deleteAccount();
}
