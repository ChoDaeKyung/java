package himedia.practice;

public class B_Start {
    public static void main(String[] args) {
        B_AccountBook accountbook = new B_AccountBooklmpl();

        while(true){
            int Choice = accountbook.printAccount();
            switch(Choice){
                case 1:
                    accountbook.addList();
                    break;
                case 2:
                    accountbook.checkListByDate();
                    break;
                case 3:
                    accountbook.deleteAll();
                    break;
                case 4:
                    accountbook.delectSelect();
                    break;
                default:
                    System.out.println("올바른 숫자를 입력해주세요.");
                    break;
            }
        }
    }
}
