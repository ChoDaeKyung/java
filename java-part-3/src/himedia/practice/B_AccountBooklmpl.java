package himedia.practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.detail;

public class B_AccountBooklmpl implements B_AccountBook {

    private Map<String, String[]> name = new HashMap<>();
    private String[2] detail = new ArrayList<>();

    @Override
    public int printAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
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
    public void AddList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("추가하고 싶은 상품과 가격을 입력해주세요.");
        System.out.print("상품 : ");
        String products = sc.nextLine();
        System.out.print("가격 : ");
        String prices = sc.nextLine();
        getNowDateTime();
        String Date = getNowDateTime();


        String[] product = new String[2];
        if(name.containsKey(Date)) {
            product = name.get(Date);
            product[0] = products;
            product[1] = prices;
        } else {
            List<String> newDate = new ArrayList<>();
            newDate.add("[상품] " + products + " [가격] " + prices);
            name.put(Date, newDate);
        }

        System.out.println(getNowDateTime() + "에 " + products + "와(과) " + prices + "원이 추가되었습니다.");
    }

    @Override
    public void checkListByDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고 싶은 날짜를 입력해주세요[ex) 0000-00-00] : ");
        String date = sc.nextLine();

        List<String> dataDetails = name.get(date);
        for(int i = 0; i < dataDetails.size(); i++) {
            System.out.println(dataDetails.get(i));
        }
    }

    @Override
    public void checkListByProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고 싶은 상품명을 입력해주세요 : ");
        String productName = sc.nextLine();

        for (Map.Entry<String, List<String>> Lists : name.entrySet()) {
            List<String> proDucts = name.get(Lists.getKey());
            if (name.containsValue(productName)) {
                System.out.println("[날짜] " + proDucts);
            }
        }
    }
}
