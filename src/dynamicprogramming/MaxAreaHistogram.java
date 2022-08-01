package dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */
class MaxAreaHistogram {
    public int largestRectangleAreaStack(int[] heights) {
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
        System.out.println(maxAreaHistogram.largestRectangleAreaStack(heights));
    }
}
