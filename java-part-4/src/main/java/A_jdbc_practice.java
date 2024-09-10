import java.sql.*;
import java.util.Scanner;

public class A_jdbc_practice {
    public int choice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose What You Want");
        System.out.println("[1]JOIN [2]Update Information [3]Delete Information [4]OFF");
        System.out.print(" -> ");

        return sc.nextInt();
    }

    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection connection2() {
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Complete!");

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection checkOverLap() {
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Complete!");
            System.out.println("");

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertDate() {
        String query = "INSERT INTO account (name, age, email, phone) VALUES (?, ?, ?, ?)";

        Scanner sc = new Scanner(System.in);
        System.out.print("Write your name : ");
        String name = sc.nextLine();
        System.out.print("Write your age : ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Write your email : ");
        String email = sc.nextLine();
        System.out.print("Write your phoneNumber : ");
        String phone = sc.nextLine();

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);


            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                String query2 = "SELECT id FROM account WHERE name = ? and phone = ?";
                try (
                        Connection conn2 = connection2();
                        PreparedStatement preparedStatement2 = conn2.prepareStatement(query2);
                ) {
                    preparedStatement2.setString(1, name);
                    preparedStatement2.setString(2, phone);
                    ResultSet resultSet = preparedStatement2.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        System.out.println("WELCOME " + name + "! Check your ID : " + id);
                        System.out.println("");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateData() {
        String query = "UPDATE account SET name = ?, age = ?, email = ?, phone = ? WHERE id = ?";

        Scanner sc = new Scanner(System.in);
        System.out.print("Write your id : ");
        int ID = sc.nextInt();
        sc.nextLine();

        System.out.print("Write your name : ");
        String name = sc.nextLine();
        System.out.print("Write your age : ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Write your email : ");
        String email = sc.nextLine();
        System.out.print("Write your phoneNumber : ");
        String phone = sc.nextLine();


        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(5, ID);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Update Success!");
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData() {
        String query = "DELETE FROM account WHERE id = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("Write your id : ");
        int ID = sc.nextInt();

        try(
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, ID);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Delete Success!");
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectOne() {
        String query = "SELECT id, name, age, email, phone FROM users WHERE email = ? or phone = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("Write your EMAIL or PHONENUMBER : ");
        String something = sc.nextLine();

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(6, something);
            preparedStatement.setString(7, something);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        A_jdbc_practice addjdbc = new A_jdbc_practice();

        while (true) {
            int Choice = addjdbc.choice();
            switch (Choice) {
                case 1:
                    addjdbc.insertDate();
                    break;
                case 2:
                    addjdbc.updateData();
                    break;
                case 3:
                    addjdbc.deleteData();
                    break;
                case 4:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("올바른 숫자를 입력해주세요.");
                    break;
            }
        }
    }
}
