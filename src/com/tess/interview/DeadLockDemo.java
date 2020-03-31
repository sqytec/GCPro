package com.tess.interview;

import java.util.concurrent.TimeUnit;

/**
 * Author:   Sean
 * Create:   4/1/2020 12:10 AM
 */

class HoldLock implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有lockA" + "\t尝试获取lockB");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有lockB" + "\t尝试获取lockA");
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLock(lockB, lockA), "ThreadBBB").start();
    }
}
