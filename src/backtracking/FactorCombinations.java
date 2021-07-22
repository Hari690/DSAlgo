package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactorCombinations {

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(2, 1, n, result, list);
        return result;
    }

    private static void helper(int start, int product, int n, List<List<Integer>> result, List<Integer> list) {

        if(product==n) {
            result.add(new ArrayList<>(list));
        }

        for(int i=start;i<n;i++) {
            if(product*i>n){
                break;
            }
            if(n%i==0) {
                list.add(i);
                helper(i, product * i, n, result, list);
                list.remove(list.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(getFactors(12));
    }

}
