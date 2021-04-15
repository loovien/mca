package juc;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

public class PrintX {

    static Thread t1, t2;

    static boolean flag = false;


    static ArrayList<String> strings = new ArrayList<>();
    static ArrayList<Integer> integers = new ArrayList<>();

    static {
        for (int i = 0; i < 26; i++) {
            integers.add(i);
            strings.add(String.valueOf((char) (65 + i)));
        }
    }

    public static void main(String[] args) {

        notifyImpl();
        lockSupportImpl();
    }

    private static void notifyImpl() {
        final Object lock = new Object();

        t1 = new Thread(() -> {
            t2.start();
            for (Integer i : integers) {
                synchronized (lock) {
                    try {
                        while (flag) {
                            lock.wait();
                        }
                        System.out.println(i);
                        flag = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.notify();
                    }
                }

            }
        });

        t2 = new Thread(() -> {
            for (String s : strings) {
                synchronized (lock) {
                    try {
                        while (!flag) {
                            lock.wait();
                        }
                        flag = false;
                        System.out.println(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.notify();
                    }
                }
            }
        });
        t1.start();
    }

    private static void lockSupportImpl() {
        t1 = new Thread(() -> {
            for (int i : integers) {
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(t2);
            }
        });


        t2 = new Thread(() -> {
            t1.start();
            for (String s : strings) {
                System.out.println(s);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });
        t2.start();
    }

}
