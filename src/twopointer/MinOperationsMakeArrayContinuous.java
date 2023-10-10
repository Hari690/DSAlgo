
/**

Think about quadratic solution first.

You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

    All elements in nums are unique.
    The difference between the maximum element and the minimum element in nums equals nums.length - 1.

For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.

Example 1:
Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.

Example 2:
Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.

Example 3:
Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.

  **/
 
class MinOperationsMakeArrayContinuous {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
       
        // maximum window size, current window size (unique numbers in the window)
        int maxWindow = 0, window = 0;
        // start and end index of the window
        int start = 0;
        for (int end = 0; end < n; end++) {
            while (start < end && nums[end] - nums[start] >= n) {
                // if duplicate occurs, only count the first appearance into the window
                if (start == 0 || nums[start] != nums[start - 1]) {
                    window--;
                }
                
                start++;
            }
            
            if (start == end || (end > 0 && nums[end] != nums[end - 1])) {
                window++;
            }
            
            maxWindow = Math.max(maxWindow, window);
        }
        
        return n - maxWindow;
    }
}
