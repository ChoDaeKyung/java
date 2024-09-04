package himedia.practice;

public class AAA_strat3 {
    public static void main(String[] args) {
        AAA_account account = new AAA_accountlmpl();

        while(true){
            int bankAccount = account.printAccount();
            switch(bankAccount){
                case 1:
                    account.createAccount();
                    break;
                case 2:
                    account.deposit();
                    break;
                case 3:
                    account.withdraw();
                    break;
                case 4:
                    account.checkAccount();
                    break;
                case 5:
                    account.checkAll();
                    break;
                case 6 :
                    account.checkEveryOne();
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
            }
        }
    }
}
