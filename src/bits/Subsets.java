package bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = new Subsets().subsets(nums);
        System.out.println(subsets);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        for(int i=1;i<=Math.pow(2,nums.length);i++) { // 8
            List<Integer> result = new ArrayList<>();
            for(int k=0;k<nums.length;k++) { //3
                if((i&(1<<k))>0) { // check if bit in k'th position is set
                    result.add(nums[k]);
                }
            }
            output.add(result);
        }
        return output;
    }

    public List<List<Integer>> subsetsBacktracking(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(0, nums, list, output);
        return output;
    }

    private void backtrack(int i, int[] nums, List<Integer> list, List<List<Integer>> output) {
        if(i==nums.length) {
            output.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[i]);
        backtrack(i+1, nums, list, output);
        list.remove(list.size()-1);
        backtrack(i+1, nums, list, output);
    }
}
