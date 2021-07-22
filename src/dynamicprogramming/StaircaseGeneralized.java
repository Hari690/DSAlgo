package dynamicprogramming;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top. The person can climb either 1 stair or 2 stairs at a time.
 * Count the number of ways, the person can reach the top.
 * Solution Fibonacci but recursive solution will have exponential complexity.
 * Now generalize no of ways m.
 */
public class StaircaseGeneralized {
    static int countWaysUtil(int n, int m)
    {
        int res[] = new int[n];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < n; i++) {
            res[i] = 0;
            for (int j = 1; j <= m && j <= i; j++)
                res[i] += res[i - j];
        }
        return res[n - 1];
    }

    // Returns number of ways to reach s'th stair
    static int countWays(int s, int m)
    {
        return countWaysUtil(s + 1, m);
    }

    // Driver method
    public static void main(String[] args) {
        int s = 4, m = 2;
        System.out.println("Number of ways = "
            + countWays(s, m));
    }
}
