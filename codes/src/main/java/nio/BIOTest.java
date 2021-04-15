package nio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOTest {

    static class BIOSvr {
        public void run() throws IOException {
            ServerSocket localhost = new ServerSocket();
            localhost.bind(new InetSocketAddress("localhost", 8888));
            localhost.setReuseAddress(true);
            while (true) {
                Socket accept = localhost.accept();
                new Thread(() -> {
                    try {
                        System.out.println("client connected: " + accept.getRemoteSocketAddress());
                        while (true) {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                            String line = bufferedReader.readLine();
                            System.out.println("read line: " + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            accept.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, "thread-" + accept.getRemoteSocketAddress().toString()).start();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BIOSvr bioSvr = new BIOSvr();
        bioSvr.run();
    }
}
