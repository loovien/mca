package algorithm.island;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

final public class UnionQuery<V> {
    private final Map<V, Node<V>> nodes = new HashMap<>();

    private final Map<Node<V>, Integer> size = new HashMap<>();

    private final Map<Node<V>, Node<V>> parents = new HashMap<>();

    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public UnionQuery(Iterable<V> data) {
        for (V datum : data) {
            Node<V> node = new Node<V>(datum);
            nodes.put(datum, node);
            size.put(node, 1);
            parents.put(node, node);
        }
    }

    public Node<V> query(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        Stack<Node<V>> stack = new Stack<>();
        while (node != parents.get(node)) {
            stack.push(node);
            node = parents.get(node);
        }
        while (!stack.isEmpty()) { // 变更 parent 节点
            parents.put(stack.pop(), node);
        }
        return node;
    }

    public boolean isUnion(V a, V b) {
        return query(a) == query(b);
    }

    public void unionIt(V a, V b) {
        Node<V> nodeA = query(a);
        Node<V> nodeB = query(b);
        if (nodeA == null || nodeB == null) {
            throw new IllegalArgumentException("a or b not in sets");
        }
        if (nodeA == nodeB) {
            return;
        }
        Integer aRefCnt = size.get(nodeA);
        Integer bRefCnt = size.get(nodeB);
        Node<V> newTop = aRefCnt >= bRefCnt ? nodeA : nodeB;
        Node<V> newDown = newTop == nodeA ? nodeB : nodeA;

        parents.put(newDown, newTop);
        size.put(newTop, aRefCnt + bRefCnt);
        size.remove(newDown);
    }

    public int getResult() {
        return size.size();
    }
}
