package com.tess.interview;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:   Sean
 * Create:   3/30/2020 4:51 PM
 * 可重入锁(也叫递归锁)ReentrantLock/Synchronized
 * <p>
 * 指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * <p>
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块.
 *
 * 可重入锁可以避免死锁
 */

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getId() + " \t" + "sendSMS");
        sendEMAIL();
    }

    public synchronized void sendEMAIL() {
        System.out.println(Thread.currentThread().getId() + " \t" + "sendEMAIL");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + " \t" + "get function");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + " \t" + "set function");
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();

    }
}
