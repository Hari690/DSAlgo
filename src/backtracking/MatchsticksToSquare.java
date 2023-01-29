package backtracking;

import java.util.Arrays;

/**
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the
 * matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one
 * time.
 *
 * Return true if you can make this square and false otherwise.
 */
/*
Loop using the sides of the square rather than matchstick array to make it simpler.
Sorting the input array DESC will make the DFS process run much faster. Reason behind
 this is we always try to put the next matchstick in the first subset.
 If there is no solution, trying a longer matchstick first will get to negative conclusion earlier.
 Following is the updated code. Runtime is improved from more than 1000ms to around 40ms.
  A big improvement.
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;

        Arrays.sort(nums);

        reverse(nums);

        // no visited set, just permutation of combinations
        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[index] > target) continue;
            sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
            sums[i] -= nums[index];
        }

        return false;
    }

    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++; j--;
        }
    }

    public static void main(String[] args) {
        MatchsticksToSquare matchsticksToSquare = new MatchsticksToSquare();
        //int[] arr = {1,1,2,2,2};
        int[] arr ={5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(matchsticksToSquare.makesquare(arr));
    }
}
