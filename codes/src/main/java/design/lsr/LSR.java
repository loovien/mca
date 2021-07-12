package design.lsr;

import java.util.HashMap;
import java.util.Map;

/**
 * created 7/12/2021 12:49 PM
 *
 * @author luowen <loovien@163.com>
 */
public class LSR {
    public abstract static class P {
        public abstract void greeting();

        protected void eating(Map<String, String> map) {
            System.out.println("eat meat");
        }
    }

    public static class S extends P {

        @Override
        public void greeting() {

        }

        protected void eating(HashMap<String, String> hashMap) {
            System.out.println("eat mice" + hashMap.getOrDefault("l", "nice"));
        }
    }

    public static void main(String[] args) {

        Map<String, String> a = new HashMap<String, String>();
        S s = new S();
        s.eating(a);

    }
}
