package algorithm.structs;

/**
 * created 4/16/2021 10:34 AM
 *
 * @author luowen <loovien@163.com>
 */
public class SingleDirectLink {

    static class Node {
        public Node next;
        public String value;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node a = new Node("a");
        a.next = new Node("b");
        a.next.next = new Node("c");

        /*while (a != null) {
            System.out.println(a.value);
            a = a.next;
        }*/

        Node rev = rev(a);

        System.out.println(rev);

        while (rev != null) {
            System.out.println(rev.value);
            rev = rev.next;
        }
    }

    /**
     * a -> b -> c
     *
     * @param head
     * @return
     */
    public static Node rev(Node head) {
        Node prev = null, next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
