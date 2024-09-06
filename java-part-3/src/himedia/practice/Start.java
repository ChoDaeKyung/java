package himedia.practice;

public class Start {
    public static void main(String[] args) {
        AccountBook accountBook = new AccountBooklmpl();

        while(true){
            int choice = accountBook.printAccount();
            switch(choice){
                case 1:
                    accountBook.addList();
                    break;
                case 2:
                    accountBook.displayList();
                    break;
                case 3:
                    accountBook.deleteAccount();
                    break;
            }
        }
    }
}
