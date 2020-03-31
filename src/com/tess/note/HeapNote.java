package com.tess.note;

import java.util.Random;

/**
 * @author Sean
 *
 * 新生区(简单版)
 *  新生区是类的诞生、成长、消亡的区域，一个类在这里产生，应用，最后被垃圾回收器收集，结束
 *  生命。新生区又分为两部分：伊甸区(Eden space)和幸存者区(Survivor space),所有的
 *  类都是在伊甸区被new出来的。幸存区有两个：0区(Survior 0 space)和1区(Survior 1 space)。
 *  当伊甸区的空间用完时，程序又需要创建对象，JVM的垃圾回收器将对伊甸区进行垃圾回收(Minor GC),
 *  将伊甸区中的不再被其它对象所引用的对象进行销毁。然后将伊甸区中剩余的对象移动到幸存0区。
 *  若幸存0区也满了，再对该区进行垃圾回收，然后移动到1区。若1区也满了，再移动到养老区。若养老
 *  区也满了，那么这时候将产生MajorGC(FullGC)，进行养老区的内存清理。若养老区执行了Full GC
 *  之后发现依然无法进行对象的保存，就会产生OOM异常(OutOfMemoryError)
 *  若出现java.lang.OutOfMemoryError:Java heap space异常，说明虚拟机的堆内存不够。原因有二:
 *  (1)java虚拟机的堆内存设置不够，可以通过参数-Xms、-Xmx来调整。
 *  (2)代码中创建了大量大对象，并且长时间不能被垃圾回收器收集(内在被引用)。
 *
 *
 *
 *                  MinorGC的过程(复制->清空->互换)
 *  1：eden、survivorFrom复制到SurvivorTo，年龄+1
 *      首先，当Eden区满的时候会触发第一次GC,把活着的对象拷贝到SurvivorFrom区，当Eden区再次触发GC的时候，
 *      会扫描Eden区和SurvivorFrom区，对这两个区域进行垃圾回收，经过这次回收后还存活的对象，会直接复制到To
 *      区域(如果有对象的年龄已经达到了老年的标准，则赋值到老年代区)，同时把这些对象的年龄+1
 *
 *  2：清空eden、SurvivorFrom
 *      然后，清空Eden和SurvivorFrom中的对象，也即复制之后有交换，谁空谁是to
 *
 *  3：SurvivorTo和SurvivorFrom互换
 *      最后，SurvivorTO和SurvivorFrom互换，原SurvivorTo成为下次GC时的SurvivorFrom区。部分对象会在From
 *      和To区域中复制来复制去，如此交换15次(由JVM参数MaxTenuringThreshold决定，这个参数默认是15)，最终如果
 *      还是存活，就存入到老年代.
 *
 *  在java8中，永久代已经被移除，被一个称为元空间的区域所取代。元空间的本质和永久代类似。
 *  元空间与永久代之间最大的区别在于:
 *  永久代使用的JVM的堆内存，但是java以后的元空间并不在虚拟机中而是使用本机物理内存
 *  因此，默认情况下，元空间的大小仅受本地内存限制。类的元数据放入native memory，字符串和
 *  类的静态变量放入java堆中，而由系统的实际可用空间来控制.
 *
 *  -Xms设置初始分配大小，默认为物理内存的 1/64
 *  -Xmx最大分配内存，默认为物理内存的1/4
 *  -XX:+PrintGCDetails输出详细的GC处理日志
 */
public class HeapNote {
    public static void main(String[] args) {
        /*long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中的内存总量
        System.out.println("MAX_memory="+maxMemory+"（字节）:"+(maxMemory/(double)1024/1024)+"MB");
        System.out.println("TOTAL_memory="+totalMemory+"（字节）:"+(totalMemory/(double)1024/1024)+"MB");*/
        byte[] bytes = new byte[40 * 1024*1024];
    }
}
