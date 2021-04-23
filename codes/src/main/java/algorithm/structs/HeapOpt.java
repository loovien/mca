package algorithm.structs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * created 4/23/2021 3:22 PM
 *
 * @author luowen <loovien@163.com>
 */

public class HeapOpt {
    public static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value: " + value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Node) obj).value == value;
        }
    }

    public static class HeapOptImpl<T> {

        private int heapSize = 0;

        private final ArrayList<T> heap = new ArrayList<>();

        private final Map<T, Integer> indexDb = new HashMap<>();

        private final Comparator<T> comparator;

        public HeapOptImpl(Comparator<T> comparator) {
            this.comparator = comparator;
        }


        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public void push(T data) {
            heap.add(data);
            heapInsert(heapSize);
            indexDb.put(data, heapSize++);
        }

        public T poll() {
            T t = heap.get(0);
            swap(0, --heapSize);
            indexDb.remove(t);
            heap.remove(t);
            heapIfy(0);
            return t;
        }

        public void remove(T obj) {
            T replace = heap.get(heapSize - 1);
            int index = indexDb.get(obj);
            indexDb.remove(obj);
            heap.remove(--heapSize);
            if (obj != replace) {
                heap.set(index, replace);
                indexDb.put(replace, index);
                resign(replace);
            }
        }

        private void resign(T replace) {
            Integer index = indexDb.get(replace);
            heapInsert(index);
            heapIfy(index);
        }

        private void heapIfy(int index) {
            int next = index * 2 + 1;
            while (next < heapSize) {
                int largest = (next + 1 < heapSize && comparator.compare(heap.get(next + 1), heap.get(next)) > 0)
                        ? next + 1 : next;
                if (comparator.compare(heap.get(index), heap.get(largest)) > 0) { // 当前节点比下个节点为大， 就不用交换了
                    break;
                }
                swap(index, largest);
                index = next;
                next = next * 2 + 1;
            }
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        protected void swap(int i, int j) {
            T t = heap.get(i);
            T t1 = heap.get(j);
            indexDb.put(t, j);
            indexDb.put(t1, i);
            heap.set(i, t1);
            heap.set(j, t);
        }
    }


    public static void main(String[] args) {

        HeapGreater<Node> nodeHeap = new HeapGreater<>((o1, o2) -> o1.value - o2.value);
        nodeHeap.push(new Node(1));
        nodeHeap.push(new Node(2));
        nodeHeap.push(new Node(3));
        nodeHeap.push(new Node(4));
        nodeHeap.push(new Node(5));


        /*HeapOptImpl<Node> nodeHeapOpt = new HeapOptImpl<>((o1, o2) -> o1.value - o2.value);
        nodeHeapOpt.push(new Node(1));
        nodeHeapOpt.push(new Node(2));
        nodeHeapOpt.push(new Node(3));
        nodeHeapOpt.push(new Node(4));
        nodeHeapOpt.push(new Node(5));

        System.out.println(nodeHeapOpt.heap);
        nodeHeapOpt.remove(new Node(5));
        nodeHeapOpt.remove(new Node(3));
        System.out.println(nodeHeapOpt.poll());
        System.out.println(nodeHeapOpt.poll());
        System.out.println(nodeHeapOpt.poll());
        System.out.println(nodeHeapOpt.poll());

        System.out.println(nodeHeapOpt.heap);*/
    }
}
