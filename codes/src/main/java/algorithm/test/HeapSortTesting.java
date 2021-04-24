package algorithm.test;


import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class HeapSortTesting {

    /**
     * 获取随机长度数组
     *
     * @param maxValue 数组最大值
     * @param length   数组长度
     * @return int[]
     */
    public static int[] getRandom(int maxValue, int length) {
        return Stream.generate(() -> 1).limit(length).mapToInt((v) -> new Random().nextInt(maxValue)).toArray();
    }

    /**
     * 校验2个数组是否相等
     *
     * @param a 源
     * @param b 目标
     * @return boolean
     */
    public static boolean isEquals(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int maxTimes = 10000;
        long startTime = System.currentTimeMillis();
        System.out.println("sort starting... ");
        for (int i = 0; i < maxTimes; i++) {
            int[] random = getRandom(1000, 10);
            int[] backup = new int[random.length];
            System.arraycopy(random, 0, backup, 0, random.length);
            Arrays.sort(backup);
            heapsortUseInsert(random);
            // heapSortUseIfy(random);
            if (!isEquals(backup, random)) {
                System.out.println("Oops ! fucking fucked!!! ");
                System.out.println(Arrays.toString(random));
                System.out.println(Arrays.toString(backup));
                System.exit(1);
            }
        }
        System.out.println("sort successfully cost: " + (System.currentTimeMillis() - startTime));
    }

    public static void heapsortUseInsert(int[] data) {
        if (data == null || data.length <= 0) {
            return;
        }
        int heapSize = data.length - 1;
        for (int i = 0; i <= heapSize; i++) {
            heapInsert(data, i);
        }
        swap(data, 0, heapSize);
        while (heapSize > 0) {
            heapIfy(data, 0, heapSize);
            swap(data, 0, --heapSize);
        }
    }


    public static void heapSortUseIfy(int[] data) {
        if (data == null || data.length <= 0) {
            return;
        }
        int heapSize = data.length;
        for (int i = heapSize - 1; i >= 0; i--) { // 找到数组中最大的数据， 放到堆有堆顶
            heapIfy(data, i, heapSize);
        }
        swap(data, 0, --heapSize);
        while (heapSize > 0) {
            heapIfy(data, 0, heapSize);
            swap(data, 0, --heapSize);
        }
    }


    /**
     * 根据数据数组，遍历， 将最大的数据替换到堆有顶。（当前使用的是大顶堆）
     *
     * @param data  数据数组
     * @param index 索引
     */
    public static void heapInsert(int[] data, int index) {

        int upNodeIndex = (index - 1) / 2; // 上个节点。
        while (data[index] > data[upNodeIndex]) {
            swap(data, index, upNodeIndex);
            index = upNodeIndex;
            upNodeIndex = (upNodeIndex - 1) / 2;
        }
    }

    /**
     * 堆最后的节点和最顶的节点交换后， 需要从顶部遍历下去
     *
     * @param data     数据数组
     * @param index    索引
     * @param heapSize 堆大小
     */
    public static void heapIfy(int[] data, int index, int heapSize) {

        int downNodeIndex = index * 2 + 1;
        while (downNodeIndex < heapSize) {
            // 判断节点下最大的是左节点还是右节点。
            int deepNodeIndex = (downNodeIndex + 1 < heapSize && data[downNodeIndex + 1] > data[downNodeIndex])
                    ? downNodeIndex + 1 : downNodeIndex;
            if (data[index] > data[deepNodeIndex]) {
                break;
            }
            swap(data, index, deepNodeIndex); // 交换节点
            index = deepNodeIndex;
            downNodeIndex = deepNodeIndex * 2 + 1;
        }
    }

    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
