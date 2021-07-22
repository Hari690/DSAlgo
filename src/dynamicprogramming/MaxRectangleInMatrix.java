package dynamicprogramming;

import java.util.Stack;

public class MaxRectangleInMatrix {

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0) return 0;

        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = matrix[i][j]-'0';
                if (dp[i][j] > 0 && i>0) dp[i][j] += dp[i - 1][j];
            }
            max=Math.max(largestRectangleArea(dp[i]), max);
        }

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] sL = new int[n];
        int[] sR = new int[n];
        sL[0] = -1; sR[n-1] = n;

        // For every index update index small bar before it.
        // We jump based on indexes and save on complexity.
        for(int i = 1; i < n; ++i){
            int idx = i-1;
            while(idx >= 0 && heights[idx] >= heights[i])
                idx = sL[idx];
            sL[i] = idx;
        }

        // For every index update index small bar after it.
        // We jump based on indexes and save on complexity.
        for(int i = n-2; i >= 0; --i){
            int idx = i+1;
            while(idx < n && heights[idx] >= heights[i])
                idx = sR[idx];
            sR[i] = idx;
        }
        int result = 0;
        for(int i = 0; i < n; ++i)
            result = Math.max(result, heights[i]*(sR[i] - sL[i] - 1));
        return result;
    }
}
