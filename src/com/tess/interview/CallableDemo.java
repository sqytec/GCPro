package com.tess.interview;

import java.util.concurrent.*;

/**
 * Author:   Sean
 * Create:   3/31/2020 9:14 PM
 * <p>
 * java多线程中，第3种获取多线程的方式
 */

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t" + " call function");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AAA").start();
        new Thread(futureTask2, "BBB").start();
        System.out.println("=======");
        while (!futureTask.isDone()) {

        }
        System.out.println(futureTask.get());
    }
}
