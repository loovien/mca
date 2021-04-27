package algorithm;

import java.util.HashMap;
import java.util.HashSet;
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

    /**
     * 获取一个link
     *
     * @param length     link 长度
     * @param cyclePoint 是否需要环
     * @return 链表
     */
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
            cursor.value = -cursor.value;
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

    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) {
                return null;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 找到2个链表都有环型的交点
     *
     * @param head1      link A 头节点
     * @param cycleNode1 link A 环型交点
     * @param head2      link B 头节点
     * @param cycleNode2 link B 环型节点
     * @return Node 交点
     */
    protected static Node getLoopCrossNode(Node head1, Node cycleNode1, Node head2, Node cycleNode2) {
        Node p1 = head1, p2 = head2;
        if (cycleNode1 == cycleNode2) {
            int length = 0;
            while (p1 != cycleNode1) {
                length++;
                p1 = p1.next;
            }

            while (p2 != cycleNode1) {
                length--;
                p2 = p2.next;
            }

            Node maxNode = null, cursor = null;
            if (length >= 0) {
                maxNode = head1;
                cursor = head2;
            } else {
                maxNode = head2;
                cursor = head1;
            }
            int moreStep = Math.abs(length);
            while (moreStep > 0) {
                maxNode = maxNode.next;
                moreStep--;
            }

            while (cursor != maxNode) {
                cursor = cursor.next;
                maxNode = maxNode.next;
            }
            return cursor;
        }

        // 处理两个都有环
        while (p1 != cycleNode1) {
            if (p1 == cycleNode2) {
                return p1;
            }
            p1 = p1.next;
        }
        return null;
    }

    /**
     * 找2个连别都没有环型的交点
     *
     * @param head1 link A 头节点
     * @param head2 link B 头节点
     * @return Node 交点
     */
    protected static Node getNoLoopCrossNode(Node head1, Node head2) {
        int length = 0;
        Node cursor = head1;
        while (cursor != null) {
            length++;
            cursor = cursor.next;
        }
        cursor = head2;
        while (cursor != null) {
            length--;
            cursor = cursor.next;
        }
        Node maxLink = null;
        if (length >= 0) {
            maxLink = head1;
            cursor = head2;
        } else {
            maxLink = head2;
            cursor = head1;
        }

        int moreStep = Math.abs(length);
        while (moreStep > 0) {
            maxLink = maxLink.next;
            moreStep--;
        }

        while (cursor != maxLink) {
            cursor = cursor.next;
            maxLink = maxLink.next;
        }
        return cursor;
    }


    public static void main(String[] args) {

        Node loopLink1 = getLoopLink(10, 1);
        Node loopNode1 = getLoopNode(loopLink1);

        Node loopLink2 = getLoopLink(10, 3);
        Node loopNode2 = getLoopNode(loopLink2);

        if ((loopNode1 == null && loopNode2 != null) || (loopNode1 != null && loopNode2 == null)) {
            System.out.println("link1 或 link2 一个不为null, 下个为null, 则不会相交");
            return;
        }

        /*
         *              b
         *            /
         *  a       /
         *  \     /
         *   \  /
         *    P
         *    |
         *    |
         *    c
         *
         *    A 没有环， link B 也没有环， 就只有这个结构,  先计算B链表比A链表多了几个结点,
         *    然后长的链表b先多走多出来a的步， 再和同时向下走， 直到节点相我等，就是交点了。
         */
        if (loopNode1 == null) {
            Node noLoopCrossNode1 = getNoLoopCrossNode(loopLink1, loopLink2);
            System.out.println(noLoopCrossNode1.value);
            return;
        }

        /*
         *        有如下2中情况
         *                b
         *               /
         *  a          /
         *   \       /                  a
         *    \     /                    \
         *     \  /                       \           b
         *      \                           \       /
         *      P                        /   \   /
         *      \                       /       \
         *       |                      \       /
         *      |                         _____
         *      |
         *     /  \
         *    /    \
         *   |      \
         *   \      /
         *    \___/
         */
        Node loopCrossNode = getLoopCrossNode(loopLink1, loopNode1, loopLink2, loopNode2);
        System.out.println(loopCrossNode);

    }


    /**
     * set 实现
     *
     * @param head1 link A 节点
     * @param head2 link B 节点
     * @return Node 焦点
     */
    public static Node containerImpl(Node head1, Node head2) {
        HashSet<Node> nodes = new HashSet<>();
        while (head1 != null) {
            nodes.add(head1);
            head1 = head1.next;
        }

        while (head2 != null) {
            if (nodes.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }

}
