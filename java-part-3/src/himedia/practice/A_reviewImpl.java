package himedia.practice;

import java.util.*;

import static java.util.Arrays.*;

public class A_reviewImpl implements A_review {

    Map<String, String> map = new HashMap<>();
    Map<String, Integer> likeMap = new HashMap<>();

    @Override
    public void printMenu() {
        System.out.println("");
        System.out.println("===============댓글===============");
        System.out.println("[1]댓글추가 [2]좋아요 [3]전체보기 [4]내용삭제 [5]종료");
        System.out.println("=================================");
    }

    @Override
    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("원하시는 옵션의 번호를 입력해주세요 -> ");

        return sc.nextInt();
    }

    @Override
    public void User() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID를 입력해주세요 : ");
        String id = sc.nextLine().trim();
        System.out.print("댓글을 적어주세요 : ");
        String comments = sc.nextLine().trim();


        likeMap.put(id, 0);

        map.put(id, comments);

    }

    @Override
    public void LikeList() {

        Scanner sc = new Scanner(System.in);
        System.out.print("좋아요를 누르고 싶은 ID를 입력해주세요 : ");
        String idcomments = sc.nextLine();

            if (map.containsKey(idcomments)) {
                likeMap.put(idcomments, likeMap.get(idcomments) + 1);
                System.out.println(idcomments + " " + map.get(idcomments) + "     " + likeMap.get(idcomments));
            }else{
                System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public void Delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하고 싶은 ID를 입력해주세요 : ");
        String id = sc.nextLine();

        if (map.containsKey(id)) {
            map.remove(id);
            likeMap.remove(id);
        }else{
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public void totalLikeList() {
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(likeMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : entryList) {
            String userId = entry.getKey();
            int likeCount = likeMap.get(userId);
            String content = map.get(userId);

            System.out.println("사용자 ID: " + userId + ", 내용: " + content + ", 좋아요: " + likeCount);
        }
    }
}