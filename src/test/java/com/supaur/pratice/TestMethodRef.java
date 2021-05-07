package com.supaur.pratice;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class TestMethodRef {
    //方法引用的第一种方式 对象::实例方法名
    @Test
    public void test1(){
        PrintStream p1=System.out;
        Consumer<String> con=(x)->p1.println(x);
        PrintStream p=System.out;
        Consumer<String> con1=p::println;
        con1.accept("abcdef");

    }
    @Test
    public void test2(){
        Employee employee=new Employee();
        Supplier<String> sp=()->employee.getName();
        String str=sp.get();
        System.out.println(str);
        Supplier<Integer> sp1=employee::getAge;
        Integer i=sp1.get();
        System.out.println(i);
    }
    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> comparator=(x,y)->Integer.compare(x,y);

        Comparator<Integer> comparator1=Integer::compare;
    }
    //类::实例方法名 第一个参数是实例方法的调用者，第二个参数是实例方法的参数 可以使用ClassName::method
    @Test
    public void test4(){
        BiPredicate<String,String> bp=(x,y)->x.equals(y);

        BiPredicate<String,String> bp2=String::equals;
    }
    @Test
    public void test5(){

        Supplier<Employee> sup1=()->new Employee();
        //构造器引用方式
        Supplier<Employee> sup2=Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);
    }
    //数组引用
    @Test
    public void test(){
        Function<Integer,String[]> fun=(x)->new String[10];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);
        Function<Integer,String[]> fun1=String[]::new;
        String[] apply1 = fun1.apply(10);
        System.out.println(apply1.length);
    }

}
