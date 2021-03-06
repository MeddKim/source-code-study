package com.smartio;

import org.smartboot.socket.transport.AioQuickServer;

import java.io.IOException;

public class IntegerServer {
    public static void main(String[] args) throws IOException {
        AioQuickServer<Integer> server = new AioQuickServer<Integer>(8888, new IntegerProtocol(), new IntegerServerProcessor());
        server.start();
    }
}