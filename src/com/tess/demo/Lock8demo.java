package com.tess.demo;

import java.util.concurrent.TimeUnit;

class Phone {
    public synchronized static void senMail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("************sendMail");
    }

    public synchronized void senSMS() {
        System.out.println("************sendSMS");
    }

    public void sayHello() {
        System.out.println("************Sayhello");
    }
}

/**
 * @author Sean
 * @date 2020/03/18
 * <p>
 * 8 lock
 * 1.标准访问，先访问邮件还是短信                                 不确定
 * 2.暂停4秒钟在邮件方法，先打印邮件还是短信                        邮件，同一个对象锁，邮件方法先访问，且暂停4秒，需要释放这个资源后，才能发短信
 * 3.新增SayHello方法，先打印邮件还是hello                       hello,资源类中的非同步方法可以自由调用
 * 4.两部手机，先打印邮件还是短信                                 先短信，不同对象，不同资源
 * 5.两个静态同步方法，同一部手机，先打印邮件还是短信                  邮件，静态同步方法是全局锁，class层次
 * 6.两个静态同步方法，两部手机，先打印邮件还是短信                    邮件，class层次
 * 7.一个静态同步方法，一个普通方法，一部手机，先邮件还是短信           短信，两种锁没有竞态关系
 * 8.一个静态同步方法，一个普通方法，两部手机，先邮件还是短信           短信，两种锁没有竞态关系
 */
public class Lock8demo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.senMail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        Thread.sleep(100);
        new Thread(() -> {
            phone2.senSMS();
//            phone.sayHello();
        }, "B").start();
    }
}




/*
    笔记
		一个对象里面如果有多个Synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了,
		其它的线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些Synchronized方法
		
		锁的是当前对象this，倍锁定后，其他的线程都不能进入到当前对象的其它的synchronized方法
		
		
		加一个普通方法后发现和同步锁无关
		换成两个对象后，不是同一把锁了，情况立刻变化
		
		
		都换成静态同步方法后，情况又变化
		所有的非静态同步方法用的都是同一把锁----示例对象本身
		synchronized实现同步的基础:Java中的每一个对象都可以作为锁。
		具体表现为以下三种形式
			对于普通同步方法，锁是当前实例对象，锁的是当前对象this
			对于同步方法块，锁是synchronized括号里配置的对象
			
			对于静态同步方法，锁是当前类的class对象
		
		当一个线程试图访问同步代码块时，它首先必须得到锁，退出或者抛出异常时必须释放锁。
		也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其它非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
		可是别的实例对象的非静态方法因为跟该实例对象的非静态方法用的不是不同锁，
		所以无需等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
		
		所有的静态同步方法用的也是同一把锁----类对象本身，
		这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
		但是一旦一个静态同步方法获取锁后，其它的静态同步方法都必须等待该方法释放锁才能获取锁，
		而不管是同一个实例对象的静态同步方法之间，
		还是不同的实例对象的静态同步方法之间，只要他们同一个类的实例对象.
		
 */