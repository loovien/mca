package algorithm.test;

import java.util.Stack;

/**
 * created 4/20/2021 12:42 PM
 *
 * @author luowen <loovien@163.com>
 */
public class Stack2Queue {

    public static class Stack2QueueImpl {
        private Stack<Integer> data = new Stack<>();
        private Stack<Integer> help = new Stack<>();

        public void add(Integer a) {
            data.push(a);
            reBuild();
        }

        public Integer take() {
            reBuild();
            return help.pop();
        }

        private void reBuild() {
            if (!help.empty()) {
                return;
            }
            while (!data.empty()) {
                help.push(data.pop());
            }
        }

    }

    public static void main(String[] args) {
        Stack2QueueImpl stack2Queue = new Stack2QueueImpl();
        stack2Queue.add(1);
        stack2Queue.add(2);
        stack2Queue.add(3);
        stack2Queue.add(4);
        for (int i = 0; i < 4; i++) {
            System.out.println(stack2Queue.take());
        }
    }

}
