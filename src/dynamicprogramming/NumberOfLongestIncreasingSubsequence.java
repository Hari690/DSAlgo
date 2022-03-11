package dynamicprogramming;

/**
 * Given an integer array nums, return the number of longest increasing subsequences.
 * Notice that the sequence has to be strictly increasing.
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] counts = new int[nums.length];
        int max_len=0;

        for( int i=0;i<nums.length;i++) {
            dp[i]=1;counts[i]=1;
            for(int j=0;j<i;j++) {
                if(nums[i]>nums[j]) {
                    // when the value is greater than previous element, increase dp[i]
                    // in that case copy count from count array.

                    if(dp[i]<dp[j]+1) {
                        dp[i]=dp[j]+1;
                        counts[i] = counts[j];
                    } else if(dp[j]+1==dp[i])
                        counts[i]+=counts[j];
                }
            }
            max_len = Math.max(dp[i],max_len);
        }

        int result=0;
        for( int i=0;i<nums.length;i++) {
            if(dp[i]==max_len)
                result+=counts[i];
        }

        return result;
    }

    public static void main(String[] args) {
        NumberOfLongestIncreasingSubsequence solution = new NumberOfLongestIncreasingSubsequence();

        int[] nums = {1,2};
        //int[] nums = {1,1,1,1,1};
        solution.findNumberOfLIS(nums);
    }
}
