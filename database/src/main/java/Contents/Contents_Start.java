package Contents;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Contents_Start {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Contents contents = new Contentslmpl();
        while (true) {
            int firstMenu = contents.firstMenu();
            switch (firstMenu) {
                case 1:
                    contents.membership();
                    break;
                case 2:
                    contents.login();
                    break;
                case 3:
                    contents.addContents();
                    break;
                case 4:
                    contents.replaceContents();
                    break;
                case 5:
                    contents.deleteContents();
                    break;
                case 6:
                    contents.displayContents();
                    break;
                case 7:
                    contents.logout();
                    break;
                case 8:
                    contents.quitMembership();
                    break;
                case 9:
                    System.out.println("Thank you for using our system");
                    return;
            }
        }
    }
}

