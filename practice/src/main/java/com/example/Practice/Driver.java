package com.example.Practice;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Driver {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Tire tire = new KoreaTire();
        Car car = new Car(tire);

        System.out.println(car.getTireBrand());
        System.out.println(tire);
        System.out.println(tire.getBrand());
    }
}
