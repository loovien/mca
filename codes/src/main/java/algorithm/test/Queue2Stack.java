package algorithm.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created 4/20/2021 12:47 PM
 *
 * @author luowen <loovien@163.com>
 */
public class Queue2Stack {
    public static class Queue2StackImpl {
        private Queue<Integer> queue = new LinkedList<>();
        private Queue<Integer> help = new LinkedList<>();

        public void add(Integer a) {
            queue.add(a);
        }

        public Integer poll() {
            int result = -1;
            while (!queue.isEmpty()) {
                if (queue.size() == 1) {
                    result = queue.poll();
                    break;
                }
                help.add(queue.poll());
            }
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return result;
        }
    }

    public static void main(String[] args) {
        Queue2StackImpl queue2Stack = new Queue2StackImpl();
        queue2Stack.add(1);
        queue2Stack.add(2);
        System.out.println(queue2Stack.poll());
        queue2Stack.add(3);
        queue2Stack.add(4);

        for (int i = 0; i < 3; i++) {
            System.out.println(queue2Stack.poll());
        }

    }
}
