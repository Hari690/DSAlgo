package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * You may return the answer in any order.
 */
public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> result = new Combinations().combine(4, 2);

        result.forEach(System.out::println);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        combine(n, k, 0, result, output);

        return output;
    }

    private void combine(int n, int k, int index, List<Integer> result, List<List<Integer>> output) {
        if (result.size() == k) {
            output.add(new ArrayList<>(result));
        }

        for (int i = index + 1; i <= n; i++) {
            result.add(i);
            combine(n, k, i, result, output);
            result.remove(result.size() - 1);
        }
    }
}