package himedia.practice;

public class AA_Start2 {
    public static void main(String[] args) {
        AA_Member member = new AA_Management();
        member.printPricePlan();

        while (true) {

            int mychoice = member.printmenu();
            switch (mychoice) {
                case 1:
                    member.addMember();
                    break;
                case 2:
                    member.CheckByEmail();
                    break;
                case 3:
                    member.CheckByName();
                    break;
                case 4:
                    member.CheckAll();
                    break;
                case 5:
                    member.updateMember();
                    break;
                case 6:
                    member.DeleteMember();
                    break;
                default:
                    System.out.println("올바른 숫자를 입력해주세요.");
                    break;
            }
        }
    }
}
