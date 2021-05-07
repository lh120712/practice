package com.practice;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 来自于One的补充，countDownLatch在多线程下的使用
 *
 * newFixedThreadPool newCachedThreadPool newScheduleThreadPool newSingleThreadPool
 * newFixedThreadPool:定长线程池
 * newCachedThreadPool:可缓存线程池
 * newScheduleThreadPool:定长线程池，支持定时和延迟执行
 * newSingleThreadPool:一个单线程化线程池，唯一的工作线程执行任务
 */
public class Two {
    public static void main(String[] args) {
        ExecutorService pool= Executors.newCachedThreadPool();
        CountDownLatch cd1=new CountDownLatch(100);
        for (int i = 0; i <100 ; i++) {
            CountRunnable countRunnable=new CountRunnable(cd1);
            pool.execute(countRunnable);
        }
    }
}

class CountRunnable implements Runnable{

    private CountDownLatch countDownLatch;

    public CountRunnable(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run() {
        synchronized (countDownLatch){
            //计数器减一
            countDownLatch.countDown();
            System.out.println("thread counts="+countDownLatch.getCount());
        }
        try {
            countDownLatch.await();
            System.out.println("concurrency counts="+(100-countDownLatch.getCount()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * newFixedThreadPool 定长线程池 test
 * 每隔3秒连续输入三个
 */
class NewFixedThreadPoolTest{
    public static void main(String[] args) {
        /**
         * 创建一个可容纳3个线程的线程池 超出的线程会在队列中等待
         */
        ExecutorService fixedThreadPool=Executors.newFixedThreadPool(3);
        for (int i = 0; i <19 ; i++) {
            final int index=i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        fixedThreadPool.shutdown();
    }
}

/**
 * newCachedThreadPool 线程池为无限大 当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
 */
class CachedThreadPoolTest{
    public static void main(String[] args) {
        /**
         * 创建一个cachedThreadPool线程池
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index=i;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }

        executorService.shutdown();
    }
}


