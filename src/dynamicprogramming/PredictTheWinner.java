class PredictTheWinner {

  // function is for adding score of first player only hence we don't add any score for second player and minimise for second player.
  public boolean PredictTheWinner(int[] nums) {
        int sum=0;
        for(int n:nums)
            sum+=n;
        
        int[][][] dp = new int[2][nums.length][nums.length];
        for(int i=0;i<nums.length;i++) {
            Arrays.fill(dp[0][i], -1);
            Arrays.fill(dp[1][i], -1);
        }
        
        int onesScore = wins(nums, true, 0, nums.length-1, dp);
        int twosScore = sum - onesScore;
        return onesScore>=twosScore;
    }
    
    private int wins(int nums[], boolean isOne, int left, int right, int[][][] dp) {
        if(left>right) {
            return 0;
        }
        
        if(dp[isOne?0:1][left][right]!=-1)
            return dp[isOne?0:1][left][right];
                
        if(isOne) {
            int r1 = nums[left]+wins(nums, !isOne, left+1, right, dp);
            int r2 = nums[right]+wins(nums, !isOne, left, right-1, dp);
            dp[isOne?0:1][left][right] = Math.max(r1,r2);
            return dp[isOne?0:1][left][right];
        } else {
            int r1 = wins(nums, !isOne, left+1, right, dp);
            int r2 = wins(nums, !isOne, left, right-1, dp);
            dp[isOne?0:1][left][right] = Math.min(r1,r2);
            return dp[isOne?0:1][left][right];
        }
    }
}
