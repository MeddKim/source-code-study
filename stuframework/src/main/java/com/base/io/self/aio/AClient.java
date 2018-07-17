package com.base.io.self.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AClient {
    private AsynchronousSocketChannel channel;
    public static CountDownLatch latch;
    public ClientHandler clientHandler;

    public void init() throws IOException {
        this.channel = AsynchronousSocketChannel.open();
        this.clientHandler = new ClientHandler(channel);
        this.latch = new CountDownLatch(1);
    }

    public void start() throws InterruptedException {
        channel.connect(new InetSocketAddress("127.0.0.1",9003),this.clientHandler,this.clientHandler);
        latch.wait();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AClient client = new AClient();
        client.init();
        client.start();
        client.clientHandler.sendMsg("你好");
    }
}
