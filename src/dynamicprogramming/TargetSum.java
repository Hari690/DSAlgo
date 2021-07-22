package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/***
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate
 * all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 */
public class TargetSum {
    int max = 0;
    public int findTargetSumWays(int[] nums, int target) {
        return checkSum(0,target,nums,new HashMap<>());
    }

    private int checkSum(int index, int sum, int[] nums, HashMap<String, Integer> map) {
        // only for the duplicate branches of recursion.
        if(map.containsKey(index+"-"+sum))
            return map.get(index);

        if(index==nums.length) {
            if(sum==0) {
                return 1;
            }
            return 0;
        }


        int positive = checkSum(index+1,sum-nums[index], nums, map);
        int negative = checkSum(index+1,sum+nums[index], nums, map);

        map.put(index+"-"+sum, positive+negative);
        return positive + negative;
    }


    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        int[] nums = {1,1,1,1,1};

        System.out.println(targetSum.findTargetSumWays(nums, 3));
    }
}
