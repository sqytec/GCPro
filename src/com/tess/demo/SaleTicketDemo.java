package com.tess.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticker {
    private int num = 30;

    public void sale() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (num > 0) {

                System.out.println("当前线程名:" +
                        Thread.currentThread().getName() +
                        "卖出第" + (num--) + "张票，剩余" + num);
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author Sean
 * @date 20200317
 * <p>
 * 题目 三个售票员 卖出30张票
 * 笔记:如何编写企业级的多线程代码
 * 固定的编程套路+模板
 * <p>
 * 1.在高内聚低耦合的前提下,线程    操作  资源类
 * 1.1一言不合先创建一个资源类
 */

public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticker ticker = new Ticker();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticker.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticker.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticker.sale();
        }, "C").start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticker.sale();
                }
            }
        }, "售票员A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticker.sale();
                }
            }
        }, "售票员B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticker.sale();
                }
            }
        }, "售票员C").start();*/
    }
}
