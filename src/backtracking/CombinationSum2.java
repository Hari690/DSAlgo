package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where
 * the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * sorted: [1,1,2,5,6,7,10]
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs_com(cand, 0, target, path, res);
        return res;
    }

    /*
        Another option is to use visited array with set
     */
    void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return ;
        }
        if (target < 0) return;
        for (int i = cur; i < cand.length; i++){
            // At the same level of recursion i.e when cur is different from i we need to skip if
            // current element is same as previous element. In the example provided we keep a 1 and then we skip
            // all 1's for the branch without 1.
            // i.e the for loop will pick only one of a no and initiate recursion and that recursion will take care of duplicates.
            if (i > cur && cand[i] == cand[i-1]) continue;
            path.add(path.size(), cand[i]);
            dfs_com(cand, i+1, target - cand[i], path, res);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        CombinationSum2 solution = new CombinationSum2();

        int[] nos = {10,1,2,7,6,1,5};
        solution.combinationSum2(nos, 8);
    }
}
