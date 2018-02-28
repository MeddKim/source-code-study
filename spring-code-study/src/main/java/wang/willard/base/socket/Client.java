package wang.willard.base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * socket客户端，连接无服务端过程如下
 *   1. 创建socket
 *   2. 指定服务主机地址和端口
 *   3. 使用socket创建流传递数据
 */
public class Client {
    public static void main(String[] args) {
        String msg = "this is message from client";

        try {
            Socket socket = new Socket("127.0.0.1",8080);
            //使用socket创建流传递数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
            pw.println(msg);
            pw.flush();
            //接收数据
            String returnMsg = is.readLine();
            System.out.println("Server returned msg " + returnMsg);
            //关闭资源
            pw.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
