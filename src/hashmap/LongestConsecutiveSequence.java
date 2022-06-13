package hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for(int num: nums) set.add(num);
        int max = 1;
        for(int num: nums) {
            if(!set.contains(num-1)) {
                int currLength = 0;
                while(set.contains(num)) {
                    currLength++;
                    max = Math.max(max,currLength);
                    num++;
                }
            }
        }
        return max;
    }
}
