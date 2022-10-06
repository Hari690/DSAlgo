package graphs;

import java.util.List;

/**
 * You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
 *
 * Example 1:
 * Input: equations = ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 *
 * Example 2:
 * Input: equations = ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 */
public class UnionFindEquations {
    int[] s = new int[26];
    /*
        Proof by contradiction.
        Initially set all of the chars to have unique node values.
        Make all the equality nodes to point to common parent.
        For inequality check if they are same as initially set then return false.
     */
    public boolean equationsPossible(String[] equations) {
        for(int i=0;i<26;i++)
            s[i] = i;

        for(String equation : equations) {
            if(equation.charAt(1)=='=') {
                int s1 = find(equation.charAt(0)-'0');
                int s2 = find(equation.charAt(3)-'0');

                s[s2] = s1;
            }
        }

        for(String equation : equations) {
            if(equation.charAt(1)=='!') {
                int s1 = find(equation.charAt(0)-'0');
                int s2 = find(equation.charAt(3)-'0');

                if(s1==s2)
                    return false;
            }
        }

        return true;
    }

    private int find(int c) {
        return (c==s[c])? c:find(s[c]);
    }

    public static void main(String[] args) {
        String equation1 = "A==B";
        String equation2 = "C!=D";
        String[] equations = new String[]{equation1, equation2};
        UnionFindEquations unionFindEquations = new UnionFindEquations();
        unionFindEquations.equationsPossible(equations);
    }
}
