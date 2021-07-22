package problems;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 */
public class JumpGame {

    public static void main(String[] args) {

        int[] nums = {0,2,3};
        new JumpGame().canJump(nums);
    }

    boolean canJump(int A[]) {
        int last=A.length-1,i,j;
        for(i=A.length-1;i>=0;i--){
            if(i+A[i]>=last)last=i;
        }
        return last==0;
    }
}
