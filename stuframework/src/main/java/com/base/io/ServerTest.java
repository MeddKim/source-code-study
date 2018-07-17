package com.base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerTest{

    public static void main(String[] args) throws IOException {
        /**
         * 初始化操作
         */
        //创建选择器
        Selector selector = Selector.open();
        //打开监听信道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //开启非阻塞模式
        serverChannel.configureBlocking(false);
        //绑定端口 backblog设为1024
        serverChannel.socket().bind(new InetSocketAddress(9002),1024);
        //监听客户端连接请求
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);


        while (true){
            //无论是否有读写事件发生，selector每隔一秒唤醒一次
            selector.select(1000);
            //selector.select();//阻塞，只有当至少一个注册事件发生时才继续
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            SelectionKey key = null;
            while (it.hasNext()){
                key = it.next();
                it.remove();
                try {
                    handleInput(selector,key);
                }catch (IOException e){
                    key.channel().close();
                }
            }
        }
    }

    private static void handleInput(Selector selector, SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                //设置为非阻塞
                sc.configureBlocking(false);
                //注册为只读
                sc.register(selector,SelectionKey.OP_ACCEPT);
            }
        }
        //读消息
        if(key.isReadable()){
            SocketChannel sc = (SocketChannel) key.channel();
            //创建ByteBuffer，并开辟一个1M的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取请求码流，返回读取到的字节数
            int readBytes = sc.read(buffer);
            //读取到字节，对字节进行编解码
            if(readBytes>0){
                //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                buffer.flip();
                //根据缓冲区可读字节数创建字节数组
                byte[] bytes = new byte[buffer.remaining()];
                //将缓冲区可读字节数组复制到新建的数组中
                buffer.get(bytes);
                String expression = new String(bytes,"UTF-8");
                System.out.println("服务器收到消息：" + expression);
            }
            //没有读取到字节 忽略
//              else if(readBytes==0);
            //链路已经关闭，释放资源
            else if(readBytes<0){
                key.cancel();
                sc.close();
            }
        }
    }
}
