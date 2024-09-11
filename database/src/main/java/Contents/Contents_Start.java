package Contents;

public class Contents_Start {
    public static void main(String[] args) {
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
            }
        }
    }
}

