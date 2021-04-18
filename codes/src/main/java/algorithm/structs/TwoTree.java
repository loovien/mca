package algorithm.structs;

import java.util.HashMap;
import java.util.Map;

public class TwoTree {
    public static class Node {
        Node left, right;
        Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static Map<Integer, Integer> data = new HashMap<>();

    public static void main(String[] args) {
        int[] left = {1, 2, 4, 5, 3, 6, 7};
        int[] right = {4, 2, 5, 1, 6, 3, 7};

        for (int i = 0; i < right.length; i++) {
            data.put(right[i], i);
        }

        Node node = buildTree(left, 0, left.length - 1, right, 0, right.length - 1);
        printTree(node);

    }

    /**
     * {1, | 2, 4, 5,      |   |      3, 6, 7};
     * {     4, 2, 5,     | 1 |,     6, 3, 7};
     */
    public static Node buildTree(int[] left, int L1, int R1, int[] right, int L2, int R2) {
        if (left == null || right == null || left.length != right.length) {
            return null;
        }
        if (L1 > R1) { // 左边或右边没有节点, 前序头节点和中序头节点都是第一个
            return null;
        }
        if (L1 == R1) { // 就剩下最后一个节点了
            return new Node(left[L1]);
        }
        Node node = new Node(left[L1]);
        Integer middle = data.get(left[L1]);
        node.left = buildTree(left, L1 + 1, L1 + middle - L2, right, L2, middle);
        node.right = buildTree(left, L1 + middle - L2 + 1, R1, right, middle + 1, R2);
        return node;
    }

    public static void printTree(Node node) {
        if (node == null) {
            return;
        }
        printTree(node.left);
        System.out.println(node.value);
        printTree(node.right);
    }


}
