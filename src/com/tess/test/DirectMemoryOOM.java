package com.tess.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Author:   Sean
 * Create:   3/29/2020 9:04 PM
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        System.out.println(unsafeField.getName());
        System.out.println(unsafeField.toString());
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
