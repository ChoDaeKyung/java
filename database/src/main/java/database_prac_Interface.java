import java.sql.Connection;

public interface database_prac_Interface {
    int Sellect();
    String getNowDateTime();
    void addInfo();
    Connection connection();
    int jobNum(String job);
    void deleteInfo();
    void givePay();
    void displayInfo();
}
