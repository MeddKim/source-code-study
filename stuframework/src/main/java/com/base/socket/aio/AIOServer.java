package com.base.socket.aio;

public class AIOServer {
    private static int DEFAULT_PORT = 9004;
    private static AsyncServerHandler serverHandler;
    public volatile static long clientCount = 0;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if(serverHandler != null){
            return;
        }
        serverHandler = new AsyncServerHandler(port);
        new Thread(serverHandler,"Server").start();
    }

}
