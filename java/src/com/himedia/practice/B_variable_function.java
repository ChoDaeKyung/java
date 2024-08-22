package com.himedia.practice;

public class B_variable_function {
    public static void exam1() {
        String word="Hi";
        System.out.println(word);
    }

    public static int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public static void main(String[] args){
        int result = add(10,3);
        exam1();
        System.out.println(result);
    }
}