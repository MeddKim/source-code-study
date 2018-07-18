package com.base.socket.aio;

import java.util.Scanner;

public class AIOClient {

    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 9004;
    private static AsyncClientHandler clientHandler;

    public static void start(){

    }

    public static synchronized void start(String ip, int port){
        if(clientHandler != null)
            return;

        clientHandler = new AsyncClientHandler(ip,port);
        new Thread(clientHandler,"client").start();
    }

    /**
     * 向服务器发送消息
     * @param msg
     * @return
     */
    public static boolean sendMsg(String msg){
        if(msg.equals("q"))
            return false;
        clientHandler.sendMsg(msg);
        return true;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        AIOClient.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(AIOClient.sendMsg(scanner.nextLine()));
    }
}
