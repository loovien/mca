package algorithm;

public class Hanota {
    public static void main(String[] args) {
        stepPrint(3);
        System.out.println("=============================== v2 =============================");
        stepPrintV2(3);
    }

    public static void stepPrintV2(int n) {
        process(n, "left", "right", "middle");
    }

    private static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 " + from + " => " + to);
            return;
        }
        process(n - 1, from, other, to);
        System.out.println("move " + n + " " + from + " to " + to);
        process(n - 1, other, to, from);
    }


    private static void stepPrint(int i) {
        left2Right(i);
    }

    private static void left2Right(int i) {
        if (i == 1) {
            System.out.println("move 1 from left => right");
            return;
        }
        left2Mid(i - 1);
        System.out.println("move " + i + " left =>  right");
        mid2Right(i - 1);
    }

    private static void mid2Right(int i) {
        if (i == 1) {
            System.out.println("move 1 middle => right");
            return;
        }
        mid2Left(i - 1);
        System.out.println("move " + i + " middle => right");
        left2Right(i - 1);
    }

    private static void mid2Left(int i) {
        if (i == 1) {
            System.out.println("move 1 middle => left");
            return;
        }
        mid2Right(i - 1);
        System.out.println(" move " + i + " middle => left");
        right2Left(i - 1);

    }

    private static void right2Left(int i) {
        if (i == 1) {
            System.out.println("move 1 right => left");
            return;
        }
        right2Mid(i - 1);
        System.out.println("move " + i + " right => left");
        mid2Left(i - 1);
    }

    private static void right2Mid(int i) {
        if (i == 1) {
            System.out.println("move 1 right => middle");
            return;
        }
        right2Left(i - 1);
        System.out.println("move " + i + " right => middle");
        left2Mid(i - 1);
    }

    private static void left2Mid(int i) {
        if (i == 1) {
            System.out.println("move 1 left => middle");
            return;
        }
        left2Right(i - 1);
        System.out.println("move " + i + " left => middle");
        right2Mid(i - 1);
    }
}
