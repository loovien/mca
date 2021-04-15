package juc;

import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.ls.LSOutput;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class InterruptThread {

    private static volatile boolean state = true;

    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {

        // interruptThread();

        // md5("luowen");

        // stopThread();
        interrupted();


    }

    private static void interrupted() throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("gogo..");
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
        System.out.println("end..");

    }


    private static void stopThread() throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (state) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("go ...");
            }
            System.out.println("thread ending");
        });
        thread.start();

        TimeUnit.SECONDS.sleep(4);

        // thread.stop();

        state = false;
    }

    private static void interruptThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("dida ...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);

        System.out.println("end");

        thread.stop();
    }

    public static void md5(String data) throws NoSuchAlgorithmException {
        System.out.println(DigestUtils.md5Hex(data));
    }

}