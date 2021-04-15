package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

class NIOSvr {

    private Thread server;

    private Selector selector;

    private String address = "localhost";

    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();


    private int port = 8888;

    public NIOSvr() throws IOException {
    }

    public NIOSvr(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
    }

    private void initializr() throws IOException {
        selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(address, port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // register accept event
    }

    private void start() throws IOException {
        System.out.println("server running!!!");
        while (true) {
            System.out.println("current size: " + selector.select());
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                selectionKeys.remove(selectionKey);
                if (selectionKey.isAcceptable()) {
                    register(selectionKey);
                }
                if (selectionKey.isReadable()) {
                    read(selectionKey);
                }
            }
            /*Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    register(selectionKey);
                }
                if (selectionKey.isReadable()) {
                    read(selectionKey);
                }
            }*/
        }
    }

    private void read(SelectionKey selectionKey) throws IOException {
        System.out.println("xxx");
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int length = channel.read(byteBuffer);
            if (length > 0) {
                byte[] data = new byte[length];
                byteBuffer.flip();
                byteBuffer.get(data);
                System.out.println("receive from client: " + new String(data));
                byteBuffer.compact();
                channel.write(ByteBuffer.wrap(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
            channel.close();
        }
    }

    private void register(SelectionKey selectionKey) {
        try {
            // SocketChannel client = serverSocketChannel.accept();
            ServerSocketChannel serv = (ServerSocketChannel) selectionKey.channel();
            SocketChannel client = serv.accept();
            System.out.println("client connect: " + client.getRemoteAddress());
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.println(client.isRegistered());
        } catch (IOException e) {
            e.printStackTrace();
            selectionKey.cancel();
        }
    }

    public void run() {
        server = new Thread(() -> {
            try {
                initializr();
                start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start();
    }

    public void stop() {
        if (server != null) {
            server.interrupt();
        }
    }
}

public class SingleThreadNIOTest {

    public static void main(String[] args) throws IOException {

        NIOSvr nioSvr = new NIOSvr("0.0.0.0", 8888);
        nioSvr.run();
        System.in.read();
        nioSvr.stop();
    }

}
