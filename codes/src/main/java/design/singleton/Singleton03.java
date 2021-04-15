package design.singleton;

public class Singleton03 {

    private Singleton03() {
    }

    private static class __ {
        public static Singleton03 singleton03;

        static {
            singleton03 = new Singleton03();
        }
    }

    public static Singleton03 getInstance() {
        return __.singleton03;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Singleton03.getInstance().hashCode())).start();
        }
    }
}
