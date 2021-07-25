package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * created 5/17/2021 9:40 AM
 *
 * @author luowen <loovien@163.com>
 */
public class StrPrint {
    private StrPrint b = new StrPrint();

    public static void main(String[] args) {
        new StrPrint();
//        String str = "luo";
//        char[] chars = str.toCharArray();
//
//        ArrayList<String> result = new ArrayList<>();
//        process1(chars, 0, "", result);
//        System.out.println(result);
    }

    private static void process1(char[] chars, int i, String s, List<String> result) {
        if (i == chars.length) {
            result.add(s);
            return;
        }
        process1(chars, i + 1, s, result);
        process1(chars, i + 1, s + chars[i], result);
    }

    public static class Demo {
        Demo() {
            Demo demo = new Demo();
            System.out.println("==========" + demo);
        }
    }

}
