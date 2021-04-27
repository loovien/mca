package algorithm.blog;

import javafx.scene.chart.StackedAreaChart;

import java.util.Stack;

public class TwoBranchTree {
    public static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    public enum Order {
        FIRST, MIDDLE, LAST;
    }

    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        a.left = b;
        b.left = d;
        b.right = e;

        a.right = c;
        c.left = f;
        c.right = g;

        // orderPrint(a, Order.MIDDLE);
        // firstOrderPrint(a);
        orderPrint(a, Order.MIDDLE);
        System.out.println("===============");
        middleOrderPrint(a);
    }

    public static void orderPrint(Node node, Order order) {
        if (node == null) {
            return;
        }
        if (order == Order.FIRST) {
            System.out.println(node.value);
        }
        orderPrint(node.left, order);
        if (order == Order.MIDDLE) {
            System.out.println(node.value);
        }
        orderPrint(node.right, order);
        if (order == Order.LAST) {
            System.out.println(node.value);
        }
    }

    public static void firstOrderPrint(Node node) {
        Stack<Node> nodes = new Stack<>();
        nodes.add(node);
        while (!nodes.isEmpty()) {
            node = nodes.pop();
            System.out.println(node.value);
            if (node.right != null) {
                nodes.add(node.right);
            }
            if (node.left != null) {
                nodes.add(node.left);
            }
        }
    }

    public static void middleOrderPrint(Node node) {
        Stack<Node> nodes = new Stack<>();
        Node current = node;
        nodes.push(current);
        while (!nodes.isEmpty()) {
            if (current != null) {
                nodes.push(current);
                current = current.left;
            } else {
                current = nodes.pop();
                System.out.println("current:" + current.value);
                current = current.right;
            }
        }
    }

    public static void lastOrderPrint(Node node) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        if (node != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(node);
            while (!s1.isEmpty()) {
                node = s1.pop(); // 头 右 左
                s2.push(node);
                if (node.left != null) {
                    s1.push(node.left);
                }
                if (node.right != null) {
                    s1.push(node.right);
                }
            }
            // 左 右 头
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

}