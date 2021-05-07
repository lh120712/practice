package com.supaur.ThreadLocal;

/**
 * 需求:线程隔离
 * 在多线程并发的场景下，每个线程中的变量都是相互独立的
 * 线程A: 设置(变量1)  获取(变量1)
 * 线程B: 设置(变量2)  获取(变量2)
 */
public class MyDemo01 {
    private String content;
    ThreadLocal<String> t1=new ThreadLocal<>();
    private String getContent(){
        return t1.get();
    }
    private void setContent(String content){
        t1.set(content);
    }

    public static void main(String[] args) {
        MyDemo01 myDemo01=new MyDemo01();
        for (int i = 0; i < 100; i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    myDemo01.setContent(Thread.currentThread().getName()+"的数据");
                    System.out.println("==============");
                    System.out.println(Thread.currentThread().getName()+"======>"+myDemo01.getContent());
//                    Thread.currentThread().
                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
    }
}
