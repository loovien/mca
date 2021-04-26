package algorithm.test;

import java.util.Stack;

/**
 * created 4/26/2021 4:57 PM
 *
 * @author luowen <loovien@163.com>
 */
public class IsPalindromeLink {
    public static class Node {
        Integer value;
        Node next = null;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(2);
        node.next.next.next.next = new Node(4);
        // boolean palindrome = isPalindrome(node);
        boolean palindrome = isPalindromeV2(node);
        System.out.println(palindrome);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    private static boolean isPalindrome(Node node) {
        Stack<Node> nodes = new Stack<>();
        Node head = node;
        while (node != null) {
            nodes.add(node);
            node = node.next;
        }

        while (head != null) {
            if (!head.value.equals(nodes.pop().value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindromeV2(Node node) {
        Node middleNode = getMiddle(node);
        Node right = reverse(middleNode);
        Node p1 = node, p2 = right;
        boolean result = true;
        while (p1 != null && p2 != null) {
            if (!p1.value.equals(p2.value)) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        recover(right, middleNode);
        return result;
    }

    private static void recover(Node right, Node middleNode) {
        if (right == null) {
            return;
        }
        Node prev = null;
        while (right != null) {
            Node next = right.next;
            right.next = prev;
            prev = right;
            right = next;
        }
        middleNode.next = prev.next;
    }

    protected static Node reverse(Node node) {
        Node prev = null;
        while (node != null) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    protected static Node getMiddle(Node node) {
        if (node.next.next == null) {
            return node;
        }
        Node slow = node.next;
        Node fast = node.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
