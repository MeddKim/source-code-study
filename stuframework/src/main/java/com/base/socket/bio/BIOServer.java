package com.base.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    /**
     * 默认端口
     */
    private static int DEFAULT_PORT = 9002;
    /**
     * 单例的ServerSocket
     */
    private static ServerSocket server;

    public static void start(){
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int port){
        if(server != null){
            return;
        }
        try {
            server = new ServerSocket(port);
            System.out.println("[******]服务器已启动，线程为"+Thread.currentThread().getName()+"，端口号为："+port);
            Socket socket;

            while (true){
                socket = server.accept();
                new Thread(new BIOServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
