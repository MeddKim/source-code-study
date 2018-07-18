package com.base.socket.self.bio;

import com.base.socket.NetIOUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9002);
        //获取socket输入输出流
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Scanner scanner = new Scanner(System.in);

        while (true){
            //像服务端发送信息
            String request = scanner.nextLine();
            if(request.equals("q")){
                NetIOUtils.close(reader,writer,socket);
            }
            writer.write(request+"\n");
            writer.flush();
            //接收服务端信息
            String msg = reader.readLine();
            System.out.println("来自服务端的信息："+msg);
        }
    }
}
