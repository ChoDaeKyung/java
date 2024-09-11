import java.sql.SQLException;

public class database_prac_Start {


    public static void main(String[] args) throws SQLException {
        database_prac_Interface database = new database_practice();

        while(true){
            int choice = database.Sellect();
            switch(choice){
                case 1:
                    database.addInfo();
                    break;
                case 2:
                    database.deleteInfo();
                    break;
                case 3:
                    database.givePay();
                    break;
                case 4:
                    database.displayInfo();
                    break;
                case 5:
                    System.out.println("Thanks for Using My System.");
                    return;
                default:
                    System.out.println("Please write the right number.");
                    break;
            }
        }

    }
}
