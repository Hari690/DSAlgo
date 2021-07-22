package problems;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such
 * that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such
 * that the container contains the most water.
 * Notice that you may not slant the container.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] heights) {
        int maxArea = Integer.MIN_VALUE;
        int i=0;
        int j=heights.length-1;

        while ( i<j ) {
            maxArea = Math.max(maxArea,(j-i)*Math.min(heights[i],heights[j]));
            if(heights[i]>heights[j]) {
                j--;
            } else {
                i++;
            }
        }

        return maxArea;
    }
}
