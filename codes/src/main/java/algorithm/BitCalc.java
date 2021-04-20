package algorithm;

/**
 * created 4/19/2021 2:25 PM
 *
 * @author luowen <loovien@163.com>
 */
public class BitCalc {
    public static void main(String[] args) {
        // bitCalcV1();
    }

    private static void bitCalcV1() {
        int[] data = {1, 1, 1, 3, 3, 3, 3, 4, 4, 7, 8, 8, 7, 7};
        int eor = 0, eor1 = 0;
        for (int datum : data) {
            eor ^= datum;
        }
        int flag = eor & (-eor);
        for (int datum : data) {
            if ((datum & flag) > 0) {
                eor1 ^= datum;
            }
        }
        System.out.println("single one: " + eor1 + " single two: " + (eor ^ eor1));
    }

    private static void bitCalcV2() {
        // K = 3, M= 5
        int[] data = {1, 1, 1, 3};

    }
}
