package Contents;

import java.sql.Connection;

public interface Contents {
    Connection connection();
    int firstMenu();
    void membership();
    void login();
    void addContents();
}
