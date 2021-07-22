package problems;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new TrappingRainWater().trap(height));
    }
     public int trap(int[] height) {

         // To store the maximum water
         // that can be stored
         int res = 0;

         // For every element of the array
         // except first and last element
         for(int i = 0; i < height.length; i++)
         {

             // Find maximum element on its left
             int left = height[i];
             for(int j = 0; j < i; j++)
             {
                 left = Math.max(left, height[j]);
             }

             // Find maximum element on its right
             int right = height[i];
             for(int j = i + 1; j < height.length; j++)
             {
                 right = Math.max(right, height[j]);
             }

             // Update maximum water value
             res += Math.min(left, right) - height[i];
         }
         return res;
     }
}
