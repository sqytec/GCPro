package com.tess.train;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class Call implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" coming in...");
        TimeUnit.SECONDS.sleep(3);
        return "success";
    }
}

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Call call = new Call();
        FutureTask futureTask = new FutureTask(call);
        FutureTask futureTask2 = new FutureTask(call);
        new Thread(futureTask,"A").start();
        new Thread(futureTask2,"B").start();
        System.out.println("main done");
        String ret = (String)futureTask.get();
        String ret2 = (String)futureTask2.get();
        System.out.println("result:"+ret);

    }
}
