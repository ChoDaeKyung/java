package himedia.practice;

import java.util.*;

public class AAA_accountlmpl implements AAA_account {

    Map<String, List<Object[]>> cusInfo = new HashMap<>();

    @Override
    public int printAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("================================================================================");
        System.out.println("원하시는 옵션을 선택해주세요.");
        System.out.println("[1] 계좌생성 [2] 입금 [3] 출금 [4] 잔액 조회 [5] 내역 조회 [6] 전체 조회 [7]프로그램 종료");

        return sc.nextInt();
    }

    @Override
    public void createAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력해주세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력해주세요.");
        String email = sc.nextLine();
        System.out.println("전화번호를 입력해주세요.");
        String phone = sc.nextLine();

        if (cusInfo.containsKey(email)) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        System.out.println("계좌 생성에 성공하였습니다.");
        int[] accountNum = new int[5];
        for (int i = 0; i < accountNum.length; i++) {
            if (accountNum[0] == 0) {
                accountNum[0] += (int) (Math.random() * 10);
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

        int MoNey = 0;
        String AccountN = "";
        System.out.print(name + "님의 계좌번호는 '");
        for (int i = 0; i < accountNum.length; i++) {
            System.out.print(accountNum[i]);
            AccountN += accountNum[i];
        }
        System.out.println("' 입니다.");

        Object[] cusTom = new Object[];
        cusInfo.put(AccountN, new ArrayList<>());
        cusTom[0] = name;
        cusTom[1] = email;
        cusTom[2] = phone;
        cusInfo.get(AccountN).add(cusTom);

    }

    @Override
    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("입급하실 계좌번호를 입력해주세요 : ");
        String accountNum = sc.nextLine();

        boolean CheckAccountNum = cusInfo.containsKey(accountNum);
        if (!CheckAccountNum) {
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        System.out.print("얼마를 입금하시겠습니까? -> ");
        int money = sc.nextInt();

        List<Object[]> membersInfo = cusInfo.get(accountNum);
        int result = (int)membersInfo.get(3) + money;

        membersInfo.add(result);
        cusInfo.put(accountNum, membersInfo);

        System.out.print("고객님의 현재 잔액은 : " + membersInfo.get(3) + "원 입니다.");
    }

    @Override
    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.print("출금하실 계좌번호를 입력해주세요 : ");
        String accountNum = sc.nextLine();

        boolean CheckAccountNum = cusInfo.containsKey(accountNum);
        if (!CheckAccountNum) {
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        System.out.print("얼마를 출금하시겠습니까? -> ");
        int money = sc.nextInt();

        List<Object> membersInfo = cusInfo.get(accountNum);
        int result = (int) membersInfo.get(3) - money;

        membersInfo.add(result);
        cusInfo.put(accountNum, membersInfo);

        System.out.print("고객님의 현재 잔액은 : " + membersInfo.get(3) + "원 입니다.");
    }

    @Override
    public void checkAll() {
        Scanner sc = new Scanner(System.in);
        System.out.print("계좌번호를 입력해주세요 : ");
        String accountNum = sc.nextLine();

        boolean CheckAccountNum = cusInfo.containsKey(accountNum);
        if (!CheckAccountNum) {
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        for (Map.Entry<String, List<Object>> customer : cusInfo.entrySet()) {
            List<Object> membersInfo = cusInfo.get(accountNum);
            if(customer.getKey().equals(accountNum))
                System.out.println("[이름]" + membersInfo.get(0) + " [전화번호] " + membersInfo.get(1) + " [이메일] " + membersInfo.get(2) + "[현재잔액] " +membersInfo.get(3));
        }
    }

    @Override
    public void checkAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("계좌번호를 입력해주세요 : ");
        String accountNum = sc.nextLine();

        boolean CheckAccountNum = cusInfo.containsKey(accountNum);
        if (!CheckAccountNum) {
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        List<Object> membersInfo = cusInfo.get(accountNum);
        System.out.println(membersInfo.get(0) + "고객님의 현재 잔액은 " + membersInfo.get(3) + "원 입니다.");
    }

    @Override
    public void checkEveryOne() {
        for (Map.Entry<String, List<Object>> customer : cusInfo.entrySet()) {
            List<Object> membersInfo = cusInfo.get(customer.getKey());
            System.out.println("[이름] "+membersInfo.get(0)+" [전화번호] " + membersInfo.get(1) + " [이메일] " + membersInfo.get(2) + "[현재잔액] " + membersInfo.get(3) + " [계좌번호] " + customer.getKey());
        }
    }
}