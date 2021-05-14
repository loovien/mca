package algorithm;

import java.util.HashMap;
import java.util.Stack;

/**
 * created 5/13/2021 1:52 PM
 *
 * @author luowen <loovien@163.com>
 */
public class UnionFindAlgo {

    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    public static class Union<V> {
        HashMap<Node<V>, Integer> size = new HashMap<>();
        HashMap<Node<V>, Node<V>> parents = new HashMap<>();
        HashMap<V, Node<V>> nodes = new HashMap<>();

        public Union(V[] data) {
            for (V datum : data) {
                Node<V> node = new Node<>(datum);
                size.put(node, 1);
                parents.put(node, node);
                nodes.put(datum, node);
            }
        }

        public Node<V> getTop(V a) {
            Node<V> node = nodes.get(a);
            Stack<Node<V>> vs = new Stack<>();
            while (node != parents.get(node)) {
                vs.add(node);
                node = parents.get(node);
            }
            while (!vs.isEmpty()) {
                parents.put(vs.pop(), node);
            }
            return node;
        }

        public boolean isInSet(V a, V b) {
            return getTop(a) == getTop(b);
        }

        public boolean union(V a, V b) {
            Node<V> node1 = getTop(a);
            Node<V> node2 = getTop(b);
            if (node1 == node2) {
                return false;
            }
            Integer size1 = size.get(node1);
            Integer size2 = size.get(node2);
            Node<V> newTop = size1 >= size2 ? node1 : node2;
            Node<V> newDown = newTop == node1 ? node2 : node1;
            parents.put(newDown, newTop);
            size.put(newTop, size1 + size2);
            size.remove(newDown);
            return true;
        }

        public int getNum() {
            return size.size();
        }
    }


    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Union<Integer> unions = new Union<Integer>(a);
        boolean inSet = unions.isInSet(1, 2);
        System.out.println(inSet);
        unions.union(1, 2);
        unions.union(1, 4);
        unions.union(1, 5);
        unions.union(1, 7);
        unions.union(1, 9);
        System.out.println("1 with 2: " + unions.isInSet(1, 2));
        System.out.println("set size: " + unions.getNum());
        System.out.println(unions.parents);
    }


}