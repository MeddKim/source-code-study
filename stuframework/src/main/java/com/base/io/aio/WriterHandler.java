package com.base.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class WriterHandler implements CompletionHandler<Integer,ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public WriterHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch){
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        //完成全部数据的写入
        if(attachment.hasRemaining()){
            clientChannel.write(attachment,attachment,this);
        }else {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            clientChannel.read(readBuffer,readBuffer,new ReadHandler(clientChannel,latch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }
}
