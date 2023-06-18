package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
 *
 * In the ith operation (1-indexed), you will:
 *
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 *
 * The function gcd(x, y) is the greatest common divisor of x and y.
 *
 * Example 1:
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 2)) = 1
 *
 * Example 2:
 * Input: nums = [3,4,6,8]
 * Output: 11
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 *
 * Example 3:
 * Input: nums = [1,2,3,4,5,6]
 * Output: 14
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *
 * Constraints:
 *
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 106
 */
public class MaxScoreAfterNOperationsOfGCDInArray {

    /*
        Based on the constraints of the question we can do bitmasking to store intermediate state of array and try all combinations.
        (1<<i & state)!=0 this checks i bit is set, similar for j.
        Store temp value of state in the array to restore for the next iteration.
     */
    public int maxScore(int[] nums) {
        int state = 0;
        Map<Integer, Integer> cache = new HashMap<>();
        return helper(state, nums, cache, 1);
    }
    int helper(int state, int[] nums, Map<Integer, Integer> cache, int times) {
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if((1<<i & state)!=0 || (1<<j & state)!=0)
                    continue;

                int tempState = state;
                state = state | 1<<i | 1<<j;
                int score = times * gcd(nums[j], nums[i]);
                max = Math.max(max, score + helper(state, nums, cache, times+1));
                state = tempState;
            }
        }
        cache.put(state, max);
        return max;
    }
    int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    public static void main(String[] args) {
        MaxScoreAfterNOperationsOfGCDInArray maxScoreAfterNOperations = new MaxScoreAfterNOperationsOfGCDInArray();

        System.out.println(maxScoreAfterNOperations.maxScore(new int[]{3,4,6,8}));
    }
}
