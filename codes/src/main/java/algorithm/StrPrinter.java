package algorithm;

import java.util.ArrayList;
import java.util.Stack;

public class StrPrinter {
    public static void main(String[] args) {
        // printAllSubStr();
        // printAllPermutation();
        reverseStack();
    }

    private static void reverseStack() {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        process(stack);
        System.out.println(stack);
    }

    private static void process(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer f = f(stack);
        process(stack);
        stack.push(f);
    }

    private static void p2(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer result = f(stack);
        p2(stack);
        stack.push(result);
    }

    private static Integer f(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        Integer last = f(stack);
        stack.push(pop);
        return last;
    }

    private static void printAllPermutation() {
        char[] chars = "abc".toCharArray();
        ArrayList<String> result = new ArrayList<>();
        p1(chars, 0, result);
        System.out.println(result);
    }

    private static void p1(char[] chars, int index, ArrayList<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            p1(chars, index + 1, result);
            swap(chars, i, index);
        }
    }

    private static void swap(char[] data, int i, int j) {
        char datum = data[i];
        data[i] = data[j];
        data[j] = datum;
    }

    public static void printAllSubStr() {
        char[] chars = "abc".toCharArray();
        ArrayList<String> characters = new ArrayList<>();
        subPrinter(chars, "", 0, characters);
        System.out.println(characters);
    }

    private static void subPrinter(char[] chars, String s, int index, ArrayList<String> characters) {
        if (index == chars.length) {
            characters.add(s);
            return;
        }
        subPrinter(chars, s, index + 1, characters);
        subPrinter(chars, s + chars[index], index + 1, characters);
    }
}
