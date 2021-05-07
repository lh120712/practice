package com.supaur.pratice.stream;

import org.junit.Test;
import sun.security.jca.GetInstance;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 并行流与顺序流
 * 并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流
 * java8中将并行了优化，我们可以很容易的对数据进行并行操作，Stream API可以声明性地通过parallel()与sequential()在并行流
 * 与顺序流中切换
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = -7971757420536802685L;

    @Override
    protected Long compute() {
        return null;
    }
    @Test
    public void test(){
        Instant start=Instant.now();
        LongStream.rangeClosed(0,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end=Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }
}
