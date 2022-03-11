package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to
 * group numbers and operators. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 */
public class DiffWaysToAddParenthesis {

    /*
        Idea is to use recursion and split expression and compute the solutions recursively as solution lists
        and calculate the results using a cross product.
        When there's no operator then add the operand into the list which will be the base condition.
     */
    public List<Integer> diffWaysToCompute(String expression) {
        Map<String,List<Integer>> cache = new HashMap<>();
        return compute(expression, cache);
    }

    private List<Integer> compute(String expression, Map<String,List<Integer>> cache) {
        if(cache.containsKey(expression))
            return cache.get(expression);
        List<Integer> result = new ArrayList<>();
        Character[] ops = {'+','-','*'};
        List<Character> opsList = Arrays.asList(ops);
        Set<Character> operators = new HashSet<>(opsList);
        for( int i=0;i<expression.length();i++) {
            if(operators.contains(expression.charAt(i))) {
                List<Integer> part1List = diffWaysToCompute(expression.substring(0,i));
                List<Integer> part2List = diffWaysToCompute(expression.substring(i+1));

                for(Integer op1 : part1List) {
                    for( Integer op2 : part2List) {
                        switch (expression.charAt(i)) {
                            case '+':
                                int op3 = op1 + op2;
                                result.add(op3);
                                break;
                            case '-':
                                op3 = op1 - op2;
                                result.add(op3);
                                break;
                            case '*':
                                op3 = op1 * op2;
                                result.add(op3);
                                break;
                        }
                    }
                }
            }
        }

        // base case
        if(result.size()==0)
            result.add(Integer.parseInt(expression));

        cache.put(expression, result);
        return result;
    }

    public static void main(String[] args) {
        DiffWaysToAddParenthesis solution = new DiffWaysToAddParenthesis();

        solution.diffWaysToCompute("2-1-1");
    }
}
