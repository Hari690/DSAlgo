package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 */
public class Permutation {

    public static void main(String[] args) {
        int[] nos = {1,2,3};
        System.out.println(permute(nos));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();
        helperFunction(output, temp,new HashSet<>(), nums);
        return output;
    }

    public static void helperFunction(List<List<Integer>> result, List<Integer> current, Set<Integer> tmpSet, int [] nums) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i=0; i<nums.length; i++) {
            if(!tmpSet.contains(nums[i])) {
                current.add(nums[i]);
                tmpSet.add(nums[i]);
                helperFunction(result, current, tmpSet, nums);
                tmpSet.remove(current.get(current.size() - 1));
                current.remove(current.size() - 1);
            }
        }
    }
}
