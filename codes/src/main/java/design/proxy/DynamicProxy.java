package design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Speak {

    void great(String msg);

}

class Japan implements Speak {

    @Override
    public void great(String msg) {
        System.out.println("ali ga duo");
    }
}

class China implements Speak {

    @Override
    public void great(String msg) {
        System.out.println("你好");
    }
}

public class DynamicProxy {

    public static void main(String[] args) {
        China china = (China) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{Speak.class}, (proxy, method, args1) -> method.invoke(new China(), args1));
        china.great("");
    }

}
