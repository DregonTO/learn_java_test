package learn.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //1.serverSelector负责轮询是否有新的连接，服务器端检测到新的连接之后，不在创建一个新的线程
        //而是直接将新连接绑定到clientSelector上，这样就不用while(true)
        Selector serverSelector = Selector.open();
        //2.clientSelector负责轮询连接是否有数据可读
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                //对应IO编程中服务器端启动
                ServerSocketChannel listenerChanel = ServerSocketChannel.open();
                listenerChanel.socket().bind(new InetSocketAddress(3333));
                listenerChanel.configureBlocking(false);
                listenerChanel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true){
                    //监测是否有新的连接，这里的1指的是阻塞的时间为1ms
                    if (serverSelector.select(1)>0){
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();

                            if (key.isAcceptable()){
                                    try {
                                        // (1) 每来一个新链接，不需要创建一个线程，而是直接注册到clientSelector
                                        SocketChannel clientChanel = ((ServerSocketChannel) key.channel()).accept();
                                        clientChanel.configureBlocking(false);
                                        clientChanel.register(clientSelector, SelectionKey.OP_READ);
                                    } finally {
                                        keyIterator.remove();
                                    }
                            }
                        }
                    }
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true){
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
                    if (clientSelector.select(1)>0){
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();

                            if (key.isReadable()){
                                try {
                                    SocketChannel clientChanel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    // (3) 面向 Buffer
                                    clientChanel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
