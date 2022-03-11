package array;

import java.util.HashSet;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 * Example 1:
 *
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 *
 * Input: c = 3
 * Output: false
 */
public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        long l=0;
        long r=(int)Math.sqrt(c);

        while(l<=r) {
            long ans = l*l + r*r;
            if(c==ans)
                return true;
            else if(ans>c) {
                r--;
            }
            else {
                l++;
            }
        }

        return false;
    }

    public boolean judgeSquareSumSet(int c) {
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 0; i <= Math.sqrt(c); i++) {
            set.add(i * i);
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JudgeSquareSum judgeSquareSum = new JudgeSquareSum();
        judgeSquareSum.judgeSquareSum(4);
    }
}
