package algorithm.structs;

/**
 * created 4/16/2021 5:52 PM
 *
 * @author luowen <loovien@163.com>
 */
public class LWStack {

    public static class Node<V> {
        Node<V> next = null;
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class LWStackImpl<V> {
        public Node<V> cursor = null;

        public void push(V value) {
            Node<V> node = new Node<>(value);
            if (cursor == null) {
                cursor = node;
            } else {
                node.next = cursor;
                cursor = node;
            }
        }

        public V pop() {
            V ans = null;
            if (cursor != null) {
                ans = cursor.value;
                cursor = cursor.next;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        LWStackImpl<Integer> integerLWStack = new LWStackImpl<>();
        integerLWStack.push(1);
        integerLWStack.push(2);
        integerLWStack.push(3);
        integerLWStack.push(4);
        while (true) {
            Integer pop = integerLWStack.pop();
            if (pop == null) {
                break;
            }
            System.out.println(pop);
        }
    }
}
