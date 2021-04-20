package algorithm.structs;

/**
 * created 4/20/2021 10:26 AM
 *
 * @author luowen <loovien@163.com>
 */
public class RemoveLink {

    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<Integer>(1);

        node.next = new Node<>(2);
        node.next.next = new Node<>(3);
        node.next.next.next = new Node<>(2);
        node.next.next.next.next = new Node<>(4);

        Node<Integer> newNode = removeNode(node, 2);
        while (newNode != null) {
            System.out.println(newNode.data);
            newNode = newNode.next;
        }
    }

    public static <T> Node<T> removeNode(Node<T> node, Integer value) {
        if (node == null) {
            return null;
        }

        Node<T> head = node;

        while (node != null) {
            while (node.data == value) {
                node = node.next;
                head = node;
            }
            Node<T> next = node.next;
            if (next == null) {
                return head;
            }
            if (node.next.data != value) {
                node = next;
                continue;
            }
            node.next = next.next;
            node = next.next;
        }
        return head;
    }
}
