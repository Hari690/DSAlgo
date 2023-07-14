package dynamicprogramming;

/**
 * You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.
 * You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
 * Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
 *
 * Example 1:
 * Input: rods = [1,2,3,6]
 * Output: 6
 * Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
 *
 * Example 2:
 * Input: rods = [1,2,3,4,5,6]
 * Output: 10
 * Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
 *
 * Example 3:
 * Input: rods = [1,2]
 * Output: 0
 * Explanation: The billboard cannot be supported, so we return 0.
 *
 * Constraints:
 *
 * 1 <= rods.length <= 20
 * 1 <= rods[i] <= 1000
 * sum(rods[i]) <= 5000
 *
 */
public class TallestBillboard {

    /*
        This solution has 3 variables for applying dynamic programming so will result in MLE.
        Since we are checking s1==s2 we can simplify and use s1-s2 as a variable to apply dp.
     */
    public int tallestBillboard(int[] rods) {
        return longestBillBoard(rods, 0, 0, 0);
    }

    private int longestBillBoard(int[] rods, int i, int s1, int s2) {
        if(i>=rods.length)
            return (s1==s2)?s1:0;

        int max1 = Math.max(longestBillBoard(rods, i+1, s1+rods[i], s2),
                longestBillBoard(rods, i+1, s1, s2+rods[i]));

        return Math.max(max1, longestBillBoard(rods, i+1, s1, s2));
    }

    public int tallestBillboardDp(int[] rods) {
        Integer[][] dp = new Integer[20][2*5000];
        return longestBillBoardDp(rods, 0, 0, dp);
    }

    private int longestBillBoardDp(int[] rods, int i, int diff, Integer[][] dp) {
        if(i==rods.length) {
            return (diff==0)?0:Integer.MIN_VALUE;
        }

        // to prevent dp array from using negative index;
        if(dp[i][diff+5000]!=null)
            return dp[i][diff+5000];

        // add rod while adding to one basket so we can return the value in the end.
        int max1 = rods[i] + longestBillBoardDp(rods, i+1, diff+rods[i], dp);
        int max2 = longestBillBoardDp(rods, i+1, diff-rods[i], dp);

        int max = Math.max(max1,max2);

        dp[i][diff+5000] = Math.max(max, longestBillBoardDp(rods, i+1, diff, dp));
        return dp[i][diff+5000];
    }

    public static void main(String[] args) {
        TallestBillboard tallestBillboard = new TallestBillboard();
        int[] rods = {1,2,3,4,5,6};
        tallestBillboard.tallestBillboardDp(rods);
    }
}

