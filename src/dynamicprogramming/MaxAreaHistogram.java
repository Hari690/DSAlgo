package dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */
class MaxAreaHistogram {
    /*
        dp - keep storing numbers to left with indexes of no less than current.
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] sL = new int[n];
        int[] sR = new int[n];
        // since we only use these for calculating the base of histogram we can set base condition appropriately.
        sL[0] = -1; sR[n-1] = n;

        // For every index update index of small bar before it.
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

    public int largestRectangleAreaStack2(int[] heights) {
        Deque<int[]> stack = new LinkedList<>();

        int maxArea = 0;
        for(int i=0;i<heights.length;i++) {
            int start = i;
            // pop from the stack if current is smaller than peak then pop and calculate maxArea.
            // since current element can be extended insert current element with last popped index signifying the rectangle.
            while(!stack.isEmpty() && stack.peek()[0]>heights[i]) {
                int[] prev = stack.pop();
                maxArea = Math.max(maxArea, (i-prev[1])*prev[0]);
                start = prev[1];
            }
            stack.push(new int[]{heights[i],start});
        }

        while (!stack.isEmpty()) {
            int[] prev = stack.pop();
            maxArea = Math.max(maxArea, (heights.length-prev[1])*prev[0]);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};

        MaxAreaHistogram maxAreaHistogram = new MaxAreaHistogram();
        //System.out.println(maxAreaHistogram.largestRectangleArea(heights));

        System.out.println(maxAreaHistogram.largestRectangleAreaStack2(heights));
    }
}
