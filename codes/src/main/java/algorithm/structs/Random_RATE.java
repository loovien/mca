package algorithm.structs;

import javax.swing.plaf.IconUIResource;
import java.util.Arrays;

/**
 * @author: luowen <loovien@163.com>
 * @created: 4/15/2021 2:27 PM
 */
public class Random_RATE {
    private static final int times = 1000_000;

    public static void test() {

        int[] result = new int[10];
        int times = 1000_000;
        for (int i = 0; i < times; i++) {
            result[(int) (Math.random() * 10)] += 1;
        }
        System.out.println(Arrays.toString(result));
        System.out.println("=====================");
        double K = 0.3;
        int count = 0;
        for (int i = 0; i < times; i++) {
            if (x2Power() < K) {
                count++;
            }
        }
        System.out.println("testResult: " + (double) count / (double) times);
        System.out.println("distResult: " + Math.pow(K, 3));

        System.out.println("=====================");
        K = 0.3;
        count = 0;
        for (int i = 0; i < times; i++) {
            if (x2MinPower() < K) {
                count++;
            }
        }
        System.out.println("testResult: " + (double) count / (double) times);
        System.out.println("distResult: " + (1 - Math.pow(((double) 1 - K), 2)));

        System.out.println("-----------------------");
        K = 0.3;
        count = 0;
        for (int i = 0; i < times; i++) {
            if (xplus() < (K * (double) 2)) {
                count++;
            }
        }
        System.out.println("testResult: " + (double) count / (double) times);
        System.out.println("distResult: " + K / (double) 2);

    }

    public static void main(String[] args) {

        int count = 0;
        double K = 0.6;
        for (int i = 0; i < times; i++) {
            if (doubleRandom() <= K) {
                count++;
            }
        }

        System.out.println("testResult: " + (double) count / (double) times);
        System.out.println("distResult: " + K);


    }

    public static void randX() {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        int[] result1 = new int[10];
        int count = 0;
        for (int i = 0; i < times; i++) {
            /*if (((int) (Math.random() * 5) + 1) <= 3) {
                count++;
            }*/
            if (random1To7() == 7) {
                count++;
            }
        }
        System.out.println((double) count / (double) times);
        System.out.println((double) 1 / (double) 7);
    }

    public static double xplus() {
        return Math.random() + Math.random();
    }

    public static double x2Power() {
        return Math.max(Math.random(), Math.max(Math.random(), Math.random()));
    }

    public static double x2MinPower() {
        return Math.min(Math.random(), Math.random());
    }

    // ============================================================
    public static int random1To5() {
        int ans;
        do {
            ans = ((int) (Math.random() * 5)) + 1;
        } while (ans == 3);
        return ans <= 2 ? 1 : 0;
    }

    public static int random1To7() {
        int ans;
        do {
            ans = (random1To5() << 2) + (random1To5() << 1) + random1To5();
        } while (ans == 0);
        return ans;
    }

    // =================================================
    public static int notBalance() {
        return Math.random() <= 0.3 ? 1 : 0;
    }

    public static int balance() {
        int ans;
        do {
            ans = notBalance();
        } while (ans == notBalance());
        return ans;
        /*int ans, a, b;
        do {
            a = notBalance();
            b = notBalance();
            ans = a + b;
        } while (ans != 1);

        return a == 1 ? 1 : 0;*/

    }

    // ================================
    public static double doubleRandom() {
        return Math.random(); // + Math.random();
    }

}

