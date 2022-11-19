package array;

import java.util.HashMap;
import java.util.Map;

/**
 * A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.
 * You can pick any two different foods to make a good meal.
 * Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the i​​​​​​th​​​​​​​​ item of food, return the number of different good meals you can make from this list modulo 109 + 7.
 * Note that items with different indices are considered different even if they have the same deliciousness value.
 *
 */
public class TwoSumCountNumbersThatAddToTwosPower {

    /*
        Basic idea is somewhat similar to that of two sum, where we store the count of all encountered nums in a hashmap.
        Then for each num, we go through all powers of twos in the integer range (22 iterations total), and try to find the corresponding entry in the map.

     */
    int mod = 1000000007;
    public int countPairs(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        long res = 0;
        for (int num : arr) {
            int power = 1;
            for (int i = 0; i < 22; i++) {
                if (map.containsKey(power - num)) {
                    res += map.get(power - num);
                    res %= mod;
                }
                power *= 2;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return (int) res;
    }
}
