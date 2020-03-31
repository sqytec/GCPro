package com.tess.test;

import sun.misc.Unsafe;

/**
 * Author:   Sean
 * Create:   3/29/2020 8:03 PM
 */
public class JavaJVMStackSOF {
    private int stackLen = 1;

    public void stackLeak() {
        stackLen++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
       /* JavaJVMStackSOF oom = new JavaJVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println(oom.stackLen);
            throw e;
        }*/
        String s2 = new StringBuilder("voi").append("d").toString();
        System.out.println(s2.intern() == s2);
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1);
    }
}
