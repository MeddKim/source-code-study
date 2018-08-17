package com.base.thread.four;


import com.base.thread.SleepUtils;

/**
 * 演示 ThreadLocal
 */
public class Profileer {
    private static final ThreadLocal<Long> TIME_THREDLOCAL = new ThreadLocal<Long>();

    protected Long initialValue(){
        return System.currentTimeMillis();
    }

    public static final void begin(){
        TIME_THREDLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end(){
        return System.currentTimeMillis() - TIME_THREDLOCAL.get();
    }

    public static void main(String[] args) {
        Profileer.begin();
        SleepUtils.second(1);
        System.out.println("Cost :" + Profileer.end() + " mills");
    }
}
