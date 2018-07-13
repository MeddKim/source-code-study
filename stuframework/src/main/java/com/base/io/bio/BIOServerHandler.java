package com.base.io.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BIOServerHandler implements Runnable{

    private Socket socket;

    public BIOServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {

            System.out.println(">>>>>有客户端接入，线程"+Thread.currentThread().getName()+"负责处理");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            String result;

            while(true){
                System.out.println(in.readLine());
                if((expression = in.readLine()) == null)
                    break;
                System.out.println("服务器接收到消息："+expression);
                result = Thread.currentThread().getName() + "接收到消息了："+expression;
                out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }

}
