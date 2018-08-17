package com.base.thread.four;

import com.base.thread.SleepUtils;

/**
 * 演示Deamon线程
 */
public class Deamon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DeamonRunner(),"DeamonRunner");
        thread.setDaemon(true);
        thread.start();
    }
    static class DeamonRunner implements Runnable{
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            }finally {
                System.out.println("DeamonThread finally run.");
            }

        }
    }
}
