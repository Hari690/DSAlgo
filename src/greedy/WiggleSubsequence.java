package greedy;

/**
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 */
public class WiggleSubsequence {

    // when 2 continuous increasing or decreasing we can skip one and continue.
    public int wiggleMaxLength(int[] nums) {
        int oddLength=1,evenLength=1;
        int val1=nums[0],val2=nums[0];
        boolean order1=true,order2=true;
        for(int i=1;i<nums.length;i++) {
            if((nums[i]>val1 && order1) || (nums[i]<val1 && !order1)) {
                oddLength++;
                order1=!order1;
            } if ((nums[i]<val2 && order2) || (nums[i]>val2 && !order2)) {
                evenLength++;
                order2=!order2;
            }
            val1 = nums[i];
            val2 = nums[i];
        }
        return Math.max(oddLength,evenLength);
        //[33,53,12,64,50,41,45,21,97,35,47,92,39
    }

    public int wiggleMaxLengthDp(int[] nums) {
        if (nums.length == 0) return 0;
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) down = up + 1;
            else if (nums[i] > nums[i - 1]) up = down + 1;
        }
        return Math.max(up, down);
    }
}
