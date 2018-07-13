package com.base.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        //继续接收其他客户端的请求
        AIOServer.clientCount ++;
        System.out.println("连接的客户端数量为："+ AIOServer.clientCount);
        serverHandler.channel.accept(serverHandler,this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //异步读取，第三个参数为接收到消息后的回调业务handler

    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler attachment) {

    }
}
