package algorithm.structs;

public class LinkReverseInfinity {

    public static class Node<V> {
        Node<V> next = null;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<Integer>(1);
        Node<Integer> head = node;
        Node<Integer> backup = node;
        for (int i = 2; i < 8; i++) {
            node.next = new Node<>(i);
            node = node.next;
        }

        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }

    }

    public static Node<Integer> process(Node<Integer> node, int k) {

        return new Node<>(1);

    }

    public static void reverse(Node<Integer> start, Node<Integer> end) {
        end = end.next;
        Node<Integer> prev = null;
        Node<Integer> current = start;
        Node<Integer> next = null;
        while (current != end) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        start.next = end;
    }

    public static Node<Integer> getIndex(Node<Integer> node, int k) {

        while (--k != 0 && node != null) {
            node = node.next;
        }
        return node;

        /*for (int i = k; i > 0; i--) {
            if (node == null) {
                return node;
            }
            node = node.hand;
        }
        return node;*/
    }
}
