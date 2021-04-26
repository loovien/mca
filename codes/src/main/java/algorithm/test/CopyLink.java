package algorithm.test;

import java.rmi.UnexpectedException;
import java.util.HashMap;

/**
 * created 4/26/2021 5:37 PM
 *
 * @author luowen <loovien@163.com>
 */
public class CopyLink {
    public static class Node {
        Integer value;
        Node next = null;
        Node rand = null;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node1.rand = node3;
        node2.rand = node1;

        Node node4 = copyLinkV2(node1);
        // node4 = node1;

        while (node4.next != null) {
            System.out.println("value: " + node4.value + " next：" + node4.next.value + " rand: " + node4.rand.value);
            node4 = node4.next;
        }

        while (node1.next != null) {
            System.out.println("value: " + node1.value + " next：" + node1.next.value + " rand: " + node1.rand.value);
            node1 = node1.next;
        }
    }

    private static Node copyLink(Node node1) {
        HashMap<Node, Node> nodeNodeHashMap = new HashMap<>();
        Node head = node1;
        while (head != null) {
            nodeNodeHashMap.put(head, new Node(head.value));
            head = head.next;
        }

        head = node1;
        while (head != null) {
            Node copyNode1 = nodeNodeHashMap.get(head);
            copyNode1.next = nodeNodeHashMap.get(head.next);
            copyNode1.rand = nodeNodeHashMap.get(head.rand);
            head = head.next;
        }
        return nodeNodeHashMap.get(node1);
    }


    private static Node copyLinkV2(Node node1) {
        Node head = node1;
        while (head != null) {
            Node next = head.next;
            Node shadow = new Node(-head.value);
            head.next = shadow;
            shadow.next = next;
            head = next;
        }
        head = node1;
        while (head.next != null && head.next.next != null) {
            Node shadow = head.next;
            shadow.rand = head.rand.next;
            head = head.next.next;
        }
        head = node1;
        Node result = node1.next;
        while (head.next != null && head.next.next != null && head.next.next.next != null) {
            Node shadow = head.next;
            Node next = shadow.next;
            head.next = next;
            head = next;
            shadow.next = next.next;
        }
        return result;
    }
}
