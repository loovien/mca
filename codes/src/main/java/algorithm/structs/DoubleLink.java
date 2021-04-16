package algorithm.structs;

/**
 * created 4/16/2021 6:11 PM
 *
 * @author luowen <loovien@163.com>
 */
public class DoubleLink {

    public static class Node<V> {
        Node<V> prev = null;
        Node<V> next = null;
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class DobuleLinkImpl<V> {
        Node<V> head = null;
        Node<V> tail = null;
        int size = 0;

        public void leftPush(V v) {
            size++;
            Node<V> node = new Node<>(v);
            if (head == null) { // first node
                head = node;
                tail = node;
                return;
            }
            node.next = head;
            head.prev = node;
            head = node;
        }

        public int size() {
            return size;
        }

        public V leftPop() {
            size--;
            if (head == null) {
                return null;
            }
            V value = head.value;
            if (head == tail) {
                head = null;
                tail = null;
                return value;
            }
            head = head.next;
            head.prev = null;
            return value;
        }
    }

    public static void main(String[] args) {
        DobuleLinkImpl<Integer> integerDobuleLink = new DobuleLinkImpl<>();
        integerDobuleLink.leftPush(1);
        integerDobuleLink.leftPush(2);
        integerDobuleLink.leftPush(3);
        integerDobuleLink.leftPush(4);

        while (integerDobuleLink.size > 0) {
            System.out.println(integerDobuleLink.leftPop());
        }

    }
}
