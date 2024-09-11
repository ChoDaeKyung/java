import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class database_practice implements database_prac_Interface {

    @Override
    public int Sellect() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("==================================================");
        System.out.println("[1]AddInfo [2]DeleteInfo [3]GivePay [4]DisplayInfo [5]QUIT");
        System.out.print("Choose your Select : ");

        return sc.nextInt();
    }

    @Override
    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/database_practice";
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public int jobNum(String job) {
        int jobnum = 0;
        if (job.equals("STUDENT") || job.equals("student") || job.equals("Student")) {
            jobnum = 10;
        }
        if (job.equals("TEACHER") || job.equals("teacher") || job.equals("Teacher")) {
            jobnum = 20;
        }
        if (job.equals("PROGRAMMER") || job.equals("programmer") || job.equals("Programmer")) {
            jobnum = 30;
        }

        return jobnum;
    }

    @Override
    public void addInfo() {
        String query = "INSERT INTO ACADEMY (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO, EMAIL, PHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryCheckEmail = "SELECT EMAIL FROM ACADEMY WHERE EMAIL = ?";
        String queryCheckPhone = "SELECT PHONE FROM ACADEMY WHERE PHONE = ?";

        Scanner sc = new Scanner(System.in);
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.print("Job : ");
        String job = sc.nextLine();
        System.out.print("EMAIL : ");
        String email = sc.nextLine();
        System.out.print("PHONE : ");
        String phone = sc.nextLine();

        getNowDateTime();
        String date = getNowDateTime();

        try (
                Connection conn = connection();
        ) {
            boolean isPhoneExist = false;
            boolean isEmailExist = false;

            try (PreparedStatement checkPhone = conn.prepareStatement(queryCheckPhone);) {
                checkPhone.setString(1, phone);
                ResultSet rs = checkPhone.executeQuery();
                if (rs.next()) {
                    isPhoneExist = true;
                }
            }
            try (PreparedStatement checkEmail = conn.prepareStatement(queryCheckEmail);) {
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
                System.out.println("Press 4 to Display your Information.");
            } else if (isEmailExist) {
                System.out.println("Your Email is already exist! Press 4 to Display your Information.");
                System.out.println("Press 4 to Display your Information.");
            } else {
                // 사원번호의 각 번호를 4칸의 배열안에 각각 삽입 (첫번째 숫자는 무조건 7)
                int[] empno = new int[4];
                empno[0] = 7;
                for (int i = 1; i < empno.length; i++) {
                    empno[i] = (int) (Math.random() * 10);
                }

                // 각각 삽입된 네개의 숫자를 String empNO라는 문자열로 나열 및 저장
                String empNO = "";
                System.out.print("Your EMPNO is : ");
                for (int i = 0; i < empno.length; i++) {
                    System.out.print(empno[i]);
                    empNO += empno[i];
                }
                System.out.println("");

                // 부서번호의 각 번호를 4칸의 배열안에 각각 삽입 (첫번째 숫자는 무조건 5)
                int[] deptno = new int[4];
                deptno[0] = 5;
                for (int i = 1; i < deptno.length; i++) {
                    deptno[i] = (int) (Math.random() * 10);
                }

                // 부서번호의 각 번호를 4칸의 배열안에 각각 삽입
                String deptNO = "";
                System.out.print("Your DEPTNO is : ");
                for (int i = 0; i < deptno.length; i++) {
                    System.out.print(deptno[i]);
                    deptNO += deptno[i];
                }
                System.out.println("");

                // String 타입으로 저장해뒀던 사원번호 및 부서번호를 Integer 타입으로 변환
                int EmpNo = Integer.parseInt(empNO);
                int DeptNo = Integer.parseInt(deptNO);

                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    // 각 table의 순서에 맞게 정보 저장
                    preparedStatement.setInt(1, EmpNo);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, job);
                    preparedStatement.setInt(4, jobNum(job));
                    preparedStatement.setString(5, date);
                    preparedStatement.setInt(6, 0);
                    preparedStatement.setInt(7, 0);
                    preparedStatement.setInt(8, DeptNo);
                    preparedStatement.setString(9, email);
                    preparedStatement.setString(10, phone);


                    int result = preparedStatement.executeUpdate();
                    if (result > 0) { // 만약 저장에 성공했다면
                        System.out.println("WELCOME!");
                        System.out.println("");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteInfo() {
        // 사원번호와 이름 모두 일치해야만 삭제가 가능하도록 and 사용
        String query = "DELETE FROM ACADEMY WHERE EMPNO = ? and ENAME = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("Write the NAME : ");
        String name = sc.nextLine();
        System.out.print("Write the EMPNO : ");
        int empno = sc.nextInt();
        sc.nextLine();

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, empno);
            preparedStatement.setString(2, name);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Delete Success!");
                System.out.println("");
            } else {
                System.out.println("There's no Information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void givePay() {
        String query = "UPDATE ACADEMY SET SAL = ?, COMM = ? WHERE EMPNO = ? and ENAME = ?";
        String totalmoney = "SELECT SAL, COMM FROM ACADEMY WHERE ENAME = ? and EMPNO = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("Write the NAME : ");
        String name = sc.nextLine();
        System.out.print("Write the EMPNO : ");
        int empno = sc.nextInt();
        sc.nextLine();

        int totalMoney = 0;
        int totalBonus = 0;

        try (
                Connection conn = connection();
        ) {
            try (PreparedStatement checkmoney = conn.prepareStatement(totalmoney)) {
                checkmoney.setString(1, name);
                checkmoney.setInt(2, empno);

                ResultSet resultSet = checkmoney.executeQuery();
                if (resultSet.next()) {
                    int moneyis = resultSet.getInt("SAL");
                    int bonusis = resultSet.getInt("COMM");

                    totalMoney = moneyis;
                    totalBonus = bonusis;
                }

            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(3, empno);
                preparedStatement.setString(4, name);

                preparedStatement.setInt(1, totalMoney);
                preparedStatement.setInt(2, totalBonus);

                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    System.out.print("How much do you want to give? : ");
                    int pay = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Bonus : ");
                    int bonus = sc.nextInt();
                    sc.nextLine();
                    preparedStatement.setInt(1, totalMoney + pay);
                    preparedStatement.setInt(2, totalBonus + bonus);
                    int result2 = preparedStatement.executeUpdate();
                    if (result2 > 0) {
                        System.out.println("Pay Complete!");
                    } else {
                        System.out.println("Pay Fail..");
                    }
                } else {
                    System.out.println("There's no Information.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void displayInfo () {
            String query = "SELECT ENAME, EMPNO, MGR, HIREDATE, DEPTNO, SAL, COMM FROM ACADEMY WHERE ENAME = ? and (EMAIL = ? or PHONE = ?)";
            Scanner sc = new Scanner(System.in);
            System.out.print("Write the NAME : ");
            String name = sc.nextLine();
            System.out.print("Write the EMAIL or PHONE : ");
            String emailorphone = sc.nextLine();

            try (
                    Connection conn = connection();
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
            ) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, emailorphone);
                preparedStatement.setString(3, emailorphone);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int empno = resultSet.getInt("EMPNO");
                    int mgr = resultSet.getInt("MGR");
                    String HIREDATE = resultSet.getString("HIREDATE");
                    int deptno = resultSet.getInt("DEPTNO");
                    int sal = resultSet.getInt("SAL");
                    int comm = resultSet.getInt("COMM");

                    System.out.println("[NAME]" + name + " [EMPNO]" + empno + " [MGR]" + mgr + " [HIREDATE]" + HIREDATE + " [DEPTNO]" + deptno + " [TOTAL_SAL]" + sal + " [TOTAL_BONUS]" + comm);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }






