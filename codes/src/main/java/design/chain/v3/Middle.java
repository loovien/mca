package design.chain.v3;

import java.util.ArrayList;
import java.util.List;

/**
 * created 7/13/2021 2:16 PM
 *
 * @author luowen <loovien@163.com>
 */
public class Middle {

    public interface Engine {
        void add(Middleware middleware);

        void process(String request, String response);
    }

    public interface Middleware {
        void apply(String request, String response, Engine engine);
    }

    public static class CorsMiddle implements Middleware {

        @Override
        public void apply(String request, String response, Engine engine) {
            System.out.println(request.replace("lo", "xx"));
            engine.process(request, response);
            System.out.println(response.replace("bad", "good"));
        }
    }

    public static class SensitiveMiddle implements Middleware {

        @Override
        public void apply(String request, String response, Engine engine) {
            System.out.println(request.replace("xx", "mao"));
            engine.process(request, response);
            System.out.println(response.replace("mao", "bad"));
        }
    }

    public static class TxtEngine implements Engine {

        private int cursor = 0;

        private final List<Middleware> middlewareList = new ArrayList<>();

        @Override
        public void add(Middleware middleware) {
            middlewareList.add(middleware);
        }

        @Override
        public void process(String request, String response) {
            if (cursor >= middlewareList.size()) {
                return;
            }
            Middleware middleware = middlewareList.get(cursor);
            cursor++;
            middleware.apply(request, response, this);
        }
    }

    public static void main(String[] args) {
        TxtEngine txtEngine = new TxtEngine();
        txtEngine.add(new CorsMiddle());
        txtEngine.add(new SensitiveMiddle());

        txtEngine.process("lo bigwen", "mao's my lover");
    }


}
