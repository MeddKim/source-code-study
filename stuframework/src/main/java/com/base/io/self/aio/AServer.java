package com.base.io.self.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class  AServer {

    public AsynchronousServerSocketChannel channel;
    public volatile static long clientCount = 0;
    public CountDownLatch latch;

    public void init() throws IOException {
        //创建服务端通道
        this.channel = AsynchronousServerSocketChannel.open();
        //绑定端口
        channel.bind(new InetSocketAddress(9003));
        this.latch = new CountDownLatch(1);
    }

    public void start(){
        //接收
        channel.accept(this,new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        AServer server = new AServer();
        server.init();
        server.start();
    }

}



/**
 *  CountDownLatch 并发辅助类，他的三个方法分别是
 *  await()   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 *  await(long timeout, TimeUnit unit) //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 *  countDown() { };  //将count值减1
 **/
