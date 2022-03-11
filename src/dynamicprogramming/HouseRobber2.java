package dynamicprogramming;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at
 * this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a
 * security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight
 * without alerting the police.
 */
public class HouseRobber2 {
    // Try two passes 0 to (n-2) & 1 to (n-1)
    public int rob(int[] nums) {
        if(nums.length==1)
            return nums[0];
        if(nums.length==2)
            return Math.max(nums[0],nums[1]);
        return Math.max(rob(nums,0,nums.length-1),rob(nums,1,nums.length));
    }

    public int rob(int[] nums, int l, int r) {
        if ( nums.length==1){
            return nums[l];
        }
        int p1 = 0;
        int p2 = 0;
        for ( int i=l;i<r;i++) {
            int temp=Math.max(p1+nums[i],p2);
            p1=p2;
            p2=temp;

        }
        return p2;
    }
}
