package dynamicprogramming;

import java.util.Arrays;

/**
 * A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits s and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.
 * Given the string s and the integer k, return the number of the possible arrays that can be printed as s using the mentioned program. Since the answer may be very large, return it modulo 109 + 7.
 * Example 1:
 * Input: s = "1000", k = 10000
 * Output: 1
 * Explanation: The only possible array is [1000]
 *
 * Example 2:
 * Input: s = "1000", k = 10
 * Output: 0
 * Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.
 *
 * Example 3:
 * Input: s = "1317", k = 2000
 * Output: 8
 * Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
 */
public class RestoreArray {
    int MOD = 1000000007;
    public int numberOfArrays(String s, int k) {
        long[] cache = new long[s.length()+1];
        Arrays.fill(cache, -1);
        return (int)(dfs(s, k, cache)%MOD);
    }

    private long dfs(String s, int k, long[] cache) {
        if(s.length()==0)
            return 1;

        if(cache[s.length()]!=-1)
            return cache[s.length()];

        long total = 0;
        for(int i=1;i<=s.length();i++) {
            String first = s.substring(0, i);
            String second = s.substring(i);
            if(first.length()<=10 && Long.parseLong(first)<=k && !first.startsWith("0")) {
                total+=dfs(second,k, cache);
            }
        }

        cache[s.length()]=total%MOD;
        return total;
    }

    public static void main(String[] args) {
        RestoreArray restoreArray = new RestoreArray();

        System.out.println(restoreArray.numberOfArrays("1111111111111",1000000000));
    }
}
