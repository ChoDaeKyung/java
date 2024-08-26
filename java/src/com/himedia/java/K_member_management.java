package com.himedia.java;

import java.util.Scanner;

public class K_member_management {

    static int totalCnt = 0;
    static int totalMemberCnt = 0;

    // 요금제
    public static int printPricePlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[요금제를 선택하세요.]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");

        return sc.nextInt();
    }

    public static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 " + totalMemberCnt + " / " + totalCnt + "]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");

        return sc.nextInt();
    }

    public static void addMember(String[][] members) {
        // 사용자로부터 이름, 이메일, 연락처
        if (totalMemberCnt >= totalCnt){
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력하세요.");
        String email = sc.nextLine();
        System.out.println("연락처를 입력하세요.");
        String phone = sc.nextLine();

        if(checkemail(members,email)){
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        // members에 넣어주세요.
        members[totalMemberCnt][0] = name;
        members[totalMemberCnt][1] = email;
        members[totalMemberCnt][2] = phone;

        totalMemberCnt++;
    }

    public static boolean checkemail(String[][] members, String email) {
      for(int i=0;i<members.length;i++){
          if(email.equals(members[i][0])){
              return true;
          }
      }
        return false;
    }

    public static void selectEmail(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이메일을 입력하세요.");
        String email = sc.nextLine();

        for(int i=0;i<members.length;i++){
            if(email.equals(members[i][1])){
                System.out.println("[이름] "+members[i][0]+" [이메일] "+members[i][1]+" [연락처] "+members[i][2]);
                return;
            }
        }

        System.out.println("찾으시는 정보가 없습니다.");
    }

    public static void selectName(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이름을 입력하세요.");
        String name = sc.nextLine();

        boolean flag = false;
        for (int i = 0; i < members.length; i++) {
            if (name.equals(members[i][0])) {
                System.out.println("[이름] " + members[i][0] + " [이메일] " + members[i][1] + " [연락처] " + members[i][2]);
                flag = true;
            }
        }
        ;

        if (!flag) {
            System.out.println("찾으시는 정보가 없습니다.");
        }
    }

    public static void selectAll(String[][] members) {
        for (int i = 0; i < members.length; i++) {
            if (members[i][0] == null) {
                break;
            }
            System.out.println((i+1) + ": [이름] " + members[i][0] + " [이메일] " + members[i][1] + " [연락처] " + members[i][2]);
        }
    }

    public static void updateMember(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("회원님의 이메일을 입력하세요.");
        String email = sc.nextLine();

         boolean update = false;
        for (int i = 0; i < members.length; i++) {
            if (email.equals(members[i][1])) {
                System.out.println("이름을 입력하세요.");
                String newName = sc.nextLine();
                System.out.println("이메일을 입력하세요.");
                String newEmail = sc.nextLine();
                System.out.println("연락처를 입력하세요.");
                String newPhone = sc.nextLine();
                update = true;
                members[i][0] = newName;
                members[i][1] = newEmail;
                members[i][2] = newPhone;
                System.out.println("수정이 완료 되었습니다.");
            }

            if(!update){
                System.out.println("찾으시는 회원이 없습니다.");
                break;
            }
        }
    }

    public static void deleteMember(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("회원님의 이메일을 입력하세요.");
        String email = sc.nextLine();
        boolean delete = false;
         for (int i = 0; i < members.length; i++) {
             if (email.equals(members[i][1])) {
                 members[i][0] = null;
                 members[i][1] = null;
                 members[i][2] = null;
                 delete = true;
                 totalMemberCnt--;
                 System.out.println("회원 탈퇴가 완료되었습니다.");
                 for (int j = i + 1; j < members.length; j++) {
                     members[j - 1][0] = members[j][0];
                     members[j - 1][1] = members[j][1];
                     members[j - 1][2] = members[j][2];
                 }
             }
         if(!delete){
             System.out.println("회원 정보를 찾을 수 없습니다.");
             break;
         }

         }
    }

    public static void main(String[] args) {
        // 사용자로부터 요금제 선택을 받아서
        // 해당 크기에 맞는 2차원배열을 생성해주세요.
        // 단, 열은 3열로 고정한다.
        // 배열명 : members
        int pricePlanNum = printPricePlan();
        String[][] members = new String[pricePlanNum * 10][3];
        totalCnt = pricePlanNum * 10;

        while (true) {
            // 7번 프로그램 종료를 완성시켜주세요.
            // "이용해주셔서 감사합니다."
            int menuNum = printMenu();
            switch (menuNum) {
                case 1:
                    addMember(members);
                    break;
                case 2:
                    selectEmail(members);
                    break;
                case 3:
                    selectName(members);
                    break;
                case 4:
                    selectAll(members);
                    break;
                case 5:
                    updateMember(members);
                    break;
                case 6:
                    deleteMember(members);
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;

                default:
                    System.out.println("잘못 선택하셨습니다.");
                    break;
            }
        }

    }
}