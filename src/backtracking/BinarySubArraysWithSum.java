package backtracking;

import java.util.ArrayList;
import java.util.List;

public class BinarySubArraysWithSum {
    int total = 0;
    public int numSubarraysWithSum(int[] nums, int goal) {
        check(nums, goal, 0, 0, new ArrayList<>());
        return total;
    }

    private void check(int[] nums, int goal, int sum, int index, List<Integer>list) {
        if(sum==goal) {
            total++;
        }


        if(sum>goal)
            return;

        for(int i=index;i<nums.length;i++) {
            list.add(nums[i]);
            check(nums, goal, sum+nums[i], i+1, list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        BinarySubArraysWithSum solution = new BinarySubArraysWithSum();

        int[] nums = {1,0,1,0,1};
        solution.numSubarraysWithSum(nums, 2);
    }
}
