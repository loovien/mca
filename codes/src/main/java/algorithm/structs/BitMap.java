package algorithm.structs;

import java.util.Arrays;

public class BitMap {

    public static class BitMapImpl {

        Integer maxValue;

        long[] database;

        public BitMapImpl(Integer maxValue) {
            this.maxValue = maxValue;
            database = new long[(maxValue + 64) >> 6];
        }

        public void add(Integer value) {
            database[value >> 6] |= 1L << (value & 63);
        }

        public void delete(Integer value) {
            database[value >> 6] &= ~(1L << (value & 63));
        }

        public boolean contains(Integer value) {
            return (database[value >> 6] & (1L << (value & 63))) > 0;
        }

    }

    public static void main(String[] args) {
        BitMapImpl bitMap = new BitMapImpl(100);
        bitMap.add(10);
        bitMap.add(12);
        bitMap.add(15);

        System.out.println(bitMap.contains(10));
        bitMap.delete(10);
        System.out.println(bitMap.contains(10));

    }

}
