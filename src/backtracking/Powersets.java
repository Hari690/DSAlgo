package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Powersets {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        new Powersets().subsets(nums);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        subsets(nums, 0, subset, subsets);
        System.out.println(subsets);
        return subsets;
    }

    private void subsets(int[] nums, int index, List<Integer> subset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(subset));

        for(int i=index;i<nums.length;i++) {
            if(!subset.contains(nums[i])) {
                subset.add(nums[i]);
                subsets(nums, index+1, subset, subsets);
                subset.remove(subset.size()-1);
            }
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {

        List<List<Integer>> finalOutput = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        finalOutput.add(output);
        List<Integer> newList = null;

        for( int i=0;i<nums.length;i++) {
            for(List<Integer> list: new ArrayList<>(finalOutput)) {
                newList = new ArrayList<>(list);
                newList.add(nums[i]);
                finalOutput.add(newList);
            }
        }
        return finalOutput;
    }
}
