package containers;

import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("luowen");
        stack.push("maomaa");

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
