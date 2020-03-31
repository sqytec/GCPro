package com.tess.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author:   Sean
 * Create:   3/30/2020 5:48 PM
 * <p>
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该再有其它线程可以对该资源进行读或写
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * <p>
 * 写操作：院子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断
 */

class MyCache {

    private Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void putData(String k, Object v) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入:" + v);
            map.put(k, v);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t写入完成:" + v);
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public void getData(String k) {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取k对应的值");
            System.out.println(Thread.currentThread().getName() + "\t读取完成" + map.get(k));
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void clearData() {
        map.clear();
    }

}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.putData(String.valueOf(tmp), tmp);
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.getData(String.valueOf(tmp));
            }, String.valueOf(i)).start();
        }
    }
}
