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
}
