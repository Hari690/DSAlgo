package array;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class ProductOfArrayExceptSelf {
    /*
    Given numbers [2, 3, 4, 5], regarding the third number 4, the product of array except 4 is 2*3*5 which consists of two parts: left 2*3 and right 5. The product is left*right. We can get lefts and rights:
    Numbers:     2    3    4     5
    Lefts:            2  2*3 2*3*4
    Rights:  3*4*5  4*5    5
     */
    public int[] productExceptSelf(int[] nums) {
        int[] loutput = new int[nums.length];
        loutput[0]=nums[0];

        for( int i=1;i<nums.length;i++) {
            loutput[i] = nums[i]*loutput[i-1];
        }

        int[] output = new int[nums.length];
        output[nums.length-1]=loutput[nums.length-2];
        int rproduct = nums[nums.length-1];
        for( int i=nums.length-2;i>=1;i--) {
            output[i] = loutput[i-1]*rproduct;
            rproduct = nums[i]*rproduct;
        }
        output[0] = rproduct;

        return output;
    }
}
