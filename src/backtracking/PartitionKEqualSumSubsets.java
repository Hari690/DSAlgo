package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums and an integer k,
 * return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 */
public class PartitionKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        List<Integer> set = new ArrayList<>();
        if (k > nums.length) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, 0, k, visited, sum/k, 0, 0);
    }


    public boolean dfs(int[] nums,int sum, int k, boolean[] visited, int val, int total, int index) {
        if(sum==val) {
            total++;
            return dfs(nums,0,k,visited,val, total, 0);
        }
        if(total==k) {
            return true;
        }
        for(int i=index; i<nums.length; i++) {
            if(!visited[i] && sum+nums[i]<=val) {
                visited[i] = true;
                if(dfs(nums,sum+nums[i],k,visited,val, total, i+1))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }
}
