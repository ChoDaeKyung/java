package com.himedia.practice;

public class J_array {

    public static void exam1(){
        int[] score = new int[3];

        score[0] = 10;
        score[1] = 20;
        score[2] = 30;

        for (int i=0; i<3; i++){
            System.out.println(score[i]);
        }
    }

    public static void exam2(){
        String[] temp = new String[3];
        temp[0] = "Cho";
        temp[1] = "Dae";
        temp[2] = "Kyung";

        for ( int i=0;i<temp.length;i++){
            System.out.println(temp[i]);
        }
    }

    public static void exam3() {
        char[] alpha = { 'a' , 'b' , 'c'};
        char beta = 'B';
        System.out.println(alpha.length);
        alpha[0] = 'A';
        alpha[1] = beta;
        System.out.println(alpha[0]);
        System.out.println(alpha[1]);
        System.out.println(beta);
    }

    public static String exam4(String[] name, String myname) {
        name[1] = "Brian";
        myname = "DaeKyung";
        return myname;
    }

    public static void exam4_1(){
        String[] name = {"John","Tom","Sam"};
        String myname = "Something";

        for (int i=0; i<name.length; i++){
            System.out.println(name[i]);
        }
        System.out.println(myname);

        myname = exam4(name,myname);
        System.out.println(myname);
        System.out.println(name[1]);
    }

    public static void main(String[] args){
        exam4_1();
    }
}
