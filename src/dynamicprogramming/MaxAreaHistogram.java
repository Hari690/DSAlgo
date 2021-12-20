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

    // stack based solution by inserting indexes.
    public int largestRectangleAreaStack(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();

        int maxArea = 0;
        int n=heights.length;
        int i=0;

        while(i<n) {
            // as long as the current bar is shorter than the last one in the stack
            // we keep popping out the stack and calculate the area based on
            // the popped bar
            while(!stack.isEmpty() && heights[i]<heights[stack.peek()]) {
                // tricky part is how to handle the index of the left bound
                // finding index for handling this case [5,4,1,2] 4*2 after popping 5 from stack
                // i.e arr[1]*(1-0+1).
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - (stack.isEmpty() ? 0 : stack.peek() + 1)));
            }
            // put current bar's index to the stack
            stack.push(i++);
        }

        // finally pop out any bar left in the stack and calculate the area based on it so n minus instead of i minus.
        while(!stack.isEmpty())
            maxArea = Math.max(maxArea, heights[stack.pop()] * (n - (stack.isEmpty() ? 0 : stack.peek() + 1)));

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};

        MaxAreaHistogram maxAreaHistogram = new MaxAreaHistogram();
        System.out.println(maxAreaHistogram.largestRectangleArea(heights));

        System.out.println(maxAreaHistogram.largestRectangleAreaStack(heights));
    }
}
