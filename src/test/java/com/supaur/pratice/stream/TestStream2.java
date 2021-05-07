package com.supaur.pratice.stream;

import com.supaur.pratice.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStream2 {
    //中间操作
    /*
    * 筛选与切片
    * filter-接收Lambda,从流中排除某些元素
    * limit-截断流，使其元素不超过给定数量
    * skip(n)--跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
    * distinct--筛选，通过流所生成元素的hashCode()和equals()去重重复元素
    * */
    List<Employee> list = Arrays.asList(
            new Employee(101,"张三",18,9999.99, Employee.Status.FREE),
            new Employee(102,"李四",59,6666.66, Employee.Status.FREE),
            new Employee(103,"王五",28,3333.33, Employee.Status.BUSY),
            new Employee(104,"赵六",8,7777.77, Employee.Status.BUSY),
            new Employee(105,"田七",38,5555.55, Employee.Status.VOCATION)
    );
    @Test
    public void test1(){
        //中间操作不会执行任何操作
        /**
         * 排序
         * sorted()-自然排序
         * sorted(comparator com)-定制排序
         */
        List<String> list1=Arrays.asList("ccc","aaa","bbb","ddd","eee");
        list1.stream().sorted().forEach(System.out::println);

        //Stream<Employee> stream = list.stream().filter((x)->x.getAge()>35);
        //终止操作:一次执行全部内容，即“惰性求值”
        //stream.forEach(System.out::println);
        list.stream().sorted((e1,e2)->{
            if (e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge()-e2.getAge();
            }
        }).forEach(System.out::println);
    }
    @Test
    public void test2(){
        list.stream().filter((x)->x.getSalary()>5000).skip(2).forEach(System.out::println);
    }
    /*
    * 映射:map-接收Lambda,将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其
    * 映射成一个新元素，flatMap-接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    * */
    @Test
    public void test(){
        List<String> list1=Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list1.stream().map((s)->s.toUpperCase()).forEach(System.out::println);
        list.stream().map(Employee::getAge ).forEach(System.out::println);
    }
}
