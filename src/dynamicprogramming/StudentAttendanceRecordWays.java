package dynamicprogramming;

import java.util.Arrays;

/**
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 2
 * Output: 8
 * Explanation: There are 8 records with length 2 that are eligible for an award:
 * "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 *
 * Example 2:
 * Input: n = 1
 * Output: 3
 *
 * Example 3:
 * Input: n = 10101
 * Output: 183236316
 */
public class StudentAttendanceRecordWays {

    int MOD=1000000007;

    /*
    // Cases:
    // Put P at current ind, the consecutive late count will become 0 and absent count will remain same.

    // Put L at current ind, the consecutive late count will increase by 1 and absent count remains the same.

    // Put A at current ind, the consecutive late count becomes 0 and absent count increases by 1.
     */

    long solve(int i, int a, int l, int n, long dp[][][]){
        if(a>1 || l>2)
            return 0;
        if(i==n)
            return 1;
        if(dp[i][a][l]!=-1)
            return dp[i][a][l];
        long ans=0;

        ans=(ans%MOD+solve(i+1, a, 0, n, dp)%MOD + solve(i+1, a+1, 0, n, dp)%MOD + solve(i+1, a, l+1, n, dp)%MOD)%MOD;
        return dp[i][a][l]=ans;
    }

    int checkRecord(int n) {
        long dp[][][] = new long[n][2][3];
        for(int i=0;i<n;i++)
            for(int j=0;j<2;j++)
                Arrays.fill(dp[i][j], -1);
        return (int) solve(0,0,0, n, dp);
    }

    public static void main(String[] args) {
        StudentAttendanceRecordWays studentAttendanceRecordWays = new StudentAttendanceRecordWays();

        studentAttendanceRecordWays.checkRecord(2);
    }
}
