package algorithm;

/**
 * created 4/21/2021 10:01 AM
 *
 * @author luowen <loovien@163.com>
 */
public class MergeBigTwoTimes {

    public static void main2(String[] args) {
        int[] data = {1, 2, 4, 8, 2};
        // System.out.println(processV1(data, 0, data.length - 1));
        // System.out.println(process(data, 0, data.length - 1));

    }

    private static int processV1(int[] data, int left, int right) {

        if (left == right) {
            return 0;
        }

        int middle = left + ((right - left) >> 1);
        return processV1(data, left, middle) +
                processV1(data, middle + 1, right) +
                mergeV1(data, left, middle, right);

    }

    private static int mergeV1(int[] data, int left, int middle, int right) {

        int result = 0;
        int windowR = middle + 1;
        for (int i = left; i <= middle; i++) {
            while (windowR <= right && data[i] > (data[windowR] * 2)) {
                windowR++;
            }
            result += windowR - middle - 1;
        }


        int p1 = left, p2 = middle + 1, cursor = 0;
        int[] help = new int[right - left + 1];
        while (p1 <= middle && p2 <= right) {
            if (data[p1] <= data[p2]) {
                help[cursor++] = data[p1++];
                continue;
            }
            help[cursor++] = data[p2++];
        }

        while (p1 <= middle) {
            help[cursor++] = data[p1++];
        }
        while (p2 <= right) {
            help[cursor++] = data[p2++];
        }
        System.arraycopy(help, 0, data, left, right - left + 1);
        return result;
    }

    public static int biggerTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processV1(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int m, int r) {
        // [L....M]   [M+1....R]

        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = m + 1;
        for (int i = L; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - m - 1;
        }


        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
