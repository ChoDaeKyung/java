package com.himedia.practice;

import java.util.Scanner;

public class M_household_Account {

    public static int printAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("조회하시고 싶으신 달을 선택해주세요.");
        System.out.println("[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12]");

        return sc.nextInt();
    }

    public static void Products(String[][] products) {

        int productCase = 0;

        if (productCase >= 11) {
            for (int i = 1; i < productCase; i++) {
                for (int j = 0; j < 2; j++) {
                    products[i - 1][j] = products[i][j];
                }
            }
            productCase--;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("가계부에 할 상품을 입력해주세요.");
        String product = sc.nextLine();
        System.out.println("상품의 가격을 입력해주세요.");
        String price = sc.nextLine();

        products[productCase][0] = product;
        products[productCase][1] = price;

        productCase++;

    }

    public static int Choice() {
        String[][] products = new String[10][2];
        Scanner sc = new Scanner(System.in);
        System.out.println("[1]상품추가 [2]가계부 조회 [3]나가기");
        int choice = sc.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    Products(products);
                    break;
                case 2:
                    Products(products);
                    break;
            }
            return choice;
        }
    }

    public static void Month(String[][] month) {


    }


    public static void main(String[] args) {

        while (true) {
            int AccountMonth = printAccount();
            switch (AccountMonth) {
                case 1:
                    while(true) {
                        Choice();
                        break;
                    }
                }
            }
        }
    }
