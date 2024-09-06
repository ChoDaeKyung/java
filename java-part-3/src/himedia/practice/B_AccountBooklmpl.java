package himedia.practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class B_AccountBooklmpl implements B_AccountBook {

    private Map<String, List<String[]>> map = new HashMap<>();

    @Override
    public int printAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("==================================================");
        System.out.println("[1]내역추가 [2]내역조회 [3]전체삭제 [4]내역삭제 [5]종료");
        System.out.print("원하시는 옵션을 선택해주세요 : ");
        System.out.println("");

        return sc.nextInt();
    }

    @Override
    public String getNowDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public void addList() {
        Scanner sc = new Scanner(System.in);
        System.out.print("추가할 상품을 입력해주세요 : ");
        String name = sc.nextLine();
        System.out.print("상품의 가격을 입력해주세요 : ");
        String price = sc.nextLine();
        getNowDateTime();
        String date = getNowDateTime();
        String[] item = new String[2];
        item[0] = name;
        item[1] = price;

        if (map.containsKey(date)) {
            map.get(date).add(item);
        } else {
            List<String[]> list = new ArrayList<>();
            list.add(item);
            map.put(date, list);
        }

        System.out.println("추가내용 : [상품]" + name + " [가격]" + price + " [날짜]" + date);
    }

    @Override
    public void checkListByDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고 싶은 날짜를 입력해주세요 ex)yyyy-mm-dd : ");
        String date = sc.nextLine();

        if (map.isEmpty() || !map.containsKey(date)) {
            System.out.println("해당 날짜의 내역이 없습니다.");
            return;
        }

        List<String[]> list = map.get(date);
        for (int i = 0; i < list.size(); i++) {
            String[] detail = list.get(i);
            if (map.containsKey(date)) {
                System.out.println("[날짜]" + date + " [상품]" + detail[0] + " [가격]" + detail[1]);
            } else {
                System.out.println("해당 날짜의 내역이 없습니다.");
            }
        }
    }

    @Override
    public void deleteAll() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하고 싶은 날짜를 입력해주세요 : ");
        String date = sc.nextLine();

        if (map.containsKey(date)) {
            map.remove(date);
            System.out.println("해당 날짜의 내역 삭제를 완료했습니다.");
        } else {
            System.out.println("해당 날짜의 내역이 없습니다.");
        }
    }

    @Override
    public void delectSelect() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하고싶은 날짜를 입력해주세요 : ");
        String date = sc.nextLine();

        List<String[]> list = map.get(date);
        if (map.containsKey(date)) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("(" + (i+1) + ")" + " [상품]" + list.get(i)[0] + " [가격]" + list.get(i)[1]);
            }
        }else{
            System.out.println("해당 날짜의 내역이 없습니다.");
            return;
        }

        System.out.print("삭제하고 싶은 내역의 번호를 입력해주세요 : ");
        int num = sc.nextInt();
        list.remove(num-1);
        System.out.println("내역 삭제가 완료 되었습니다.");
    }
}