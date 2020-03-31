package com.tess.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
//                    semaphore.acquire(3);
                    semaphore.tryAcquire(4);
                    System.out.println("线程" + Thread.currentThread().getName() + " 获得资源");
                    System.out.println("空闲资源:"+semaphore.availablePermits());
//                    TimeUnit.SECONDS.sleep(3);
                } finally {
//                    semaphore.release(3);
                    semaphore.release(4);
                }
            }, String.valueOf(i)).start();
        }
    }
}
