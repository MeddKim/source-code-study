package com.base.socket.self.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9001);

        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        }

    }

    private static class ServerHandler implements Runnable{

        private Socket socket;

        public ServerHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                //获取socket输入输出流
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (true){
                    //接收客户端信息
                    String msg = reader.readLine();
                    System.out.println("来自客户端"+socket.getInetAddress()+"："+msg);
                    //向客户端发送信息
                    String reponse = Thread.currentThread().getName()+"自动返回："+msg+"\n";
                    writer.write(reponse);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
