package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 */
public class Subsets2WithDuplicates {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        set.add(new ArrayList<>());

        for(int num : nums) {
            Set<List<Integer>> newSet = new HashSet<>(set);
            for(List<Integer> list : set) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(num);
                newSet.add(newList);
            }
            set=newSet;
        }

        List<List<Integer>> result = new ArrayList<>();

        for(List<Integer> list : set) {
            result.add(list);
        }

        return result;
    }

    //backtrack
    public List<List<Integer>> subsetsWithDupBacktrack(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();
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
        // we have already considered this value in previous case so we won't consider same value again while
        // excluding the current element since it would lead to duplicates.
        // think of it in the context of single duplicate element 2.
        while((index+1)<nums.length && nums[index]==nums[index+1]) {
            index++;
        }
        backtrack(nums, index+1, list, output);
    }
}
