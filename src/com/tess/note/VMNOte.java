package com.tess.note;

/**
 * @author Sean
 * 主题：JVM
 *
 * 1.JVM系统架构图
 *
 * 2.类加载器
 * System.out.ptintln(myObj.getClass().getClassLoader().getParent().getParent())
 * System.out.ptintln(myObj.getClass().getClassLoader().getParent())
 * System.out.ptintln(myObj.getClass().getClassLoader())
 *
 *
 *      2.1有哪几种加载器
 *      2.2双亲委派
 *      2.3沙箱安全机制
 *
 *  3 Native
 *      3.1native是一个关键字
 *      3.2声明有，实现无，原因？
 *
 *  4.PC寄存器
 *      4.1记录了方法之间的调用和执行情况，类似排班值日表
 *      用来存储指向吓一跳指令的地址，也即将要执行的指令代码
 *      它是当前线程所执行的字节码的行号指示器
 *  5.方法区
 *      供各线程共享的运行时内存区域。它存储了每一个类的结构信息，
 *      例如运行时常量池(Runtime Constant Pool)、字段和方法数据、
 *      构造函数和普通方法的字节码内容。上面讲的是规范，在不同虚拟机里头实现是
 *      不一样的，最经典的就是永久代(PermGen space)和元空间(Metaspace)。
 *
 *      5.1它存储了每一个类的结构信息
 *      5.2方法区是规范，在不同虚拟机里头实现是不一样的，
 *          最经典的就是永久代(PermGen space)和元空间(Metaspace)。
 *  6.stack
 *      6.1栈管运行，堆管存储
 *      6.2保存哪些内容
 *          8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配
 *
 *      6.3Exception in thread "main" java.lang.StackOverflowError
 *
 *  7.heap
 *
 *
 *  8.heap----->对象的生命周期----->OOM
 *
 *
 */
public class VMNOte {
}
