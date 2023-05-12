package twopointer;

/**
 * You are given an integer array nums and two integers minK and maxK.
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 *
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * Output: 2
 * Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
 *
 * Example 2:
 * Input: nums = [1,1,1,1], minK = 1, maxK = 1
 * Output: 10
 * Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 */
public class CountSubarraysWithFixedBounds {

     /*
        So, count _1 will store the subarrays are in the window >=minK and <=maxK .
            count _2 will store the subarrays are in the window >minK and <=maxK .
            count _3 will store the subarrays are in the window >=minK and <maxK .
            count _4 will store the subarrays are in the window >minK and <maxK .
            To calculate the number of subarrays that have min=minK and max=maxK, we use count_1 - count_2 - count_3 + count_4.
            count_4 is beacause this window is present in both count_2 and count_3, and is subtracted 2 times. Hence it has to be added 1 time to get the correct answer.
     */

    public long countSubarrays2(int[] nums, int minK, int maxK)
    {
        int n = nums.length;
        long count1 = totalSubarrays(nums, n, maxK, minK);
        long count2 = totalSubarrays(nums, n, maxK, minK+1);
        long count3 = totalSubarrays(nums, n, maxK - 1, minK);
        long count4 = totalSubarrays(nums, n, maxK-1, minK+1);
        //System.out.println(count +" "+count1+" "+count2+" "+count3);
        long ans = count1 - count2 - count3 + count4;

        return ans;

    }

    // count no of sub-arrays in a given range in an array.
    // If we reduce min to min+1 and max to max-1 then we will get the exact sub-arrays beginning and ending
    // with the nos we have.
    public long totalSubarrays(int arr[], int n, int upper,int lower)
    {
        long ans = 0;
        int i = 0;
        while (i < n) {
            if (arr[i] > upper || arr[i]< lower) {
                i++;
                continue;
            }
            long count = 0;
            while (i < n && (arr[i] <= upper && arr[i] >= lower)) {
                i++;
                count++;
            }
            ans += ((count * (count + 1)) / 2);
        }
        return ans;
    }


    public long countSubarrays(int[] nums, int minK, int maxK) {

        int minIndex=-1,maxIndex=-1,lastWrongIndex=-1;
        long count = 0;

        for(int i=0;i<nums.length;i++) {
            if(nums[i]>=minK && nums[i]<=maxK) {

                minIndex = (nums[i]==minK)? i : minIndex;
                maxIndex = (nums[i]==maxK)? i : maxIndex;

                count+=Math.max(0, Math.min(maxIndex,minIndex) - lastWrongIndex);
            } else {
                minIndex = -1;
                maxIndex = -1;
                lastWrongIndex = i;
            }
        }

        return count;

    }

    public static void main(String[] args) {
        CountSubarraysWithFixedBounds countSubarraysWithFixedBounds = new CountSubarraysWithFixedBounds();
        int[] nums = {1,3,5,2,7,5};
        countSubarraysWithFixedBounds.countSubarrays2(nums, 1, 5);
    }
}
