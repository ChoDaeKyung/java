package com.himedia.java;

public class G_maltiz extends G_dog{

    public void size(){
        System.out.println(name + "'s size is small");
    }

    @Override
    public void bark() {
        System.out.println(name + " is barking 왕왕~");
    }

    @Override
    public void walk() {
        System.out.println("뒤뚱뒤뚱");
    }
}
