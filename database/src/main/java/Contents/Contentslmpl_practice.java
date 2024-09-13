package Contents;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Contentslmpl_practice implements Contents {

    private boolean status = false;
    private String nowID = null;
    private String nowName = null;


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
    public String getNowDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / hh:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public int firstMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        if(status){
            System.out.println("'" + nowName + "'" + "님 현재 로그인 상태입니다!");
        }else if(!status){
            System.out.println("<< 로그아웃 상태, 로그인 해주세요! >>");
        }
        System.out.println("[1]회원가입 [2]로그인 [3]댓글 추가 [4]댓글 수정 [5]댓글 삭제");
        System.out.println("[6]전체글 보기 [7]로그아웃 [8]회원탈퇴 [9]종료");
        System.out.print("-> 옵션을 선택해주세요 : ");

        return sc.nextInt();
    }


    @Override
    public void membership() {
        String memInfo = "INSERT INTO INFO (ENAME, EMAIL, PHONE, ID, PW) VALUES (?, ?, ?, ?, ?)";
        String queryCheckEmail = "SELECT EMAIL FROM INFO WHERE EMAIL = ?";
        String queryCheckPhone = "SELECT PHONE FROM INFO WHERE PHONE = ?";
        String queryCheckID = "SELECT ID FROM INFO WHERE ID = ?";

        Scanner sc = new Scanner(System.in);
        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.print("이메일 : ");
        String email = sc.nextLine();
        System.out.print("전화번호 : ");
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
                System.out.println("이미 존재하는 전화번호입니다.");
            } else if (isEmailExist) {
                System.out.println("이미 존재하는 이메일입니다.");
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
                            System.out.println("이미 존재하는 ID입니다.");
                        } else {
                            try (PreparedStatement addInfo = conn.prepareStatement(memInfo)) {
                                addInfo.setString(1, name);
                                addInfo.setString(2, email);
                                addInfo.setString(3, phone);
                                addInfo.setString(4, id);
                                addInfo.setString(5, pw);

                                int result = addInfo.executeUpdate();
                                if (result > 0) {
                                    System.out.println("환영합니다 '" + name + "'님!");
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
        if (status == true) {
            System.out.println("이미 로그인상태입니다.");
            return;
        }

        String loginName = "SELECT ENAME FROM INFO WHERE ID =? and PW = ?";
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
                    System.out.println("존재하지 않는 ID입니다.");
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
                    System.out.println("비밀번호가 틀렸습니다.");
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
                            System.out.println("로그인 성공!");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    try(
                            PreparedStatement nowNameis = conn.prepareStatement(loginName)
                    ){
                        nowNameis.setString(1, ID);
                        nowNameis.setString(2, PW);

                        ResultSet resultSet = nowNameis.executeQuery();
                        if (resultSet.next()) {
                            String nowname = resultSet.getString("ENAME");

                            this.nowName = nowname;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addContents() {
        String resetContNo = "SET @COUNT = 0";
        String reCountNo = "UPDATE CONTENTS SET CONTNO=@COUNT:=@COUNT+1 WHERE ID = '" + nowID + "'";
        String query = "INSERT INTO CONTENTS (ID, CONTENTS, NOWDATE) VALUES (?, ?, ?)";
        getNowDateTime();
        String date = getNowDateTime();

        if (!status) {
            System.out.println("로그인 먼저 해주세요.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("글쓰기 : ");
        String content = sc.nextLine();

        try (
                Connection conn = connection();
                PreparedStatement addCon = conn.prepareStatement(query);
        ) {
            addCon.setString(1, nowID);
            addCon.setString(2, content);
            addCon.setString(3, date);

            int result = addCon.executeUpdate();
            if (result > 0) {
                try (Statement resetNo = conn.createStatement()) {
                    resetNo.execute(resetContNo);
                    resetNo.executeUpdate(reCountNo);

                    System.out.println("댓글이 추가되었습니다!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public void replaceContents() {
        String displayCon = "SELECT CONTNO, ID, CONTENTS FROM CONTENTS WHERE ID = ?";
        String updateCon = "UPDATE CONTENTS SET ID = ?, CONTENTS = ? WHERE CONTNO = ?";


        if (!status) {
            System.out.println("로그인 먼저 해주세요.");
            return;
        }

        try (
                Connection conn = connection();
        ) {
            try (PreparedStatement displaycontents = conn.prepareStatement(displayCon)) {
                displaycontents.setString(1, nowID);

                ResultSet resultSet = displaycontents.executeQuery();
                while (resultSet.next()) {
                    int contno = resultSet.getInt("CONTNO");
                    String id = resultSet.getString("ID");
                    String contents = resultSet.getString("CONTENTS");

                    System.out.println("");
                    System.out.println("[" + contno + "] " + id + " : " + contents);

                }

                Scanner sc = new Scanner(System.in);
                System.out.println("");
                System.out.print("수정하고 싶은 댓글의 번호를 입력해주세요 : ");
                int chooseNum = sc.nextInt();
                sc.nextLine();

                System.out.print("수정 : ");
                String newContents = sc.nextLine();
                try (PreparedStatement updatecontents = conn.prepareStatement(updateCon)) {
                    updatecontents.setString(1, nowID);
                    updatecontents.setString(2, newContents);
                    updatecontents.setInt(3, chooseNum);

                    int result = updatecontents.executeUpdate();
                    if (result > 0) {
                        System.out.println("수정이 완료되었습니다!");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteContents() {
        String displayCon = "SELECT CONTNO, ID, CONTENTS FROM CONTENTS WHERE ID = ?";
        String deleteCont = "DELETE FROM CONTENTS WHERE CONTNO = ?";
        String resetContNo = "SET @COUNT = 0";
        String reCountNo = "UPDATE CONTENTS SET CONTNO=@COUNT:=@COUNT+1 WHERE ID = " + nowID;

        if (!status) {
            System.out.println("로그인 먼저 해주세요.");
            return;
        }

        try (
                Connection conn = connection();
        ) {
            try (PreparedStatement displaycontents = conn.prepareStatement(displayCon)) {
                displaycontents.setString(1, nowID);

                ResultSet resultSet = displaycontents.executeQuery();
                while (resultSet.next()) {
                    int contno = resultSet.getInt("CONTNO");
                    String id = resultSet.getString("ID");
                    String contents = resultSet.getString("CONTENTS");

                    System.out.println("[" + contno + "] " + id + " : " + contents);

                }

                Scanner sc = new Scanner(System.in);
                System.out.print("삭제하고 싶은 댓글의 번호를 입력해주세요 : ");
                int chooseNum = sc.nextInt();
                sc.nextLine();

                try (PreparedStatement deletecontents = conn.prepareStatement(deleteCont)) {
                    deletecontents.setInt(1, chooseNum);

                    int result = deletecontents.executeUpdate();
                    if (result > 0) {
                        try (Statement resetNo = conn.createStatement()) {
                            resetNo.execute(resetContNo);
                            resetNo.executeUpdate(reCountNo);

                            System.out.println("삭제 완료!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayContents() {
        String displayAll = "SELECT ID, CONTENTS, NOWDATE FROM CONTENTS";

        try (
                Connection displayall = connection();
                PreparedStatement displayAllCont = displayall.prepareStatement(displayAll);
        ) {
            ResultSet resultSet = displayAllCont.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String contents = resultSet.getString("CONTENTS");
                String date = resultSet.getString("NOWDATE");

                System.out.println("");
                System.out.println("[ID] " + id + " : " + contents + " ( " + date + " ) ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout() {
        status = false;
        nowID = null;
        nowName = null;
        System.out.println("로그아웃 성공!");
    }

    @Override
    public void quitMembership() {

        if (!status) {
            System.out.println("로그인 먼저 해주세요.");
            return;
        }

        String deleteInfo = "DELETE FROM INFO WHERE ENAME = ? and PW = ?";
        String deleteCont = "DELETE FROM CONTENTS WHERE ID = ?";

        Scanner sc = new Scanner(System.in);
        System.out.print("이름 재확인 : ");
        String name = sc.nextLine();
        System.out.print("PW 재확인 : ");
        String pw = sc.nextLine();

        try (
                Connection conn = connection();
        ) {
            try (PreparedStatement deleteinfo = conn.prepareStatement(deleteInfo)){
                deleteinfo.setString(1, name);
                deleteinfo.setString(2, pw);

                int result = deleteinfo.executeUpdate();
                if (result > 0) {
                    try(PreparedStatement deleteconts = conn.prepareStatement(deleteCont)){
                        deleteconts.setString(1, nowID);
                        int result2 = deleteconts.executeUpdate();
                        if (result2 > 0) {
                            System.out.println("회원탈퇴 성공! 이용해주셔서 감사합니다");

                            status = false;
                            nowID = null;
                            nowName = null;
                        }
                    }
                }else{
                    System.out.println("잘못된 정보입니다.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
