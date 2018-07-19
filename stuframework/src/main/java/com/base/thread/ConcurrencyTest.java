package com.base.thread;


public class ConcurrencyTest {
    private static final long count = 100001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
//        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(()->{
            int a = 0;
            for(long i = 0; i < count; i++){
                a = a + 5;
            }
        });
        thread.start();
        int b = 0;
        for(long i = 0; i < count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        // thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
        // 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
        thread.join();  //保证该线程执行完成后，主线程才去执行serial方法
        System.out.println("concurrency :" + time + "ms, b = "+ b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0; i < count; i++){
            a = a + 5;
        }
        int b = 0;
        for(long i = 0; i < count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial :" + time + "ms, b = "+ b+", a= "+a);
    }
}
