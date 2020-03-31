package com.tess.interview;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author:   Sean
 * Create:   3/30/2020 2:58 PM
 */

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 25);
        User lisi = new User("lisi", 22);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zhangsan);
        System.out.println(atomicReference.compareAndSet(zhangsan,lisi)+" \t "+atomicReference.get());
        System.out.println(atomicReference.compareAndSet(zhangsan,lisi)+" \t "+atomicReference.get());
    }
}
