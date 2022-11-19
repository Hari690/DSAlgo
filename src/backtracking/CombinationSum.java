package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    /**
     * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates
     * where the chosen numbers sum to target. You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at
     * least one of the chosen numbers is different.
     *
     * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
     */

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();

        combinationSum(0, 0, candidates, target, temp, output);
        return output;
    }


    public static void combinationSum(int index, int sum, int[] candidates, int target, List<Integer> temp, List<List<Integer>> output) {
        if( target==sum ) {
            output.add(new ArrayList<>(temp));
            return;
        }

        if(sum>target || index==candidates.length) {
            return;
        }
        temp.add(candidates[index]);
        combinationSum(index,sum+candidates[index],candidates, target, temp, output);
        temp.remove(temp.size()-1);
        combinationSum(index+1,sum,candidates, target, temp, output);
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        System.out.println(combinationSum(candidates, 8));
    }

}
