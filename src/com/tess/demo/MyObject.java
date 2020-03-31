package com.tess.demo;

public class MyObject {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.getClass().getClassLoader());
        System.out.println(MyObject.class.getClassLoader());
    }
}
