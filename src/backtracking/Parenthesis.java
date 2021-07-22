package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 */
class Parenthesis {

    public static void main(String[] args) {
        List<String> allParenthesis = new Parenthesis().generateParenthesis(3);
        System.out.println(allParenthesis);
    }

    public List<String> generateParenthesis(int n) {
        List<String> output = new ArrayList<>();
        backTrack(output,"",0,0,n);
        return output;
    }

    private void backTrack(List<String> output, String s, int open, int close, int max) {
        if(s.length()==6) {
            output.add(s);
        }
        if(open<max)
            backTrack(output, s+"(", open+1, close, max);
        if(close<open)
            backTrack(output, s+")", open, close+1, max);
    }
}
