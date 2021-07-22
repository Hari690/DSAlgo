package dynamicprogramming;

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

        // For every index update index small bar before it.
        // We jump based on indexes and save on complexity.
        for(int i = 1; i < n; ++i){
            int idx = i-1;
            while(idx >= 0 && heights[idx] >= heights[i])
                idx = sL[idx];
            sL[i] = idx;
        }

        // For every index update index small bar before it.
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

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};

        MaxAreaHistogram maxAreaHistogram = new MaxAreaHistogram();
        maxAreaHistogram.largestRectangleArea(heights);
    }
}
