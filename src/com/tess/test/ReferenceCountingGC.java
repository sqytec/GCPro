package com.tess.test;

/**
 * Author:   Sean
 * Create:   3/29/2020 9:24 PM
 */
public class ReferenceCountingGC {
    public Object instance = null;
    public static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[_1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();

    }

    public static void main(String[] args) {
        testGC();
    }
}
