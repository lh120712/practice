package com.supaur.ThreadLocal;

/**
 * 需求:线程隔离
 * 在多线程并发的场景下，每个线程中的变量都是相互独立的

 */
public class MyDemo02 {
    private String content;
    private String getContent(){
        return this.content;
    }
    private void setContent(String content){
        this.content=content;
    }

    public static void main(String[] args) {
        MyDemo02 myDemo02=new MyDemo02();
        for (int i = 0; i < 5; i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (MyDemo02.class) {
                        myDemo02.setContent(Thread.currentThread().getName() + "的数据");
                        System.out.println("==============");
                        System.out.println(Thread.currentThread().getName() + "======>" + myDemo02.getContent());
                    }
                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
    }
}
