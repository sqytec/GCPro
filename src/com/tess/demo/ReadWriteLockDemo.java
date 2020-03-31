package com.tess.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author:   Sean
 * Date:     3/24/2020 11:16 PM
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是如果有一个线程想去写共享资源，就不应该再有其它线程可以对该资源进行读或者写
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 */

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String k, Object v) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始写入key:" + k);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + " 写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String k) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始读key:" + k);
            Object result = map.get(k);
            System.out.println(Thread.currentThread().getName() + " 读完成,数据为:" + result);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 10; i++) {
            final int tmpVal = i;
            new Thread(() -> {
                myCache.put(String.valueOf(tmpVal), tmpVal);
            }, String.valueOf(i)).start();

            new Thread(() -> {
                myCache.get(String.valueOf(tmpVal));
            }, String.valueOf(i)).start();
        }
    }
}
