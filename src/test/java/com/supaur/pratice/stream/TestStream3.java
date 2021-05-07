package com.supaur.pratice.stream;

import com.supaur.pratice.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 终止操作
 */
public class TestStream3 {
    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否至少匹配一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意元素
     * count-返回流中元素总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */
    List<Employee> list = Arrays.asList(
            new Employee(101,"张三",18,9999.99, Employee.Status.FREE),
            new Employee(102,"李四",59,6666.66, Employee.Status.FREE),
            new Employee(103,"王五",28,3333.33, Employee.Status.BUSY),
            new Employee(104,"赵六",8,7777.77, Employee.Status.FREE),
            new Employee(105,"田七",38,5555.55, Employee.Status.VOCATION)
    );
    @Test
    public void test1(){
        boolean isAllFree = list.stream().allMatch((x) -> x.getStatus().equals(Employee.Status.FREE));
        System.out.println(isAllFree);

        Optional<Employee> first = list.stream().sorted((e1, e2)
                -> ((Double) e1.getSalary()).compareTo(e2.getSalary())).findFirst();
        System.out.println(first.get());

        Optional<Employee> any = list.stream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(any.get());
        Optional<Employee> max = list.stream().max((e1, e2) -> ((Double) e1.getSalary()).compareTo(e1.getSalary()));
        Employee employee = max.get();
        System.out.println(employee);
        Optional<Double> min = list.stream().map(Employee::getSalary).min(Double::compareTo);
        System.out.println(min.get());
    }
    /**
     * 归约:
     * reduce(T identity,BinaryOperator)/reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值
     * map和reduce的连接通常称为map-reduce模式，因Goole用它来进行网络搜索而出名
     */
    @Test
    public void test2(){
        List<Integer> list1=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list1.stream().reduce(8, (x, y) -> x + y);
        System.out.println(reduce);

        Optional<Double> reduce1 = list.stream().map(Employee::getSalary).reduce((Double::sum));
        System.out.println(reduce1.get());

    }
    /**
     * 收集
     * collect--将流转换为其他形式，接收一个collector接口的实现,用于给stream中元素做汇总的方法
     */
    @Test
    public void test4(){
        List<String> collect = list.stream().map(Employee::getName).collect(Collectors.toList());
       collect.forEach(System.out::println);
        Set<String> collect1 = list.stream().map(Employee::getName).collect(Collectors.toSet());
        collect1.forEach(System.out::println);
        HashSet<String> collect2 = list.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);
        //总数
        Long collect3 = list.stream().collect(Collectors.counting());
        System.out.println(collect3);
        Double collect4 = list.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect4);
        //总和
        Double collect6 = list.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect6);
        //最大值
        Optional<Employee> collect5 = list.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect5.get());
        //最小值
        Optional<Double> collect7 = list.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println(collect7.get());
    }
    //分组
    @Test
    public void test3(){
        Map<Employee.Status, List<Employee>> collect = list.stream().collect(Collectors.groupingBy(Employee::getStatus));
    }
    //多级分组
    @Test
    public void test5(){
        Map<Employee.Status, Map<String, List<Employee>>> collect = list.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
            if (e.getAge() <= 35) {
                return "青年";
            } else {
                return "老年";
            }
        })));
        System.out.println(collect);
        //分区
        Map<Boolean, List<Employee>> collect1 = list.stream().collect(Collectors.partitioningBy((e) ->
                e.getSalary() > 5000
        ));
        System.out.println(collect1);
    }


}
