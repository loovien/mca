package design.singleton;

/**
 * no deserialize issue, perfect design.singleton code.
 */
public enum Singleton04 {
    INSTANCE;

    public void print() {
        System.out.println("enum design.singleton");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> System.out.println(INSTANCE.hashCode())).start();
        }
    }
}
