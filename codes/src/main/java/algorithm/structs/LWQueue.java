package algorithm.structs;

/**
 * created 4/16/2021 5:20 PM
 *
 * @author luowen <loovien@163.com>
 */
public class LWQueue {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class LWQueueImpl<V> {
        private Node<V> head = null;
        private Node<V> tail = null;

        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V v) {
            Node<V> node = new Node<>(v);
            if (head == null && tail == null) {
                head = node;
                tail = node;
                return;
            }
            Node<V> next = tail;
            next.next = node;
            tail = node;
            size++;
        }

        public V take() {
            V ans = null;
            if (head != null) {
                Node<V> next = head;
                head = next.next;
                ans = next.value;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }
    }

    public static void main(String[] args) {

        LWQueueImpl<Integer> integerLWQueue = new LWQueueImpl<>();
        integerLWQueue.offer(1);
        integerLWQueue.offer(30);
        integerLWQueue.offer(3);
        integerLWQueue.offer(4);

        for (int i = 0; i < 4; i++) {
            System.out.println(integerLWQueue.take());
        }

    }

}
