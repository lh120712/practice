package com.bilibili.mashibing;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC  CAS(compare and set compare and swap)+AQS   lock comxchg (自旋锁)和jvm打交道 轻量级
 * synchronized 和操作系统打交道 重量级  上锁是改变了markword 不能多个 不能公平锁
 *
 * CAS=AQS+volitle
 */
public class JustTest {

    private static Long n=0L;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger=new AtomicInteger();
        Object o=new Object();
        synchronized (o){//并行变串行
            Thread[] threads=new Thread[100];
            CountDownLatch latch=new CountDownLatch(threads.length);

            for (int i = 0; i <threads.length ; i++) {  
                threads[i]=new Thread(()->{
                    for(int j=0;j<10000;j++) {
                        synchronized (JustTest.class) {
                            n++;
                        }
                    }
                    latch.countDown();
                });
                
            }
            for (Thread t:threads) {
                t.start();
            }
            latch.await();
            System.out.println(n);

        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
