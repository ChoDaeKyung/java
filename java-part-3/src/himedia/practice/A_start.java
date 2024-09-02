package himedia.practice;

public class A_start {
    public static void main(String[] args) {
      A_review Review = new A_reviewImpl();

        while(true){
            Review.printMenu();
            int myChoice = Review.getChoice();
            switch(myChoice){
                case 1:
                    Review.User();
                    break;
                case 2:
                    Review.LikeList();
                    break;
                case 3:
                    Review.totalLikeList();
                    break;
                case 4:
                    Review.Delete();
                    break;
                case 5:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
            }
        }
    }
}
