package design.singleton;

public class Singleton01 {
    private final static Singleton01 singleton01 = new Singleton01();

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        return singleton01;
    }

    public void print() {
        System.out.println("design.singleton 01");
    }
}
