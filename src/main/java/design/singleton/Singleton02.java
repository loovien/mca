package design.singleton;

public class Singleton02 {

    private static volatile Singleton02 singleton02 = null;

    private Singleton02() {
    }

    public static Singleton02 getInstance() {
        if (singleton02 == null) {
            synchronized (Singleton02.class) {
                if (singleton02 != null) {
                    return singleton02;
                }
                singleton02 = new Singleton02();
            }
        }
        return singleton02;
    }

    public void print() {
        System.out.println("design.singleton 02");
    }
}
