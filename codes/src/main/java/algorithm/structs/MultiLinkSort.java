package algorithm.structs;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class MultiLinkSort {
    public static class Node<V> {
        Node<V> next = null;

        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class MultiLinkSortImpl<V> {

        private PriorityQueue<Node<Integer>> p = new PriorityQueue<>(Comparator.comparingInt(node -> node.value));

        public Node<Integer> sort(Node<Integer>[] data) {
            for (int i = 0; i < data.length; i++) {
                while (data[i] != null) {
                    p.add(data[i]);
                    data[i] = data[i].next;
                }
            }
            Node<Integer> next, prev = p.poll();
            Node<Integer> head = prev;
            do {
                next = p.poll();
                prev.next = next;

                prev = prev.next;
            } while (p.size() > 0);
            if (next != null) {
                next.next = null;
            }
            return head;
        }

        public Node<Integer> sortV2(Node<Integer>[] data) {
            if (data.length <= 0) {
                return null;
            }
            Collections.addAll(p, data);
            Node<Integer> head = p.poll();
            Node<Integer> prev = head;
            if (prev.next != null) {
                p.add(prev.next);
            }

            while (!p.isEmpty()) {
                Node<Integer> poll = p.poll();
                prev.next = poll;
                if (poll.next != null) {
                    p.add(poll.next);
                }
                prev = prev.next;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<Integer>(1);
        node1.next = new Node<>(3);
        node1.next.next = new Node<>(5);
        node1.next.next.next = new Node<>(7);

        Node<Integer> node2 = new Node<>(2);
        node2.next = new Node<>(4);
        node2.next.next = new Node<>(6);
        node2.next.next.next = new Node<>(10);

        Node<Integer> node3 = new Node<>(8);
        node3.next = new Node<>(8);
        node3.next.next = new Node<>(16);
        node3.next.next.next = new Node<>(17);

        Node[] data = new Node[]{node1, node2, node3};

        MultiLinkSortImpl<Integer> multiLinkSort = new MultiLinkSortImpl<>();
        Node sort = multiLinkSort.sortV2(data);

        // Node sort = multiLinkSort.sort(data);
        while (sort != null) {
            System.out.println(sort.value);
            sort = sort.next;
        }
    }

}
