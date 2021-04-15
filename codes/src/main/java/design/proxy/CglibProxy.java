package design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.time.Instant;

class Tank {
    public void move() {
        System.out.println("tank move kongkong...");
    }
}

public class CglibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("xxxxxxxxxxxx");
            Instant now = Instant.now();
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("cost: " + (Instant.now().getNano() - now.getNano()));
            return result;
        });

        Tank tank = (Tank) enhancer.create();
        tank.move();
    }

}

