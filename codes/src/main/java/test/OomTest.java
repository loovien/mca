package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OomTest {

    private static class Person {
        private String name;

        private byte[] data = new byte[1024 * 100];

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static final List<Person> persons = new ArrayList();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.MILLISECONDS.sleep(1000);
            new Thread();
            System.out.println("thread count: " + Thread.activeCount());
            persons.add(new Person("luowen"));
        }
    }
}
