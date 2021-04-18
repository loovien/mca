package algorithm.structs;

public class MergeLink {
    public static class Node<V> {
        Node<V> next = null;

        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node<Integer> integerNode = new Node<Integer>(2);
        integerNode.next = new Node<>(4);
        integerNode.next.next = new Node<>(6);
        integerNode.next.next.next = new Node<>(8);

        Node<Integer> integerNode1 = new Node<>(3);
        integerNode1.next = new Node<>(5);
        integerNode1.next.next = new Node<>(7);

        Node<Integer> merge = merge(integerNode, integerNode1);
        while (merge != null) {
            System.out.println(merge.value);
            merge = merge.next;
        }


    }

    public static Node<Integer> merge(Node<Integer> node1, Node<Integer> node2) {
        Node<Integer> head = (node1.value > node2.value) ? node2 : node1;
        Node<Integer> prev = head;
        Node<Integer> c1 = head.next;
        Node<Integer> c2 = node1.value > node2.value ? node1 : node2;
        while (c1 != null && c2 != null) {
            if (c1.value <= c2.value) {
                prev.next = c1;
                c1 = c1.next;
                prev = prev.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
                prev = prev.next;
            }
        }
        prev.next = c1 != null ? c1 : c2;
        return head;
    }
}
