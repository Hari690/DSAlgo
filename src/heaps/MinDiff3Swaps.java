package heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 * Example 1:
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 *
 * Example 2:
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 */
public class MinDiff3Swaps {

    public int minDifferenceSort(int[] nums) {
        if(nums.length<=4) {
            return 0;
        }
        Arrays.sort(nums);

        int max = Integer.MAX_VALUE;
        for(int i=0;i<4;i++) {
            max = Math.min(max,Math.abs(nums[nums.length-1-3+i]-nums[i]));
        }

        return max;

    }

    /*
     Notice we don't have to sort the entire array here just first 4 and last 4.
     */
    public int minDifference(int[] nums) {
        int len = nums.length;
        if(len<5){
            return 0;
        }
        PriorityQueue<Integer> max = new  PriorityQueue<>();
        PriorityQueue<Integer> min = new  PriorityQueue<>(Collections.reverseOrder());

        for(int i= 0; i<len; i++){
            max.add(nums[i]);
            min.add(nums[i]);

            if(max.size()>4){
                max.poll();
                min.poll();
            }
        }
        //input : [20, 75 ,81, 82 ,95 ,100, 200 ,300]
        int[] minA = new int[4]; //[82,81,75,20]
        int[] maxA = new int[4]; //[300,200,100,95]
        for(int i=0,k=3; i<4; i++,k--){
            minA[i] = min.poll();
            maxA[k] = max.poll();
        }

        int ans = Integer.MAX_VALUE;
        for(int i=0; i<4; i++){
            ans = Math.min(ans, maxA[i] - minA[i]);
        }
        return ans;
    }
}
