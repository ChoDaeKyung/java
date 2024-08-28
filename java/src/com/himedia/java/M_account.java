package com.himedia.java;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.Scanner;


public class M_account {

    static int totalMoney=0;
    static int accountTimes=0;

    public static int printAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("================================================================================");
        System.out.println("원하시는 옵션을 선택해주세요.");
        System.out.println("[1] 계좌생성 [2] 입금 [3] 출금 [4] 잔액 조회 [5] 내역 조회 [6]프로그램 종료");
        System.out.println("고객님의 현재 잔액은: " + totalMoney +"원 입니다.");

        return sc.nextInt();
    }

    public static void createAccount(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력해주세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력해주세요.");
        String email = sc.nextLine();
        System.out.println("전화번호를 입력해주세요.");
        String phone = sc.nextLine();

        System.out.println("계좌 생성에 성공하였습니다.");
        int[] accountNum = new int[5];
        for (int i = 0; i < accountNum.length; i++) {
            if (accountNum[0] == 0) {
                accountNum[0] += (int)(Math.random()*10);
                continue;
            }
            accountNum[i] = (int) (Math.random() * 10);
            for (int j = 0; j < i; j++) {
                if (accountNum[j] == accountNum[i]) {
                    i--;
                    break;
                }
            }
        }

        System.out.print(name+"님의 계좌번호는 '");
        for(int i=0; i<accountNum.length; i++){
            System.out.print(accountNum[i]);};
        System.out.println("' 입니다.");
    }

    public static void Deposit(String[][] MyAccount){

        if(accountTimes>=5) {
            for (int i = 1; i < accountTimes; i++) {
                for (int j = 0; j < 3; j++) {
                    MyAccount[i-1][j] = MyAccount[i][j];
                }
            }
            accountTimes--;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("얼마를 입금하시겠습니까?");
        int deposit = sc.nextInt();

        totalMoney += deposit;

        System.out.println("고객님의 현재 잔액은 : " + totalMoney + "원 입니다.");
        Date date = new Date();
        String nowdate = date.toString();
        MyAccount[accountTimes][0] = String.valueOf("[입금]" +deposit);
        MyAccount[accountTimes][1] = String.valueOf("[잔액]" + totalMoney);
        MyAccount[accountTimes][2] = nowdate;

        accountTimes++;
    }

    public static void Withdraw(String[][] MyAccount){

        if(accountTimes>=5) {
            for (int i = 1; i < accountTimes; i++) {
                for (int j = 0; j < 3; j++) {
                    MyAccount[i-1][j] = MyAccount[i][j];
                }
            }
            accountTimes--;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("얼마를 출금하시겠습니까?");
        int withdraw = sc.nextInt();

        totalMoney -= withdraw;

        System.out.println("고객님의 현재 잔액은 : " + totalMoney +"원 입니다.");
        Date date = new Date();
        String nowdate = date.toString();
        MyAccount[accountTimes][0] = String.valueOf("[출금]" + withdraw);
        MyAccount[accountTimes][1] = String.valueOf("[잔액]" + totalMoney);
        MyAccount[accountTimes][2] = nowdate;

        accountTimes++;
    }

    public static void checkAccount(String[][] MyAccount){
        System.out.println("고객님의 현재 잔액은 : " + totalMoney +"원 입니다.");
    }

    public static void Details(String[][] MyAccount){
        for (int i = 0; i < accountTimes; i++) {
            System.out.print(MyAccount[i][0] +"  ");
            System.out.print(MyAccount[i][1] +"  ");
            System.out.println(MyAccount[i][2]);
        }
    }


    public static void main(String[] args){
        String[][] member = new String[5][5];
        String[][] myAccount = new String[5][3];

        while(true){
            int BankAccount = printAccount();
            switch(BankAccount){
                case 1:
                    createAccount(member);
                    break;
                case 2:
                    Deposit(myAccount);
                    break;
                case 3:
                    Withdraw(myAccount);
                    break;
                case 4:
                    checkAccount(myAccount);
                    break;
                case 5:
                    Details(myAccount);
                    break;
                case 6:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
            }
        }
    }
}
