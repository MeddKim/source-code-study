package com.base.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerHandler implements Runnable{
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;


    public NIOServerHandler(int port){
        try {
            //创建选择器
            selector = Selector.open();
            //打开监听通道
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false); //开启非诸塞模式
            //绑定端口，backlog设置为1024
            serverChannel.socket().bind(new InetSocketAddress(port),1024);
            //监听客户端连接请求
            serverChannel.register(selector,SelectionKey.OP_ACCEPT);
            //标记服务器已经开启
            started = true;
            System.out.println("服务器已经启动，端口号为："+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        started = false;
    }

    @Override
    public void run() {

        while (started){
            //无论是否有读写事件发生，selector每隔1秒被唤醒一次
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                //通过ServerSocketChannel的accept创建SocketChannel实例
                //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                SocketChannel sc = ssc.accept();
                //设为非阻塞的
                sc.configureBlocking(false);
                //注册为读
                sc.register(selector,SelectionKey.OP_READ);
            }
            //读消息
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel)key.channel();
                //创建ByteBuffer，并开辟1M缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取
                int readBytes = sc.read(buffer);
                //读取到字节，对字节进行编解码
                if(readBytes > 0){
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建数组中
                    buffer.get(bytes);
                    String content = new String(bytes,"UTF-8");
                    System.out.println("服务端接收到消息："+content);

                    //回复
                    String result = "我是服务器，接收到你的消息：" + content;
                    doWrite(sc,result);
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        byte[] bytes = response.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送
        channel.write(writeBuffer);
    }
}


/***********************************************
1. 打开ServerSocketChannel，监听客户端连接
2. 绑定监听端口，设置连接为非阻塞模式
3. 创建Reactor线程，创建多路复用器并启动线程
4. 将ServerSocketChannel注册到Reactor线程中的Selector上，监听ACCEPT事件
5. Selector轮询准备就绪的key
6. Selector监听到新的客户端接入，处理新的接入请求，完成TCP三次握手，简历物理链路
7. 设置客户端链路为非阻塞模式
8. 将新接入的客户端连接注册到Reactor线程的Selector上，监听读操作，读取客户端发送的网络消息
9. 异步读取客户端消息到缓冲区
10. 对Buffer编解码，处理半包消息，将解码成功的消息封装成Task
11. 将应答消息编码为Buffer，调用SocketChannel的write将消息异步发送给客户
 ***********************************************/