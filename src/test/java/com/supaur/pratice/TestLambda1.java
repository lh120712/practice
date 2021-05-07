package com.supaur.pratice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8内置的四大核心的函数式接口
 *
 * Consumer<T>:消费型接口
 *          void accept(T t);
 * Supplier<T>:供给型接口
 *      T get();
 *
 * Function<T,R>:函数型接口
 *          R apply(T t);
 * Predicate<T>:断言型接口
 *          boolean test(T t);
 */
public class TestLambda1 {
    //Predicate<T> 断言型接口:
    //需求:将满足条件的字符串放入集合中,放入集合中
    @Test
    public void test4(){
        List<String> strings = Arrays.asList("aaaaa", "bbb", "cc", "ddddd");
        List<String> strings1 = filterStr(strings, (s) -> s.length() > 3);
        for (String string:strings1) {
            System.out.println(string);
        }
    }
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> list1=new ArrayList<>();
        for (String str:list) {
            if (predicate.test(str))
                list1.add(str);
        }
        return list1;
    }
    //Function<T,R>函数型接口:
    @Test
    public void test3(){
        String newStr=strHandler("\t\t\t 威武   ",(str)->str.trim());
        System.out.println(newStr);
    }
    public String strHandler(String str, Function<String,String> function){
        String apply = function.apply(str);
        return apply;
    }
    //Supplier<T>攻击型接口:
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer :numList) {
            System.out.println(integer);
        }
    }
    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < num ; i++) {
            Integer n=supplier.get();
            list.add(n);
        }
        return list;
    }
    //Consumer<T>消费型接口
    @Test
    public void test1(){
        happy(100,(m)-> System.out.println("消费了"+m+"元"));
    }
    public void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }
}
