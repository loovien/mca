package algorithm.structs;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/**
 * created 4/25/2021 9:44 AM
 *
 * @author luowen <loovien@163.com>
 */
public class Tripe {
    public static class Node {

        private static final int maxPaths = 26;

        int pass = 0;

        int end = 0;

        Node[] paths = null;

        public Node() {
            this.paths = new Node[maxPaths];
        }
    }

    public static class TripeImpl {

        Node root = new Node();

        public void insert(String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            char[] chars = str.toCharArray();
            Node parent = root;
            parent.pass++;
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (parent.paths[index] == null) {
                    parent.paths[index] = new Node();
                }
                parent = parent.paths[index];
                parent.pass++;
            }
            parent.end++;
        }

        public int search(String str) {
            if (str == null || str.length() <= 0) {
                return -1;
            }
            char[] chars = str.toCharArray();

            Node parent = root;
            for (char ascii : chars) {
                int index = ascii - 'a';
                if (parent.paths[index] == null) {
                    return -1;
                }
                parent = parent.paths[index];
            }
            return parent.end;
        }

        public boolean delete(String str) {
            if (str == null || str.length() <= 0) {
                return false;
            }
            if (search(str) < 0) { // 没有存在， 处理了
                return false;
            }
            char[] chars = str.toCharArray();
            Node parent = root;
            for (char c : chars) {
                int index = c - 'a';
                if (--parent.paths[index].pass == 0) { // delete node
                    parent.paths[index] = null;
                }
                parent = parent.paths[index];
            }
            parent.end--;
            return true;
        }

        public int prefixCnt(String str) {
            if (str == null || str.length() <= 0) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node parent = root;
            for (char c : chars) {
                int index = c - 'a';
                if (parent.paths[index] == null) {
                    return 0;
                }
                parent = parent.paths[index];
            }
            return parent.pass;
        }
    }

    public static String[] getRandomStr(int strLength, int length, int lastDuplicateCnt) {
        String[] strResult = new String[length + lastDuplicateCnt - 1];
        for (int i = 0; i < length; i++) {
            strResult[i] = RandomStringUtils.randomAlphabetic(strLength).toLowerCase(Locale.ROOT);
        }
        for (int i = 0; i < lastDuplicateCnt; i++) {
            strResult[length - 1 + i] = strResult[0];
        }
        return strResult;
    }

    public static int search(String[] data, String dist) {
        int cntResult = 0;
        for (String str : data) {
            if (dist.equals(str)) {
                cntResult++;
            }
        }
        return cntResult;
    }

    public static int prefixCnt(String[] data, String dist) {
        int cntResult = 0;
        for (String str : data) {
            if (StringUtils.indexOf(str, dist) == 0) {
                cntResult++;
            }
        }
        return cntResult;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testTimes; i++) {
            TripeImpl tripe = new TripeImpl();
            String[] randomStr = getRandomStr(random.nextInt(10) + 3, 1000, 3);
            for (String s : randomStr) {
                tripe.insert(s);
            }
            // int i1 = search(randomStr, randomStr[0]);
            // int i2 = tripe.search(randomStr[0]);
            int i1 = prefixCnt(randomStr, "ab");
            int i2 = tripe.prefixCnt("ab");
            System.out.println("i1: " + i1 + " i2: " + i2);
            if (i1 != i2) {
                System.out.println("Oops! fucking fucked !!: repect: " + i1 + " result: " + i2);
                System.out.println(Arrays.toString(randomStr));
                return;
            }
        }
        System.out.println("testing successfully, cost: " + (System.currentTimeMillis() - startTime));
    }
}
