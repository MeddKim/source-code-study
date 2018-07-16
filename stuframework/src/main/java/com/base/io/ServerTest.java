package com.base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ServerTest{

    public static void main(String[] args) throws IOException {
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

    }
}
