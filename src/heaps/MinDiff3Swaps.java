package heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 *
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 */
public class MinDiff3Swaps {

    /*
     Notice we don't have to sort the entire array here.
     */
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
