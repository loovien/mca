package test;

public class BitCalc {

    public static void print(int data) {
        for (int i = 31; i >= 0; i--) {
            char bit = (data & (1 << i)) > 0 ? '1' : '0';
            System.out.print(bit);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        print(1024);
        print(1);
        print(5);
        print(-5);
        print(~5 + 1);


    }
}
