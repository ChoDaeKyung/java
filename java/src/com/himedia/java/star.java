package com.himedia.java;

public class star {

    public static void main(String[] args) {

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j <= i; j++) {
//                System.out.print("*"); // 별 출력
//            }
//            System.out.println();
//        }
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5 - i; j++) {
//                System.out.print(" ");
//            }
//            for (int j = 0; j <= i; j++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//
//        for (int i = 5; i >= 0; i--) {
//            for (int j = i; j > 0; j--) {
//                System.out.print("*"); // 별 출력
//            }
//            System.out.println();
//        }
//
//        for (int i = 5; i > 0; i--) {
//            for (int j = 5 - i; j > 0; j--) {
//                System.out.print(" ");
//            }
//            for (int j = i; j > 0; j--) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }


        for (int i = 1; i <= 10 - 1; i++) {
            if (i <= 5) {
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
            } else {
                for (int j = 1; j <= 10 - i; j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}

