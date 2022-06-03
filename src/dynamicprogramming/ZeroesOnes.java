package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Example 1:
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 * Example 2:
 *
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 */
public class ZeroesOnes {
    class Pair {
        int count0;
        int count1;
        Pair(int count0, int count1) {
            this.count0=count0;
            this.count1=count1;
        }
    }

    public int findMaxForm(String[] strs, int m, int n) {
        List<Pair> list = new ArrayList<>();

        // since we will need to calculate this multiple times we can do it in the beginning.
        for(String str : strs) {
            Pair result = findCurrCount(str);
            list.add(result);
        }

        int[][][] dp=new int[strs.length][m+1][n+1];
        for(int i=0;i<strs.length;i++)
            for(int j=0;j<m+1;j++)
                Arrays.fill(dp[i][j], -1);

        return findMax(list, m, n, 0, dp);
    }

    private int findMax(List<Pair> list, int m, int n, int index, int[][][] dp) {
        if((m<=0 && n<=0) || index==list.size()) {
            return 0;
        }

        if(dp[index][m][n]!=-1)
            return dp[index][m][n];

        // If this condition is not there, indexes will become negative.
        if(list.get(index).count0 > m || list.get(index).count1 > n)
            return findMax(list , m , n, index+1, dp);

        int max1 = 1+findMax(list, m-list.get(index).count0, n-list.get(index).count1, index+1, dp);
        int max2 = findMax(list, m, n, index+1, dp);
        dp[index][m][n] = Math.max(max1, max2);
        return dp[index][m][n];
    }


    private Pair findCurrCount(String s) {
        int count0=0;
        int count1=0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)-'0'==0)
                count0++;
            else
                count1++;
        }
        return new Pair(count0, count1);
    }
}
