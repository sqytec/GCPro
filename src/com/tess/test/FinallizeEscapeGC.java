package com.tess.test;

/**
 * Author:   Sean
 * Create:   3/29/2020 9:42 PM
 */
public class FinallizeEscapeGC {
    public static FinallizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("It's Alive!!!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finallize function is invoked!");
        SAVE_HOOK = this;
    }

    public static void test() throws Exception {
        SAVE_HOOK = new FinallizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("Die...");
        }
        System.out.println("===========================second");
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("Die...");
        }
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
