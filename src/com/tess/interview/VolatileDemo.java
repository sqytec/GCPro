package com.tess.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:   Sean
 * Create:   3/30/2020 10:39 AM
 * 1.验证Volatile可见性
 * 1.1假如int num = 0; num变量之前根本没有添加volatile关键字修饰，没有可见性
 * 1.2添加了volatile，可以解决可见性问题。
 * <p>
 * 2.验证volatile不保证原子性
 * 2.1原子性指的是什么意思？
 * 不可以分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。
 * 需要整体完整，要么同时成功，要么同时失败
 * 2.2volatile不保证原子性的案例演示
 * 2.3why
 * 2.4如何解决原子性？
 *      2.41加synchronized
 *      2.42使用juc下的AtomicInteger   ----->CAS
 */
class MyData {
    volatile int num = 0;

    public void addTO() {
        this.num = 60;
    }

    public void addAuto() {
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkVolatile();
        varifyAtomic();
    }

    private static void varifyAtomic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addAuto();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(myData.num);
        System.out.println(myData.atomicInteger);
    }

    private static void seeOkVolatile() {
        MyData myData = new MyData();
        System.out.println(myData.num);
        System.out.println("=============");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTO();
            System.out.println(Thread.currentThread().getName() + "\t " + myData.num);
        }, "AAA").start();

        while (myData.num == 0) {
        }
        System.out.println(Thread.currentThread().getName() + "\t " + myData.num);
    }
}
