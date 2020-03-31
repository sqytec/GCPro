package com.tess.interview;

import jdk.nashorn.internal.ir.Block;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Author:   Sean
 * Create:   3/30/2020 8:56 PM
 * <p>
 * ArrayBlockingQueue:是一个基于数组结构的有限阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
 * LinkedBlockingQueue:一个基于链表结构的阻塞队列，此队列按FIFO（先进先出）排序元素，吞吐量通常要高于ArrayBlockingQueue
 * SynchronousQueue:一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高
 * <p>
 * <p>
 * <p>
 * <p>
 * 1.队列
 * <p>
 * 2阻塞队列
 * 2.1 阻塞队列有没有好的一面
 * <p>
 * 2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
//        exceptionCase();
//        speCase();
//        blockCase();
        blockDuringCase();
    }

    /**
     * 空间不足、元素不够时，将在有限时间内阻塞
     * @throws Exception
     */
    public static void blockDuringCase() throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a", 2L, TimeUnit.SECONDS);
        blockingQueue.offer("a", 2L, TimeUnit.SECONDS);
        blockingQueue.offer("a", 2L, TimeUnit.SECONDS);
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }

    /**
     * 空间不足、元素不够时，将会出现阻塞
     * put时队列将会一直阻塞生产者线程直到put数据or响应中断退出
     * take时队列会一直阻塞消费者线程直到队列可用
     */
    public static void blockCase() throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    /**
     * 空间不足、元素不够时，不出现异常，但此时offer的结果是false，poll的结果是nulll
     */
    public static void speCase() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

    }

    /**
     * 空间不足、元素不够时，add和remove将会出现异常
     */
    public static void exceptionCase() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
