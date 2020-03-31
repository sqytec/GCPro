package com.tess.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author:   Sean
 * Create:   3/30/2020 5:16 PM
 * <p>
 * 题目：实现一个自旋锁
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait的阻塞
 * <p>
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒，B随后进来后发现
 * 当前有线程持有锁，不是null，所以只能通过自选等待，直到A释放锁后B随后抢到.
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + "\t" + " coming in");
        while (!atomicReference.compareAndSet(null, t)) {
        }
    }

    public void myUnLock() {
        Thread t = Thread.currentThread();
        atomicReference.compareAndSet(t, null);
        System.out.println(t.getName() + "\t" + " invoked myUnLock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "AAA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }, "BBB").start();

    }
}
