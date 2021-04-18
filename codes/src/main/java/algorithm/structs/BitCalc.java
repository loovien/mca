package algorithm.structs;

public class BitCalc {

    public static void main(String[] args) {
        int a = 1, b = 5;
        int add = add(a, b);
        System.out.println(add);

    }

    public static int add(int a, int b) {
        int sum = 0;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }


}
