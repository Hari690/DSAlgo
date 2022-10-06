package dynamicprogramming;

import java.util.Arrays;

/**
 * You are given a 0-indexed binary string s which represents the types of buildings along a street where:
 *
 * s[i] = '0' denotes that the ith building is an office and
 * s[i] = '1' denotes that the ith building is a restaurant.
 * As a city official, you would like to select 3 buildings for random inspection. However, to ensure variety, no two consecutive buildings out of the selected buildings can be of the same type.
 *
 * For example, given s = "001101", we cannot select the 1st, 3rd, and 5th buildings as that would form "011" which is not allowed due to having two consecutive buildings of the same type.
 * Return the number of valid ways to select 3 buildings.
 *
 * Example 1:
 * Input: s = "001101"
 * Output: 6
 * Explanation:
 * The following sets of indices selected are valid:
 * - [0,2,4] from "001101" forms "010"
 * - [0,3,4] from "001101" forms "010"
 * - [1,2,4] from "001101" forms "010"
 * - [1,3,4] from "001101" forms "010"
 * - [2,4,5] from "001101" forms "101"
 * - [3,4,5] from "001101" forms "101"
 * No other selection is valid. Thus, there are 6 total ways.
 *
 * Example 2:
 * Input: s = "11100"
 * Output: 0
 * Explanation: It can be shown that there are no valid selections.
 */
public class NumberOfWaysSelectBuilding3dDP {
    public long numberOfWays(String s) {
        long[][][] dp = new long[s.length()+1][4][3];
        for(int i=0;i<s.length()+1;i++)
            for(int j=0;j<4;j++)
                Arrays.fill(dp[i][j], -1);
        long solve = solve(s, 0, 0, -1, dp);
        return solve;
    }

    private long solve(String s, int index, int selected, int prev, long[][][] dp) {
        if(selected==3)
            return 1;
        if(index==s.length())
            return 0;
        if(dp[index][selected][prev+1]!=-1)
            return dp[index][selected][prev+1];

        if(s.charAt(index)-'0'!=prev) {
            dp[index][selected][prev+1] = solve(s, index+1, selected+1, s.charAt(index)-'0', dp) + solve(s, index+1, selected, prev, dp);
        } else {
            dp[index][selected][prev+1] = solve(s, index+1, selected, prev, dp);
        }
        return dp[index][selected][prev+1];
    }

    public static void main(String[] args) {
        NumberOfWaysSelectBuilding3dDP numberOfWaysSelectBuilding3dDP = new NumberOfWaysSelectBuilding3dDP();
        numberOfWaysSelectBuilding3dDP.numberOfWays("001101");
    }
}
