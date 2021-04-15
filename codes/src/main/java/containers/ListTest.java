package containers;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ListTest {

    private static final Map<Integer, Integer> hashtable = new Hashtable<>();

    private static final Map<Integer, Integer> hashmap = new HashMap<>();

    private static final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    private static final long maxLoop = 10_000_000;

    public static void main(String[] args) {


        fillMap(hashtable);
        fillMap(hashmap);
        fillMap(concurrentHashMap);

        readMap(hashtable);
        readMap(hashmap);
        readMap(concurrentHashMap);

    }


    protected static void fillMap(Map<Integer, Integer> map) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < maxLoop; i++) {
            map.put(i, i);
        }
        System.out.println(map.getClass().getName() + " write cost: " + (System.currentTimeMillis() - l));
    }

    public static void readMap(Map<Integer, Integer> map) {
        long now = System.currentTimeMillis();
        for (int i = 0; i < maxLoop / 10; i++) {
            map.get(i);
        }
        System.out.println(map.getClass().getName() + " read cost: " + (System.currentTimeMillis() - now));
    }

}
