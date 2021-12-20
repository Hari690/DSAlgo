package problems;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after
 * raining.
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new TrappingRainWater().trap(height));
    }

     public int trap(int[] height) {

         if(height.length<=2)
             return 0;

         int n = height.length;
         int[] left = new int[height.length];
         int[] right = new int[height.length];


         for(int i=1;i<height.length-1;i++) {
             left[i]=Math.max(left[i-1],height[i-1]);
             right[n-i-1]=Math.max(right[n-i],height[n-i]);
         }

         right[0]=Math.max(right[1],height[1]);
         left[n-1]=Math.max(left[n-2],height[n-2]);

         int sum = 0;
         for(int i=0;i<height.length;i++) {
             int val = Math.min(left[i],right[i])-height[i];
             if(val>0)
                 sum+=val;
         }
         return sum;
     }

     /*
      2 pointer method.
      */
    public int trapShortest(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0; int right = height.length - 1; // Pointers to both ends of the array.
        int maxLeft = 0; int maxRight = 0;

        int totalWater = 0;
        while (left < right) {
            // Water could, potentially, fill everything from left to right, if there is nothing in between.
            if (height[left] < height[right]) {
                // If the current elevation is greater than the previous maximum, water cannot occupy that point at all.
                // However, we do know that everything from maxLeft to the current index, has been optimally filled, as we've
                // been adding water to the brim of the last maxLeft.
                if (height[left] >= maxLeft) {
                    // So, we say we've found a new maximum, and look to see how much water we can fill from this point on.
                    maxLeft = height[left];
                    // If we've yet to find a maximum, we know that we can fill the current point with water up to the previous
                    // maximum, as any more will overflow it. We also subtract the current height, as that is the elevation the
                    // ground will be at.
                } else {
                    totalWater += maxLeft - height[left];
                }
                // Increment left, we'll now look at the next point.
                left++;
                // If the height at the left is NOT greater than height at the right, we cannot fill from left to right without over-
                // flowing; however, we do know that we could potentially fill from right to left, if there is nothing in between.
            } else {
                // Similarly to above, we see that we've found a height greater than the max, and cannot fill it whatsoever, but
                // everything before is optimally filled
                if (height[right] >= maxRight) {
                    // We can say we've found a new maximum and move on.
                    maxRight = height[right];
                    // If we haven't found a greater elevation, we can fill the current elevation with maxRight - height[right]
                    // water.
                } else {
                    totalWater += maxRight - height[right];
                }
                // Decrement left, we'll look at the next point.
                right--;
            }
        }
        // Return the sum we've been adding to.
        return totalWater;
    }
}
