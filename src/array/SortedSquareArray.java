package array;

/**
 * Given an integer array nums sorted in non-decreasing order,
 * return an array of the squares of each number sorted in non-decreasing order.
 */
public class SortedSquareArray {
    public int[] sortedSquares(int[] nums) {
        int[] output = new int[nums.length];

        int k=nums.length-1;
        int i=0;
        int j=nums.length-1;
        while(i<=j) {
            if(nums[i]*nums[i]>=nums[j]*nums[j]) {
                output[k--] = nums[i]*nums[i];
                i++;
            } else {
                output[k--] = nums[j]*nums[j];
                j--;
            }
        }
        return output;
    }
}
