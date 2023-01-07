package treemap;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given an integer array nums of length n, and an integer array queries of length m.
 * Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from nums such that the sum of its elements is less than or equal to queries[i].
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: nums = [4,5,2,1], queries = [3,10,21]
 * Output: [2,3,4]
 * Explanation: We answer the queries as follows:
 * - The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
 * - The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
 * - The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
 *
 * Example 2:
 * Input: nums = [2,3,4,5], queries = [1]
 * Output: [0]
 * Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.
 */
public class LengthLongestSubsequenceInArrayLimitedSum {

    // since the subsequence sum is considered here we can sort it here so we get lowest values first.
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        // map of sum -> length
        int sum=0;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
            map.put(sum,i+1);
        }

        int[] result = new int[queries.length];
        for(int i=0;i<queries.length;i++) {
            Map.Entry<Integer,Integer> floorEntry = map.floorEntry(queries[i]);
            if(floorEntry!=null)
                result[i]=floorEntry.getValue();
        }

        return result;
    }
}
