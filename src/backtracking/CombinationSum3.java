package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be
 * returned in any order.
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> output = new ArrayList<>();

        backtrack(result, output, 1, 0, n, k);

        return output;

    }

    private void backtrack(List<Integer> result, List<List<Integer>> output, int i, int sum, int n, int k) {
        if(sum==n && result.size()==k) {
            output.add(new ArrayList<>(result));
            return;
        }

        if(i>9 || result.size()>k || sum>n)
            return;

        result.add(i);
        backtrack(result, output, i+1, sum+i, n, k);
        result.remove(result.size()-1);
        backtrack(result, output, i+1, sum, n, k);
    }
}
