package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientTest {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));
        if (!socketChannel.isConnected()) {
            System.out.println("connected failure");
            System.exit(1);
        }
        int write = socketChannel.write(ByteBuffer.wrap("hello world".getBytes()));
        System.out.println("write to remote socket: " + write);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            socketChannel.write(ByteBuffer.wrap(next.getBytes()));
        }
    }
}
