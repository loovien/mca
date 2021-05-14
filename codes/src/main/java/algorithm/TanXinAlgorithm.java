package algorithm;

import java.util.Arrays;
import java.util.Random;

public class TanXinAlgorithm {

    /**
     * 会议室开会时间预定， 开始时间， 结束时间， 【1，3】，【2,8】， 【3，5】， 【5，7】， 【1，8】
     * <p>
     * 选择连续最多能安排最多的会议
     */
    public static class Entry {
        public int start;

        public int end;

        public Entry(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    public static Entry[] getRandom(int length) {
        Entry[] entries = new Entry[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int prev = random.nextInt(12);
            entries[i] = new Entry(prev, prev + random.nextInt(12) + 1);
        }
        return entries;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Entry[] a = getRandom(3);
            int violence2 = violence(a, 0, 0);
            int violence1 = planMeetingRoom(a);
            if (violence1 != violence2) {
                System.out.println(Arrays.toString(a));
                System.out.println("Oops!! method: " + violence1 + " method2: " + violence2);
                return;
            }
        }
        System.out.println("successfully");
    }

    public static int planMeetingRoom(Entry[] data) {
        if (data == null || data.length <= 0) {
            return 0;
        }
        /*
            按照结束时间最小，去贪心算法
         */
        Arrays.sort(data, (prev, next) -> {
            if (prev.end == next.end) {
                return prev.start - next.start;
            }
            return prev.end - next.end;
        });
        int max = 1, end = data[0].end;
        for (int i = 1; i < data.length; i++) {
            if (data[i].start >= end) {
                max++;
                end = data[i].end;
            }
        }
        return max;
    }

    public static int x(Entry[] programs, int done, int dateline) {

        if (programs.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= dateline) {
                Entry[] next = exceptEntity(programs, i);
                max = Math.max(max, x(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    /**
     * 递归暴力解法
     *
     * @param data     数据
     * @param done     最大
     * @param dateline 当前时间
     * @return 结果
     */
    public static int violence(Entry[] data, int done, int dateline) {
        if (data == null || data.length <= 0) {
            return done;
        }

        int max = done;
        for (int i = 0; i < data.length; i++) {
            if (data[i].start >= dateline) {
                Entry[] newData = exceptEntity(data, i);
                max = Math.max(max, violence(newData, done + 1, data[i].end));
            }
        }
        return max;
    }

    private static Entry[] exceptEntity(Entry[] data, int i) {
        Entry[] entries = new Entry[data.length - 1];
        int cursor = 0;
        for (int j = 0; j < data.length; j++) {
            if (j == i) {
                continue;
            }
            entries[cursor++] = data[j];
        }
        return entries;
    }
}
