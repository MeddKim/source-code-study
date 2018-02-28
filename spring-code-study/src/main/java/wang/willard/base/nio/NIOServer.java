package wang.willard.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 *  nio通讯中,ServerSocketChanel相当与传统的ServerSocket，使用流程如下
 *  1. 创建ServerSocketChannel并设置参数
 *  2. 创建Selector并注册到ServerSocketChannel上
 *  3. 调用Selector的select方法等待请求
 *  4. Selector接收到请求后使用selectedKeys返回selectionKey集合
 *  5. 使用selectionKey获取Channel、Selector和操作类型进行具体操作
 *
 *  tip：启动该服务后，在浏览器输入http//:localhost:8080能够打印出http信息
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //1
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        //设置非阻塞模式
        ssc.configureBlocking(false);
        //注册选择器
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //创建处理器
        Handler handler = new Handler(1024);
        while (true){
            //等待请求，每次等待阻塞3秒，超过3s后线程继续运行，若传入0或者不传则将一直阻塞
            if(selector.select(3000) == 0){
                System.out.println("等待请求超时");
                continue;
            }
            System.out.println("处理请求");
            //获取待处理的SelectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                try {
                    //接收到连接请求时
                    if(key.isAcceptable()){
                        handler.handleAccept(key);
                    }
                    //读数据
                    if(key.isReadable()){
                        handler.handleRead(key);
                    }
                } catch (IOException e) {
                    keyIter.remove();
                    continue;
                }
                //处理完成后，重待处理的SelectionKey迭代器中移除当前所使用的Key
                keyIter.remove();
            }
        }
    }

    private static class Handler{
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
        private Handler(int bufferSize){
            this(bufferSize, null);
        }
        public Handler(String localCharset){
            this(-1, localCharset);
        }
        public Handler(int bufferSize,String localCharset){
            if(bufferSize > 0){
                this.bufferSize = bufferSize;
            }
            if(localCharset != null){
                this.localCharset = localCharset;
            }
        }

        public void handleAccept(SelectionKey key) throws IOException {
            SocketChannel sc = ((ServerSocketChannel)key.channel()).accept();
            sc.configureBlocking(false);
            sc.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead(SelectionKey key) throws IOException {
            //获取channel
            SocketChannel sc = (SocketChannel)key.channel();
            //获取buffer并重置
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            buffer.clear();
            //没有读到内容则关闭
            if(sc.read(buffer) == -1){
                sc.close();
            }else{
                //将buffe转为读状态
                buffer.flip();
                //将buffer中接收到的值按localChaset格式编码并保存
                String recievedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                System.out.println("recieved from client：" + recievedString);
                //返回数据给客户端
                String sendString = "recieved data" + recievedString;
                buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));

                sc.write(buffer);
                //关闭socket
                sc.close();
            }
        }
    }
}


