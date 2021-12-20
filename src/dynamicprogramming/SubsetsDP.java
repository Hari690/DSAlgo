package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class SubsetsDP {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = new SubsetsDP().subsets(nums);
        System.out.println(subsets);
    }

    /*
        Iterate over the numbers and keep adding them to all the existing subarrays.
        BFS
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }

    public List<List<Integer>> subsetsBacktracking(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> list = new ArrayList();
        backtrack(nums, 0, list, output);
        return output;
    }

    private void backtrack(int[] nums, int index, List<Integer> list, List<List<Integer>> output) {
        if(index==nums.length) {
            output.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[index]);
        backtrack(nums, index+1, list, output);
        list.remove(list.size()-1);
        backtrack(nums, index+1, list, output);
    }
}
