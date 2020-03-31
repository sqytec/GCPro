package com.tess.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:   Sean
 * Create:   3/30/2020 10:18 PM
 * <p>
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *
 * 1    线程  操作(方法)  资源类
 * 2    判断  干活      通知
 * 3    防止虚假唤醒机制
 */

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void addFunc() {
        lock.lock();
        try {
            while (number != 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void subFunc() {
        lock.lock();
        try {
            while (number == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.addFunc();
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.subFunc();
            }, String.valueOf(i)).start();
        }
    }
}
