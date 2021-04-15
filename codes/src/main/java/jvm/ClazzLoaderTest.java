package jvm;


import java.nio.charset.StandardCharsets;

class DemoLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] clazz = "class demo { void x() {System.out.println(123);}}".getBytes(StandardCharsets.UTF_8);
        return defineClass("demo", clazz, 0, clazz.length);
    }
}

public class ClazzLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DemoLoader loader = new DemoLoader();
        Object emo = loader.loadClass("demo").newInstance();
        System.out.println(emo);
    }
}
