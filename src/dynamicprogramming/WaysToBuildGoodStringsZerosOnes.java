package dynamicprogramming;

import java.util.Arrays;

/**
 * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
 * Append the character '0' zero times.
 * Append the character '1' one times.
 * This can be performed any number of times.
 *
 * A good string is a string constructed by the above process having a length between low and high (inclusive).
 * Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: low = 3, high = 3, zero = 1, one = 1
 * Output: 8
 * Explanation:
 * One possible valid good string is "011".
 * It can be constructed as follows: "" -> "0" -> "01" -> "011".
 * All binary strings from "000" to "111" are good strings in this example.
 *
 * Example 2:
 * Input: low = 2, high = 3, zero = 1, one = 2
 * Output: 5
 * Explanation: The good strings are "00", "11", "000", "110", and "011".
 */
public class WaysToBuildGoodStringsZerosOnes {
    int MOD = 1000000007;
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] cache = new int[high+1];
        Arrays.fill(cache, -1);
        return countString(low, high, zero, one, 0, cache);
    }

    private int countString(int low, int high, int zero, int one, int len, int[] cache) {
        if(len>high) {
            return 0;
        }

        if(cache[len]!=-1)
            return cache[len];

        int count = 0;
        if(len>=low && len<=high) {
            count++;
        }

        cache[len] = (count+countString(low,high,zero,one,len+zero,cache)+
                countString(low,high,zero,one,len+one,cache))%MOD;
        return cache[len];
    }
}
