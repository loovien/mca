package algorithm.test;

import java.util.Arrays;

/**
 * created 4/20/2021 11:22 AM
 *
 * @author luowen <loovien@163.com>
 */
public class ArrayQueue {

    public static class ArrayQueueImpl {
        int[] data = new int[5];

        private int index = 0;

        public void add(int value) {
            int cursor = index % 5;
            index++;
            data[cursor] = value;
        }

        public int pop() {
            if (index == 0) {
                return -100;
            }
            int cursor = index % 5;
            index--;
            return data[cursor - 1];
        }

    }

    public static void main(String[] args) {
        ArrayQueueImpl arrayQueue = new ArrayQueueImpl();
        arrayQueue.add(1);
        arrayQueue.add(2);
        arrayQueue.add(3);

        System.out.println(Arrays.toString(arrayQueue.data));

        for (int i = 0; i < 3; i++) {
            System.out.println(arrayQueue.pop());
        }
    }


}
