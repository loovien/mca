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
        int write = socketChannel.write(ByteBuffer.wrap("hello world\n".getBytes()));
        System.out.println("write to remote socket: " + write);
        new Thread(() -> {
            ByteBuffer data = ByteBuffer.allocate(1024);
            try {
                do {
                    int read = socketChannel.read(data);
                    if (read > 0) {
                        byte[] bytes = new byte[read];
                        data.flip();
                        data.get(bytes);
                        System.out.println("receive from server: " + new String(bytes));
                        data.compact();
                    } else {
                        socketChannel.close();
                    }
                } while (true);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socketChannel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            socketChannel.write(ByteBuffer.wrap(next.getBytes()));
        }
    }
}
