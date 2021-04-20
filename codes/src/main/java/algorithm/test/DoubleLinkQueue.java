package algorithm.test;

import java.sql.PreparedStatement;

/**
 * created 4/20/2021 11:00 AM
 *
 * @author luowen <loovien@163.com>
 */
public class DoubleLinkQueue {
    public static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleLinkImpl<T> {

        private Node<T> head;

        private Node<T> tail;

        private int size;

        public void add(T value) {
            size++;
            Node<T> t = new Node<T>(value);
            if (head == null) {
                head = t;
                tail = t;
                return;
            }
            t.next = head;
            head.prev = t;
            head = t;
        }

        public void addRight(T value) {
            size++;
            Node<T> t = new Node<>(value);
            if (head == null) {
                head = t;
                tail = t;
                return;
            }
            t.prev = tail;
            tail.next = t;
            tail = t;
        }

        public T pop() {
            size--;
            Node<T> result = head;
            if (result == null) {
                return null;
            }
            head = head.next;
            return result.value;
        }

        public T popRight() {
            size--;
            Node<T> result = tail;
            if (result == null) {
                return null;
            }

            tail = tail.prev;
            return result.value;
        }

        public boolean isEmpty() {
            return size <= 0;
        }
    }

    public static void main(String[] args) {

        DoubleLinkImpl<Integer> integerDoubleLink = new DoubleLinkImpl<>();
        integerDoubleLink.add(1);
        integerDoubleLink.add(2);
        integerDoubleLink.add(3);
        integerDoubleLink.add(4);

        while (!integerDoubleLink.isEmpty()) {
            System.out.println(integerDoubleLink.popRight());
            //System.out.println(integerDoubleLink.pop());
        }
    }
}
