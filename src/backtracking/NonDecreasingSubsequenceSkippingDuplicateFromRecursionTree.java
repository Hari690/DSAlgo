package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * Example 2:
 *
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 *
 */
public class NonDecreasingSubsequenceSkippingDuplicateFromRecursionTree {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList();

        dfs(0, nums, list, result);

        List<List<Integer>> output = new ArrayList<>();
        for(List<Integer> list1 : result)
            output.add(list1);

        return output;
    }

    private void dfs(int index, int[] nums, List<Integer> list, Set<List<Integer>> result) {
        if(index==0 || index==nums.length || (list.size()>=2 && list.get(list.size()-1)<list.get(list.size()-2))) {
            if(index!=0) {
                if (list.size() >= 2 && list.get(list.size()-1)>=list.get(list.size()-2))
                    result.add(new ArrayList<>(list));
                return;
            }
        }

        if(list.size()>=2 && index!=nums.length-1)
            result.add(new ArrayList<>(list));

        dfs(index+1, nums, list, result);
        list.add(nums[index]);
        dfs(index+1, nums, list, result);
        list.remove(list.size()-1);
    }

    /*
        A possible improvement: instead of using a global set to remove duplication in the final results, we can maintain
        a local set at each step. The principle is that at each step, a new value can only be picked once.
        The advantage of a local set is that it can filter out the potential repetitions just at the beginning
        instead of at the end of a sub-sequence building. For example, [1, 1, 9, 3, 6]
        So 1,1,3 will be handled in recursion and loop but we want to account fot it only in recursion and not in loop at same recursion level.
        . With a global set, we have to filter all the sequences starting at the 2nd 1 since they
        will certainly duplicate with the results beginning with the 1st 1. However, with a local set, at the first step, we will only choose the 1st 1 for sequence building and the 2nd 1 is excluded just at the first step.
        Of course a local set at each step will lead to extra costs. However,
        I think it can improve the efficiency in general, especially for an array with many repetitions, such as [1, 1, 1, 1, 1, 1, 3]
     */
    public List<List<Integer>> findSubsequencesLocalSet(int[] nums) {
        // we cannot sort array first, sequence matters
        List<List<Integer>> res = new ArrayList<>();
        search(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void search(int[] nums, List<List<Integer>> res, List<Integer> list, int pos) {
        if(list.size() >= 2) {
            res.add(new ArrayList<>(list));
        }
        Set<Integer> visited = new HashSet<>(); // local set to de-duplicate
        for(int i = pos; i < nums.length; i++) {
            if(visited.contains(nums[i])) continue;
                visited.add(nums[i]);
            if(list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
                list.add(nums[i]);
                search(nums, res, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        NonDecreasingSubsequenceSkippingDuplicateFromRecursionTree nonDecreasingSubsequence = new NonDecreasingSubsequenceSkippingDuplicateFromRecursionTree();

        int[] nums = {1,2,1,1,1,1,1};
        List<List<Integer>> result = nonDecreasingSubsequence.findSubsequences(nums);
        result.forEach(s -> {
            s.forEach(System.out::print);
            System.out.println();
        });

    }
}
