package com.tess.note;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean
 * JMM(java内存模型Java Memory Model，简称JMM)本身是一种抽象的概念并不真实存在，它描述的是一组规则或规范，通过这组
 * 规范定义了程序中各个变量(包括实例字段，静态字段和构成数组对象的元素)的访问方式.
 * <p>
 * JMM关于同步的规定:
 * 1.线程解锁前，必须把共享变量的值刷新回主内存
 * 2.线程加锁前，必须读取主内存的最新值到自己的工作内存
 * 3.加锁解锁是同一把锁
 * <p>
 * 由于JVM运行程序的实体是线程，而每个线程创建时JVM都会为其创建于一个工作内存(有些地方称为栈空间),工作内存是每个线程的私
 * 有数据区域，而java内存模型中规定所有变量都存储在主内存，主内存是共享内存区域，所有线程都可以访问，但线程对变量的操作(读
 * 取赋值等)必须在工作内存中进行，首先要将变量从内存拷贝到线程自己的工作内存空间，然后对变量进行操作，操作完成后再将变
 * 量写回主内存，不能直接操作主内存中的变量，各个线程中的工作内存中存储着主内存中的变量副本拷贝，因此不同的线程间无法访问
 * 对方的工作内存，线程间的通信(传值)必须通过主内存来完成.
 */

class MyNumber {
    volatile int number = 10;

    void updNumber() {
        this.number = 1024;
    }
}

public class JMMNote {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is coming");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.updNumber();
            System.out.println(Thread.currentThread().getName() + "\t :" + myNumber);
        }, "AAA").start();

        while (myNumber.number == 10) {
        }
        System.out.println("Main function complete");
    }
}
