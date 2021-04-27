package algorithm;

import java.util.Random;

/**
 * created 4/27/2021 12:55 PM
 * <p>
 * 给定两个链表， 链表可以有环， 可以能没有环，判断两个链表有没有相交。
 *
 * @author luowen <loovien@163.com>
 */
public class LinkCross {

    public static class Node {
        Integer value;
        Node next = null;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static Node getLoopLink(int length, int cyclePoint) {
        Node head = new Node(1);
        Node cursor = head;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            cursor.next = new Node(1 + random.nextInt(10));
            cursor = cursor.next;
        }
        if (cyclePoint > 0) {
            Node cycleNode = head;
            for (int i = 0; i < cyclePoint; i++) {
                cycleNode = cycleNode.next;
            }
            cursor.next = cycleNode;
        }
        return head;
    }

    public static void printNodeList(Node head) {
        while (head != null) {
            System.out.println("head value: " + head.value);
            head = head.next;
        }
    }


    public static void main(String[] args) {

        Node loopLink1 = getLoopLink(10, 2);
        printNodeList(loopLink1);
        Node loopLink2 = getLoopLink(10, 0);
        printNodeList(loopLink2);

    }


}
