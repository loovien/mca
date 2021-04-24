package algorithm.test;


import java.util.Arrays;

public class HeapSortTesting {

    public static void main(String[] args) {

        int[] data = {1, 5, 2, 5, 2, 53, 63, 2, 1, 45, 38, 100, -1};
        heapSort(data);
        System.out.println(Arrays.toString(data));

    }


    public static void heapSort(int[] data) {
        if (data == null || data.length <= 0) {
            return;
        }
        int heapSize = data.length;
        for (int i = heapSize - 1; i >= 0; i--) { // 找到数组中最大的数据， 放到堆有堆顶
            heapIfy(data, i);
        }
        swap(data, 0, --heapSize);
        int a = 0;
        while (heapSize > 0) {
            a++;
            heapInsert(data, 0, heapSize--);
            System.out.println(Arrays.toString(data));
            System.out.println("-====");
            swap(data, 0, heapSize);
            System.out.println(Arrays.toString(data));
            if (a == 2) {
                System.exit(0);
            }
        }
    }


    /**
     * 根据数据数组，遍历， 将最大的数据替换到堆有顶。（当前使用的是大顶堆）
     *
     * @param data  数据数组
     * @param index 索引
     */
    public static void heapIfy(int[] data, int index) {
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
    public static void heapInsert(int[] data, int index, int heapSize) {
        int downNodeIndex = index * 2 + 1;

        while (downNodeIndex <= heapSize) {
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
