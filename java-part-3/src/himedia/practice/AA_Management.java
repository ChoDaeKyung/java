package himedia.practice;

import java.util.*;

public class AA_Management implements AA_Member {
    Map<String, List<String>> Members = new HashMap<>();

    static int totalMemberCnt = 0;
    static int totalCnt = 10;

    @Override
    public int printPricePlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[요금제를 선택하세요.]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명 [4]Freepass : 제한없음");

        return sc.nextInt();
    }

    @Override
    public int printmenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 " + totalMemberCnt + " / " + totalCnt + "]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");

        return sc.nextInt();
    }

    @Override
    public void addMember() {

        if (totalMemberCnt >= totalCnt) {
            System.out.println("요금제를 변경하세요.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력하세요.");
        String email = sc.nextLine();
        System.out.println("연락처를 입력하세요.");
        String phone = sc.nextLine();

        if (Members.containsKey(email)) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        Members.put(email, new ArrayList<>());
        Members.get(email).add(name);
        Members.get(email).add(phone);

        totalMemberCnt++;
    }

    @Override
    public void CheckByEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.print("이메일을 입력해주세요 : ");
        String email = sc.nextLine();

        List<String> membersInfo = Members.get(email);
        if (Members.containsKey(email)) {
            System.out.println("[이름] " + membersInfo.get(0) + " [이메일] " + email + " [전화번호] " + membersInfo.get(1));
        } else {
            System.out.println("찾으시는 정보가 없습니다.");
        }
    }


    @Override
    public void CheckByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력해주세요 : ");
        String name = sc.nextLine();

        for (Map.Entry<String, List<String>> entry : Members.entrySet()) {
            List<String> membersInfo = Members.get(entry.getKey());
            if (Members.containsValue(name)) {
                System.out.println("[이름] " + name + " [이메일] " + entry.getKey() + " [전화번호] " + membersInfo.get(1));
            } else {
                System.out.println("찾으시는 정보가 없습니다.");
            }
        }
    }


    @Override
    public void CheckAll() {
        for (Map.Entry<String, List<String>> entry : Members.entrySet()) {
            List<String> membersInfo = Members.get(entry.getKey());
            System.out.println("[이름] " +membersInfo.get(0) + " [이메일] " + entry.getKey() + " [전화번호] " + membersInfo.get(1));
        }
    }

    @Override
    public void updateMember() {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력해주세요 : ");
        String name = sc.nextLine();

        for (Map.Entry<String, List<String>> entry : Members.entrySet()){
            System.out.println("[이름] " + Members.get(0) + " [이메일] " + entry.getKey() + " [전화번호] " + Members.get(1));
        }
    }

    @Override
    public void DeleteMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("이메일을 입력해주세요 : ");
        String email = sc.nextLine();

        for (Map.Entry<String, List<String>> entry : Members.entrySet()) {
            if (entry.getValue().equals(email)) {
                Members.remove(entry.getKey());
            }
        }
    }
}


