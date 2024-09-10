import java.sql.Connection;
import java.sql.SQLException;

public interface database_prac_Interface {
    int Sellect();
    String getNowDateTime();
    void addInfo() throws SQLException;
    Connection connection();
    int jobNum(String job);
    void deleteInfo();
    void givePay();
    void displayInfo();
}
