package com.supaur.pratice;

import com.sun.org.apache.bcel.internal.generic.LineNumberGen;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 一、Lambda 表达式的基础语法:Java8引入新的操作符"->" 该操作符称为箭头操作符或Lambda操作符，箭头操作符将Lambda表达式拆分成两部分
 * 函数式接口:接口中只有一个抽象方法的接口
 * 左侧：Lambda 表达式的参数列表
 * 右侧:Lambda 表达式中所需要执行的功能，即Lambda体
 * <p>
 * 语法格式一:无参数，无返回值
 * () -> System.out.println("Hello Lambda!")
 * 语法格式二:有一个参数，无返回值  Consumer<String> con=(x) -> System.out.println(x);con.accept("数跑科技");
 * 语法格式三:若只有一个参数，小括号可以省略不写  x->System.out.println(x)
 * 语法格式四:有两个以上的参数，有返回值，并且Lambda体中有多条语句 Comparator<Integer> com=<x,y>->{
 *     System.out.println("函数式接口");
 *     return Integer.compare(x,y);
 * };
 * 语法格式五:若Lambda体中只有一条语句，return和大括号都可以省略不写 Comparator<Integer> com=(x,y)->Integer.compare(x,y);
 * 语法格式六:Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * (Integer x,Integer y)->Integer.compare(x,y);
 *
 * 二:Lambda表达式需要“函数式接口”的支持
 * 函数式接口:接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface 修饰
 *      可以检查是否是函数式接口
 *
 *
  */
public class TestLambda {
    @Test
    public void test1() {

        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Hello World!");
            }
        };
        r.run();
        System.out.println("======================");
        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();
    }

    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("数跑科技");
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }
    @Test
    public void test4(){
        Comparator<Integer> com =(x,y)->Integer.compare(x,y);
    }
    @Test
    public void test5(){
        Integer num=operation(100,(x)->x*x);
        System.out.println(num);
    }
    public Integer operation(Integer num,MyFunction myFunction){
        return myFunction.getValue(num);
    }
    List<Employee> list = Arrays.asList(
            new Employee(101,"张三",18,9999.99, Employee.Status.FREE),
            new Employee(102,"李四",59,6666.66, Employee.Status.FREE),
            new Employee(103,"王五",28,3333.33, Employee.Status.BUSY),
            new Employee(104,"赵六",8,7777.77, Employee.Status.BUSY),
            new Employee(105,"田七",38,5555.55, Employee.Status.VOCATION)
    );
    @Test
    public void test6(){
        Collections.sort(list,(e1,e2)->{
            if (e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for (Employee employee:list) {
            System.out.println(employee);
        }
    }
    @Test
    public void test7(){
        String strHandler = strHandler("\t\t\t 威武   ", (str) -> str.trim());
        System.out.println(strHandler);
    }
    public String strHandler(String str,MyFunction01 myFunction){
        return myFunction.getValue(str);

    }
    @Test
    public void test8(){
        op(100L,100L,(x,y)->(Long)x+(Long) y);
    }
    public void op(Long l1,Long l2,MyFunction2 myFunction2){
        System.out.println(myFunction2.getValue(l1,l2));
    }
}

