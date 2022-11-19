package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return an array of all the integers of length n where the difference between every two consecutive digits is k. You may return the answer in any order.
 * Note that the integers should not have leading zeros. Integers as 02 and 043 are not allowed.
 *
 * Example 1:
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 */
public class NumbersWithSameConsecutiveDifference {
    private void dfs(int num, int N, int K, List<Integer> result){
        if(N == 0){
            result.add(num);
            return;
        }
        int last_digit = num%10;
        if(last_digit >= K) dfs(num*10 + last_digit - K, N-1, K, result);
        if(K > 0 && last_digit + K < 10) dfs(num*10 + last_digit + K, N-1, K, result);
    }

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> result = new ArrayList();
        if(N == 1) result.add(0);
        for(int d = 1; d < 10; ++d)
            dfs(d, N-1, K, result);

        return result.stream().mapToInt(i->i).toArray();
    }
}
