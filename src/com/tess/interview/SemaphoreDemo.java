package com.tess.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Author:   Sean
 * Create:   3/30/2020 8:44 PM
 */
public class SemaphoreDemo {
    private final static int TOT_SOURCES = 3;
    private final static int TOT_TH = 10;

    public static void aquireSource(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void releaseSource(Semaphore semaphore) {
        semaphore.release();
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(TOT_SOURCES);
        for(int i = 1;i<=TOT_TH;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(semaphore.getQueueLength());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }

}
