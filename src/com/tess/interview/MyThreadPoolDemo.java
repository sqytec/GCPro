package com.tess.interview;

import java.util.concurrent.*;

/**
 * Author:   Sean
 * Create:   3/31/2020 10:04 PM
 * <p>
 * 第4种获得/使用java多线程的方式，线程池
 * <p>
 * 线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量超出数量的线程
 * 排队等候，等其它线程执行完毕，再从队列中取出任务来执行。
 * <p>
 * 它的主要特点为：线程复用；控制最大并发数；管理线程。
 * <p>
 * 第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控.
 */

class DefRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("===============自定义拒绝策略");
    }
}

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        fixedThreadPool();
//        singleThreadPool();
//        cacheThreadPool();
        defThreadPool();
    }

    public static void defThreadPool() {
        DefRejectedExecutionHandler defRejectedExecutionHandler = new DefRejectedExecutionHandler();
        int maximumPoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                maximumPoolSize,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());   //超过最大线程数和阻塞队列空间数和时，报异常，退出
//                new ThreadPoolExecutor.CallerRunsPolicy());//返回调用者
//                new ThreadPoolExecutor.DiscardOldestPolicy());//移除等待最久线程
//                new ThreadPoolExecutor.DiscardPolicy());//移除最新线程
                defRejectedExecutionHandler);//移除最新线程
        try {
            for (int i = 1; i <= 100; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 正在处理任务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    public static void cacheThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理数据");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public static void singleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理数据");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public static void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理数据");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
