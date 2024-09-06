package himedia.practice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountBooklmpl implements AccountBook {

    private String desktopPath;
    private String folderPath;
    private Path Account;
    private Path todayAccount;
    int totalMoney =0;

    public AccountBooklmpl() {
        this.desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        this.folderPath = desktopPath + File.separator + "가계부";
        this.Account = Paths.get(folderPath);
    }

    @Override
    public void howMuch(int money) {
        money=totalMoney;
    }

    @Override
    public int printAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("==================================================");
        System.out.println("[1]내역추가 [2]내역조회 [3]전체삭제 [4]내역삭제 [5]종료");
        System.out.print("원하시는 옵션을 선택해주세요 : ");

        return sc.nextInt();
    }

    @Override
    public String getNowDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public void addList() throws RuntimeException {
        Scanner sc = new Scanner(System.in);
        System.out.print("추가할 상품을 입력해주세요 : ");
        String name = sc.nextLine();
        System.out.print("상품의 가격을 입력해주세요 : ");
        String price = sc.nextLine();

        getNowDateTime();
        String date = getNowDateTime();

        try {
            if (Files.notExists(Account)) {
                Files.createDirectory(Account);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        todayAccount = Account.resolve(date + ".txt");

        if (Files.notExists(todayAccount)) {
            try (FileOutputStream fos = new FileOutputStream(todayAccount.toFile())) {
                String Name = "[상품]" + name + " ";
                String Price = "[가격]" + price + "\n";
                totalMoney = 0;
                totalMoney += Integer.parseInt(price);
                howMuch(totalMoney);
                fos.write(Name.getBytes());
                fos.write(Price.getBytes());
                System.out.println("총금액 : " + totalMoney);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (FileOutputStream fos = new FileOutputStream(todayAccount.toFile(), true)) {
                String Name = "[상품]" + name + " ";
                String Price = "[가격]" + price + "\n";
                totalMoney += Integer.parseInt(price);
                fos.write(Name.getBytes());
                fos.write(Price.getBytes());
                System.out.println("총금액 : " + totalMoney);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void displayList() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고 싶은 날짜를 입력해주세요 : ");
        String date = sc.nextLine();

        todayAccount = Account.resolve(date + ".txt");

        if ( Files.exists(todayAccount) ) {
            try (FileInputStream fis = new FileInputStream(todayAccount.toFile())) {
                int byteData;
                while ( (byteData = fis.read()) != -1 ) {
                    System.out.print((char) byteData);
                }
                System.out.println();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("해당 날짜의 내역이 존재하지 않습니다.");
        }
    }

    @Override
    public void deleteAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하고 싶은 날짜를 입력해주세요 : ");
        String date = sc.nextLine();

        todayAccount = Account.resolve(date + ".txt");

        if ( Files.exists(todayAccount) ){
        }
    }
}


