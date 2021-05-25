package com.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存穿透  本身就不存在的缓存    1、使用布隆过滤器判断缓存中是否存中 2、为请求设置一个值为null的缓存，设置较短的过期时间
 * <p>
 * 缓存击穿  缓存过期失效  1、热点数据的缓存永不过期  2、使用分布式锁，缓存失效后只有一个线程更新并写入
 * <p>
 * 缓存雪崩  大面积的缓存击穿或服务不可用  使用Redis哨兵或集群等架构提升可用性 使用和缓存击穿一样的方式  错开缓存数据的过期时间点，防止缓存大面积失效
 * <p>
 * 单机模式:一台redis服务器   主从模式: 主从redis服务器 主负责写 从负责读  哨兵模式: 主从模式 哨兵监控主服务器 主服务器失效后转为从服务器
 * 集群模式:多个主从服务器
 * <p>
 * 全量复制:主服务器---从服务器   从服务器发送sync命令  主服务器bgsave RDB 缓存区命令写到从服务器
 * 增量复制:
 */
public class penetrate {
    public static void main(String[] args) {
//        String s1="zs";
//        String s2="zs";
//        String s3=new String("zs");
//        String s4=new String("zs");
//        String s5="zszs";
//        String s6=s1+s2;
//        System.err.println(s5==s6);
//        System.out.println(s3==s4);
//        System.out.println(s3.equals(s4));
//        System.out.println(s1==s2);
        for (int j=0;j<100;j++){
            new Thread(new RunnableTest()).start();
        }
    }

}

class RunnableTest implements Runnable {
    Map<Integer, String> hashMap = new HashMap<>();
    int count =0;
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            hashMap.put(i,"Go for it");
            count++;
            System.out.println("Go for it");
        }
        System.out.println(count);
    }

}
