package Contents;

import java.sql.*;
import java.util.Scanner;

public class Contentslmpl implements Contents {

    private boolean status = false;
    private String nowID = null;


    @Override
    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/contents";
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

    @Override
    public int firstMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("[1]Membership [2]Login [3]AddContents [4]ReplaceContents [5]DeleteContents");
        System.out.println("[6]DisplayContent [7]Logout [8]QuitMembership [9]QUIT");
        System.out.print("Choose one of the following options : ");

        return sc.nextInt();
    }


    @Override
    public void membership() {
        String memInfo = "INSERT INTO INFO (ENAME, EMAIL, PHONE, ID, PW) VALUES (?, ?, ?, ?, ?)";
        String queryCheckEmail = "SELECT EMAIL FROM INFO WHERE EMAIL = ?";
        String queryCheckPhone = "SELECT PHONE FROM INFO WHERE PHONE = ?";
        String queryCheckID = "SELECT ID FROM INFO WHERE ID = ?";

        Scanner sc = new Scanner(System.in);
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Phone : ");
        String phone = sc.nextLine();

        try (
                Connection conn = connection();
        ) {
            boolean isPhoneExist = false;
            boolean isEmailExist = false;

            try (PreparedStatement checkPhone = conn.prepareStatement(queryCheckPhone)) {
                checkPhone.setString(1, phone);
                ResultSet rs = checkPhone.executeQuery();
                if (rs.next()) {
                    isPhoneExist = true;
                }
            }
            try (PreparedStatement checkEmail = conn.prepareStatement(queryCheckEmail)) {
                checkEmail.setString(1, email);
                ResultSet rs = checkEmail.executeQuery();
                if (rs.next()) {
                    isEmailExist = true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (isPhoneExist) {
                System.out.println("Your PhoneNumber is already exist!");
            } else if (isEmailExist) {
                System.out.println("Your Email is already exist!");
            } else {
                System.out.print("ID : ");
                String id = sc.nextLine();
                System.out.print("PW : ");
                String pw = sc.nextLine();

                try (
                        Connection conn2 = connection();
                ) {
                    boolean isIDExist = false;

                    try (PreparedStatement checkID = conn2.prepareStatement(queryCheckID)) {
                        checkID.setString(1, id);
                        ResultSet rs = checkID.executeQuery();
                        if (rs.next()) {
                            isIDExist = true;
                        }

                        if (isIDExist) {
                            System.out.println("This ID id already exist");
                        } else {
                            try (PreparedStatement addInfo = conn.prepareStatement(memInfo)) {
                                addInfo.setString(1, name);
                                addInfo.setString(2, email);
                                addInfo.setString(3, phone);
                                addInfo.setString(4, id);
                                addInfo.setString(5, pw);

                                int result = addInfo.executeUpdate();
                                if (result > 0) {
                                    System.out.println("WELCOME! '" + name + "'!");
                                    System.out.println("");
                                }
                            }
                        }

                    }

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void login() {
        if(status==true) {
            System.out.println("Already logged in");
            return;
        }

        String conT = "SELECT ID, PW FROM INFO WHERE ID =? and PW = ?";
        String queryCheckID = "SELECT ID FROM INFO WHERE ID = ?";
        String queryCheckPW = "SELECT ID, PW FROM INFO WHERE ID = ? and PW = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("ID : ");
        String ID = sc.nextLine();
        System.out.print("PW : ");
        String PW = sc.nextLine();

        try (
                Connection conn = connection();
        ) {
            try (PreparedStatement checkID = conn.prepareStatement(queryCheckID)) {
                checkID.setString(1, ID);
                ResultSet rs = checkID.executeQuery();
                if (!rs.next()) {
                    System.out.println("There's No ID.");
                    return;
                }

                boolean isPWRight = false;

                try (PreparedStatement checkPW = conn.prepareStatement(queryCheckPW)) {
                    checkPW.setString(1, ID);
                    checkPW.setString(2, PW);
                    ResultSet ispw = checkPW.executeQuery();
                    if (ispw.next()) {
                        isPWRight = true;
                    }
                }
                if (!isPWRight) {
                    System.out.println("Wrong Password!");
                } else {
                    try (
                            PreparedStatement login = conn.prepareStatement(conT);
                    ) {
                        login.setString(1, ID);
                        login.setString(2, PW);

                        this.nowID = ID;
                        status = true;

                        ResultSet resultSet = login.executeQuery();
                        if (resultSet.next()) {
                            System.out.println("Login Successful!");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addContents() {
        String query = "INSERT INTO CONTENTS (ID, CONTENTS) VALUES (?, ?)";

        if (!status) {
            System.out.println("Please Login First.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Write : ");
        String content = sc.nextLine();

        try (
                Connection conn = connection();
                PreparedStatement addCon = conn.prepareStatement(query);
        ) {
            addCon.setString(1, nowID);
            addCon.setString(2, content);

            int result = addCon.executeUpdate();
            if (result > 0) {
                System.out.println("Contents Added!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
