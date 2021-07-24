package design.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * created 7/12/2021 3:49 PM
 *
 * @author luowen <loovien@163.com>
 */
public class ServerFactory {
    public interface Protocol {
        byte[] encode(String data);

        String decode(byte[] data);
    }

    public interface Server {
        void connect();

        void dataTransfer(String data);

        void shutdown();
    }

    public static class TcpProtocol implements Protocol {

        @Override
        public byte[] encode(String data) {
            return new byte[0];
        }

        @Override
        public String decode(byte[] data) {
            return null;
        }
    }

    public static class HttpProtocol implements Protocol {

        @Override
        public byte[] encode(String data) {
            return new byte[0];
        }

        @Override
        public String decode(byte[] data) {
            return null;
        }
    }

    public static abstract class AbstractServer implements Server {

        @Override
        public void connect() {

        }

        @Override
        public void dataTransfer(String data) {
            // pre run();
            System.out.println("hello " + data);
            run();
            // post run
        }

        protected abstract void run();

        @Override
        public void shutdown() {

        }
    }

    public static class TcpServer extends AbstractServer {

        public TcpServer() {
        }

        @Override
        protected void run() {
            System.out.println("tcp run");
        }
    }

    public static class HttpServer extends AbstractServer {

        @Override
        protected void run() {
            System.out.println("httpServer run");
        }
    }

    public static class SimpleServerFactory {
        public static <T extends Server> T serverBuilder(Class<T> t) throws Exception {
            return (T) Class.forName(t.getName()).newInstance();
        }
    }


    public static void main(String[] args) throws Exception {
        TcpServer tcpServer = SimpleServerFactory.serverBuilder(TcpServer.class);
        tcpServer.connect();
        tcpServer.dataTransfer("hello");
        tcpServer.shutdown();

        HttpServer httpServer = SimpleServerFactory.serverBuilder(HttpServer.class);
        httpServer.connect();
        httpServer.dataTransfer("hello");
        httpServer.shutdown();
    }
}
