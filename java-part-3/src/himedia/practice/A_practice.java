package himedia.practice;

import java.util.ArrayList;
import java.util.List;

public class A_practice {

    public void practice() {
        List<String> list = new ArrayList<String>();

        list.add("조대경");
        list.add("홍길동");
        list.add("이순신");
        list.add("강감찬");
        list.add("스마일");

        System.out.println(list);
        System.out.println(list.get(2));

        for(int i=0;i<list.size(); i++){
            System.out.print(list.get(i) + ", ");
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i) == "이순신"){
                list.add(i,"Lee순신");
            }
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        A_practice Apractice = new A_practice();
        Apractice.practice();
    }
}
