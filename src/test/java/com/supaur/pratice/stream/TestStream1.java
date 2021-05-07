package com.supaur.pratice.stream;

import com.supaur.pratice.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
* 一、Stream的三个操作步骤:
* 1、创建Stream
* 2、中间操作
* 3、终止操作(终端操作)
* */
public class TestStream1 {
    //创建Stream
    @Test
    public void test1(){
        //可以通过collection系列集合提供的stream()或parallelStream()
        List<String> list=new ArrayList<>();
        Stream<String> stram1=list.stream();
        //通过Arrays中的静态方法stream()获取数组流
        Employee[] employees=new Employee[10];
        Stream<Employee> stream = Arrays.stream(employees);
        //通过Stream类中的静态方法of()
        Stream<String> aa = Stream.of("aa", "bb", "cc");
        //创建无线流
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, (x -> x + 2));
        iterate.limit(10).forEach(System.out::println);
        //生成
        Stream<Double> generate = Stream.generate(() -> Math.random());
        generate.limit(10).forEach(System.out::println);
    }
}
