package practice;

import java.sql.*;

public class A_jdbc {

    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conn Success!");

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // sql injection ?
    public void insertDate(String name, int age, String phone) {
        String query = "INSERT INTO users (name, age, phone) VALUES (?, ?, ?)";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, phone);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Insert Success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDate(int id, String name, int age, String phone) {

    }

    public void selectOne(int id) {
        String query = "SELECT id, name, age, phone FROM users WHERE id = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");

                System.out.println(id + " : " + name + " : " + age + " : " + phone);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

        public void updateData(int id, String name, int age, String phone) {
        String query = "UPDATE users SET name = ?, age = ?, phone = ? WHERE id = ?";

        try(
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, phone);
            preparedStatement.setInt(4, id);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Update Success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

        public void deleteData(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try(
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ) {
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Delete Success!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

        public void selectAll() {
            String query = "SELECT id, name, age, phone FROM users";

            try (
                    Connection conn = connection();
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    ) {

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String phone = resultSet.getString("phone");

                    System.out.println(id + " : " + name + " : " + age + " : " + phone);
                    System.out.println("================================");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public static void main(String[] args) {
        A_jdbc jdbc = new A_jdbc();
//        jdbc.insertDate("Alice", 11, "010-1234-5678");
//            jdbc.selectOne(9);
//            jdbc.updateData(9, "Sally", 13, "010-1111-2222");
            jdbc.selectAll();

    }
}
