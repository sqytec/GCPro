package com.tess.train;

import java.util.concurrent.*;

class Lop implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        if (Integer.parseInt(Thread.currentThread().getName()) % 2 == 0) {
            return true;
        }
        return false;
    }
}

public class ThreadWaitNotify {
    public static void main(String[] args) throws Exception {
        Lop lop = new Lop();
        int thNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(thNum);
        addThread(thNum, countDownLatch);
        TimeUnit.SECONDS.sleep(3);
        wakeThread(thNum,countDownLatch);
        System.out.println("main function done....");
    }

    private static void wakeThread(int thNum, CountDownLatch countDownLatch) {
        for (int i = 0; i < thNum; i++) {
            countDownLatch.countDown();
        }
    }

    private static void addThread(int thNum, CountDownLatch countDownLatch) {
        for (int i = 0; i < thNum; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ": is waiting...");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + ": is running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
