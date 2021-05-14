package algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created 4/28/2021 10:59 AM
 *
 * @author luowen <loovien@163.com>
 */
public class SDeriaBST {
    public static class Node {
        Integer value;

        Node left;

        Node right;

        public Node(Integer value) {
            this.value = value;
        }
    }

    protected static Queue<Integer> prevSerialize(Node head) {
        if (head == null) {
            return null;
        }
        LinkedList<Integer> bstOrders = new LinkedList<>();
        preProcess(head, bstOrders);
        return bstOrders;
    }

    protected static void preProcess(Node head, Queue<Integer> queue) {
        if (head == null) {
            queue.add(-1);
            return;
        }
        queue.add(head.value);
        preProcess(head.left, queue);
        preProcess(head.right, queue);
    }

    protected static Node prevRebuild(Queue<Integer> queue) {
        Integer value = queue.poll();
        if (value < 0) {
            return null;
        }
        Node node = new Node(value);
        node.left = prevRebuild(queue);
        node.right = prevRebuild(queue);
        return node;
    }


    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        Queue<Integer> integers = prevSerialize(node1);
        System.out.println(integers);
    }
}
