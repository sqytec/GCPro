package com.tess.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sean
 * @date 2020/03/18
 * <p>
 * 1.故障现象
 * java.util.ConcurrentModificationException
 * 2.导致原因
 *  并发下多线程争抢同一资源，未加锁
 * <p>
 * 3.解决方法
 * 3.1 使用Vector(不推荐)
 * 3.2 Collections.synchronizedList(new ArrayList<>()) Collections中出现的都是现成不安全
 * 3.3 new CopyOnWriteArrayList<>()
 * <p>
 * 4.优化建议(同样的错误不能犯2次)
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        List<String> arr = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                arr.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(arr);
            }, String.valueOf(i)).start();
        }
    }
}



    /*
    写时复制
    CopyOnWrite容器即写时复制容器。往一个容器中添加元素时，不直接往当前容器Object[]添加，
    而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements里添加元素，添加完元素之后，再将原容器的引用指向新的容器setArray(newElements);
    这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
    所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器

    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }*/
