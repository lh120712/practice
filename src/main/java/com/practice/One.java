package com.practice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * countDownLatch和CyclicBarrier区别
 * 1、countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能用一次
 * 2、CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用
 *
 * countDownLatch
 * public countDownLatch(int count){}//count为计数器  countDownLatch只提供了这一个构造器
 * public void await() throws InterruptedException//调用await()方法的线程会被挂起，它会等待直到count为0才继续执行
 * public boolean await(long timeout,TimeUnit unit) throws InterruptedException{};//和await类似，
 * 只不过等待一定时间后count值还没变为0的话就继续执行
 * public void countDown()//将count值减1
 */
public class One {

    public static void main(String[] args) {

        final CountDownLatch latch=new CountDownLatch(2);
        System.out.println("主线程开始执行.......");
        ExecutorService es1= Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程: "+Thread.currentThread().getName()+"执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        //第二个子线程执行
        ExecutorService es2=Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行");
                latch.countDown();
            }
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕....");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}
