package com.tess.demo;

import com.tess.bean.Person;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Author:   Sean
 * Create:   3/25/2020 11:22 PM
 * 四大函数式接口
 *
 */
public class StreamDemo {
    public static void main(String[] args) {
        /*Function<String,Integer> func = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 1024;
            }
        };*/
       /* Function<String,Integer> func = s->{
            return 1024;
        };
        Predicate<String> predicate = s->{
            return s.isEmpty();
        };

        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        Supplier<String> supplier = ()->{return "a";};

        System.out.println(func.apply("1"));
        System.out.println(predicate.test("111"));
        consumer.accept("test");
        System.out.println(supplier.get());*/
        Person person1= new Person(1, "B", 14);
        Person person2= new Person(2, "A", 16);
        Person person3= new Person(3, "c", 15);
        Person person4= new Person(4, "d", 13);
        Person person5= new Person(5, "f", 17);
        Person person6= new Person(6, "e", 12);
        ArrayList<Person> userList = new ArrayList<>();
        userList.add(person1);
        userList.add(person2);
        userList.add(person3);
        userList.add(person4);
        userList.add(person5);
        userList.add(person6);

        //Begin Stream calc
        userList.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(u->{return u.getAge()>=12;})
                .map(m->{return m.getName().toUpperCase();})
                .sorted((o1,o2)->o1.compareTo(o2))
                .forEach(System.out::println);
    }
}
