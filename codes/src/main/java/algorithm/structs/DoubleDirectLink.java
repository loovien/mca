package algorithm.structs;

/**
 * created 4/16/2021 11:12 AM
 *
 * @author luowen <loovien@163.com>
 */
public class DoubleDirectLink {

    public static class Node {
        Node next;
        Node prev;
        Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.prev = node;
        node.next = new Node(2);
        node.next.prev = node.next;
        node.next.next = new Node(3);

        Node next = null, prev = null;
        while (node != null) {
            next = node.next;
            node.next = prev;
            node.prev = next;
            prev = node;
            node = next;
        }
        System.out.println(prev.next.value);
        System.out.println(prev.next.prev.value);
        System.out.println(prev.next.next.value);
    }
}
