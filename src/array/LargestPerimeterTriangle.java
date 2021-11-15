package array;

import java.util.Arrays;

/**
 * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If
 * it is impossible to form any triangle of a non-zero area, return 0.
 */
public class LargestPerimeterTriangle {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for(int i=nums.length;i-3>=0;i--)
            // looking at the condition if sum of next two bigger values can't be bigger than third
            // then the same holds for smaller values also. Hence we don't need to check for those
            // cases and need to check only for consecutive sides.
            if(nums[i-1] < nums[i-2] + nums[i-3])
                return nums[i-1]+nums[i-2]+nums[i-3];

        return 0;
    }
}
