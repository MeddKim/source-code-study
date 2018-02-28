package wang.willard.base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 普通的socket服务端，创建过程分为三步
 *    1. 创建socketServer
 *    2. 调用accept方法
 *    3. 使用accpet方法返回的socket与客户端通讯
 */
public class Server {

    public static void main(String[] args) {
        try {
            //创建监听8080端口的服务
            ServerSocket server = new ServerSocket(8080);
            //监听请求
            Socket socket = server.accept();
            //通讯，读取数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveContent = reader.readLine();
            System.out.println("Received from client："+ receiveContent);
            //通讯，向客户端发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("recieved message");
            pw.flush();
            //关闭资源
            pw.close();
            reader.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
