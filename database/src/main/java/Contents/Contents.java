package Contents;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Contents {
    String getNowDateTime();
    Connection connection();
    int firstMenu();
    void membership();
    void login();
    void addContents();
    void replaceContents();
    void deleteContents();
    void displayContents();
    void logout();
    void quitMembership();
}
