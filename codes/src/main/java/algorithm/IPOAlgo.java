package algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * created 5/13/2021 11:08 AM
 *
 * @author luowen <loovien@163.com>
 */
public class IPOAlgo {
    /**
     * 获取最大利润问题： 给定项目集合， 项目包括花费金币和获取利润金币。 【1，1】， 【3，5】， 【2，1】， 【10，3】,
     * 给定你有W本金， 通知做的项目为K个， 也就是你可以把W分成多份， 通知做多个项目，然后获利润后再做项目。
     */
    public static class Proj {
        public int cost;

        public int profit;

        public Proj(int cost, int profiles) {
            this.cost = cost;
            this.profit = profiles;
        }

        @Override
        public String toString() {
            return "cost=" + cost + ", profiles=" + profit;
        }
    }

    public static Proj[] getRandomProjs(int length) {
        Proj[] projs = new Proj[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            projs[i] = new Proj(random.nextInt(10) + 1, random.nextInt(10));
        }
        return projs;
    }


    /**
     * 贪心策略：先做能做利润做大的项目
     */
    public static void main(String[] args) {
        int profit = maxProfitCalc(5, 2);
        System.out.println(profit);
        int i = maxProfitCalc(3, 2);
        System.out.println(i);
    }

    private static int maxProfitCalc(int w, int k) {
        Proj[] randomProjs = getRandomProjs(3);
        System.out.println(Arrays.toString(randomProjs));

        PriorityQueue<Proj> canDo = new PriorityQueue<>();
        PriorityQueue<Proj> maxProfit = new PriorityQueue<>((prev, next) -> next.profit - prev.profit);
        canDo.addAll(Arrays.asList(randomProjs));
        for (int i = 0; i < k; i++) {
            while (!canDo.isEmpty() && canDo.peek().cost <= w) {
                maxProfit.add(canDo.poll());
            }
            if (maxProfit.isEmpty()) {
                return w;
            }
            w += maxProfit.poll().profit;
        }
        return w;
    }
}
