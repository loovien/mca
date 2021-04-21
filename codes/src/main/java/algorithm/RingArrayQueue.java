package algorithm;

/**
 * created 4/21/2021 9:08 AM
 *
 * @author luowen <loovien@163.com>
 */
public class RingArrayQueue {

    public static class RingArrayQueueImpl {
        private int capcity = 0;

        private int pushIndex = 0;

        private int popIndex = 0;

        protected int[] container = null;

        private int size = 0;

        public RingArrayQueueImpl(int capcity) {
            this.capcity = capcity;
            this.container = new int[capcity];
        }

        public void add(int value) {
            if (size + 1 > capcity) {
                throw new RuntimeException("enough");
            }
            size++;
            pushIndex = indexRebuild(pushIndex);
            container[pushIndex] = value;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("empty");
            }
            size--;
            popIndex = indexRebuild(popIndex);
            return container[popIndex];
        }

        protected int indexRebuild(int index) {
            return index < capcity - 1 ? index + 1 : 0;
        }
    }

    public static void main(String[] args) {

        RingArrayQueueImpl ringArrayQueue = new RingArrayQueueImpl(5);
        ringArrayQueue.add(1);
        ringArrayQueue.add(2);
        ringArrayQueue.add(3);
        ringArrayQueue.add(4);
        ringArrayQueue.add(5);

        for (int i = 0; i < 5; i++) {
            System.out.println(ringArrayQueue.pop());
        }
    }
}
