package nio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

@FunctionalInterface
interface Handler {
    void handler(Channel channel);
}

class Worker implements Runnable {

    private final Selector selector;

    private Handler handler;

    private final LinkedBlockingQueue<SocketChannel> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public Worker() throws IOException {
        selector = Selector.open();
    }

    public void wakeup() {
        selector.wakeup();
    }

    public void addHandler(Handler handler) {
        this.handler = handler;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            SocketChannel channel = null;
            try {
                System.out.println("=========================================== selector : " + Thread.currentThread().getName());
                selector.select();
                System.out.println("******************************************* selector : " + Thread.currentThread().getName() + " " + selector.selectedKeys().size());
                // System.out.println(Thread.currentThread().getName() + " wakeup " + linkedBlockingQueue.size());
                if (!linkedBlockingQueue.isEmpty()) {
                    channel = linkedBlockingQueue.take();
                    channel.register(selector, SelectionKey.OP_READ);
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                if (selectionKeys.size() <= 0) {
                    continue;
                }

                for (SelectionKey selectionKey : selectionKeys) {
                    selector.selectedKeys().remove(selectionKey);
                    if (!selectionKey.isReadable()) {
                        continue;
                    }
                    System.out.println(Thread.currentThread().getName() + " handler");
                    handler.handler(selectionKey.channel());
                }

            } catch (IOException | InterruptedException e) {
                if (channel != null) {
                    channel.close();
                }
                e.printStackTrace();
            }
        }

    }

    public void addChannel(SocketChannel clientSocket) {
        linkedBlockingQueue.add(clientSocket);
    }
}

class Bootstrap {

    private final List<Worker> workers = new ArrayList<>();

    public Bootstrap(int worksNum) throws IOException {
        if (worksNum <= 0) {
            throw new IllegalArgumentException("must than zero");
        }
        for (int i = 0; i < worksNum; i++) {
            workers.add(new Worker());
        }
    }

    public Bootstrap listen(SocketAddress address) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(address);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        new Thread(() -> {
            int currentWork = 0;
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    if (selectionKeys.size() <= 0) {
                        continue;
                    }
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (!selectionKey.isAcceptable()) {
                            continue;
                        }
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientSocket = channel.accept();
                        clientSocket.configureBlocking(false);
                        System.out.println("client connected: " + clientSocket.getRemoteAddress());
                        Worker worker = workers.get(currentWork % workers.size());// .wakeup();
                        worker.addChannel(clientSocket);
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                        worker.wakeup();
                        currentWork += 1;
                    }
                    /*for (SelectionKey selectionKey : selectionKeys) {
                        selector.selectedKeys().remove(selectionKey);
                        if (!selectionKey.isAcceptable()) {
                            continue;
                        }
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientSocket = channel.accept();
                        clientSocket.configureBlocking(false);
                        System.out.println("client connected: " + clientSocket.getRemoteAddress());
                        linkedBlockingQueue.add(clientSocket);
                        workers.get(currentWork % workers.size()).wakeup();
                        currentWork += 1;
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return this;
    }

    public Bootstrap addWorkHandler(Handler handler) {
        for (Worker worker : workers) {
            worker.addHandler(handler);
        }
        return this;
    }

    public void start() {
        int NO = 0;
        for (Worker worker : workers) {
            new Thread(worker, "workthread-" + (NO++)).start();
        }
    }


}


public class SimpleNetty {
    public static void main(String[] args) throws IOException {
        new Bootstrap(3).listen(new InetSocketAddress("0.0.0.0", 8889))
                .addWorkHandler((channel) -> {
                    System.out.println(Thread.currentThread().getName() + " handing");
                    SocketChannel clientChannel = (SocketChannel) channel;
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    try {
                        int length = clientChannel.read(allocate);
                        if (length < 0) {
                            channel.close();
                            return;
                        }
                        byte[] bytes = new byte[length];
                        allocate.flip();
                        allocate.get(bytes);
                        String packet = new String(bytes);
                        System.out.println("read from client: " + packet);
                        clientChannel.write(ByteBuffer.wrap((packet.toUpperCase().getBytes())));
                    } catch (IOException e) {
                        System.out.println("closed");
                        e.printStackTrace();
                        try {
                            channel.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }).start();
    }
}
