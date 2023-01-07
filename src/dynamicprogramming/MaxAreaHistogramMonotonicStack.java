package dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * Input: heights = [2,4]
 * Output: 4
 */
class MaxAreaHistogramMonotonicStack {
    public int largestRectangleAreaStack(int[] heights) {
        Deque<int[]> stack = new LinkedList<>();

        int maxArea = 0;
        for(int i=0;i<heights.length;i++) {
            int start = i;
            // if current is smaller than peak then we can't extend current rectangle any longer so pop from the stack and calculate maxArea
            // since current element can be extended insert current element with last popped element's index signifying the current rectangle area that can be extended.
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

        MaxAreaHistogramMonotonicStack maxAreaHistogram = new MaxAreaHistogramMonotonicStack();
        //System.out.println(maxAreaHistogram.largestRectangleArea(heights));
        System.out.println(maxAreaHistogram.largestRectangleAreaStack(heights));
    }
}
