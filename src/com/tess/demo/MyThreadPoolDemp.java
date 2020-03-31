package com.tess.demo;

import java.util.concurrent.*;

/**
 * Author:   Sean
 * Create:   3/25/2020 9:04 PM
 */
public class MyThreadPoolDemp {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService thPool = new ThreadPoolExecutor(2,
                processors,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
//                new ThreadPoolExecutor.CallerRunsPolicy());
                new ThreadPoolExecutor.DiscardPolicy());
        /*ExecutorService thPool = Executors.newFixedThreadPool(3);//一个线程池固定数量线程
        ExecutorService thPool = Executors.newSingleThreadExecutor();//一个线程池一个线程
        ExecutorService thPool = Executors.newCachedThreadPool();//一个线程池N个线程*/
        try {
            for (int i = 0; i < 10; i++) {
                thPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thPool.shutdown();
        }
    }
}
