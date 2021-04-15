package algorithm.structs;


import java.util.Arrays;

/**
 * @author: luowen <loovien@163.com>
 * @created: 4/15/2021 12:27 PM
 */
public class Arr_PRESUM {


    private final int[] prefixSUM;

    int[] items = new int[]{1, 3, 2, 5, 3, 9, 3, 8, 2, 9, 2, 5};

    public Arr_PRESUM() {
        int previous = 0;
        prefixSUM = new int[items.length];
        for (int index = 0; index < items.length; index++) {
            prefixSUM[index] = previous + items[index];
            previous += items[index];
        }
    }

    public int[] getPrefixSUM() {
        return this.prefixSUM;
    }

    public int getRange(int l, int r) {
        if (l < 0 || r > items.length || l > r) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += items[i];
        }
        return sum;
    }

    public int getRangeV2(int l, int r) {
        if (l < 0 || r > items.length || l > r) {
            throw new IllegalArgumentException();
        }
        if (l == 0) {
            return prefixSUM[r];
        }
        return prefixSUM[r] - prefixSUM[l - 1];
    }

    public static void main(String[] args) {

        Arr_PRESUM pres = new Arr_PRESUM();

        int range = pres.getRange(0, 3);
        int rangeV2 = pres.getRangeV2(0, 3);
        System.out.println(range + " " + rangeV2);

    }
}
