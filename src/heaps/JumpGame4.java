package heaps;

import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 *
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * Return the maximum score you can get.
 *
 * Example 1:
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
 * Example 2:
 *
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
 * Example 3:
 *
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 */
public class JumpGame4 {

    /*
        Idea is to maintain the highest sum in the previous k elements by maintaining index in the max heap
        and pruning out top elements which fall out of the window.
        We keep adding max sum of prev k elements back into the heap and at the end we will have the solution.
     */
    public int maxResult(int[] nums, int k) {

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b)->b[1]-a[1]);

        int sum = 0;
        maxHeap.offer(new int[]{0,nums[0]});
        for(int i=1;i<nums.length;i++) {
            while(maxHeap.peek()[0]<i-k)
                maxHeap.poll();
            System.out.println(maxHeap.peek()[1]);
            sum=maxHeap.peek()[1]+nums[i];
            maxHeap.offer(new int[]{i,sum});
        }

        return sum;
    }

    public static void main(String[] args) {
        JumpGame4 jumpGame4 = new JumpGame4();
        int[] arr = {-123};
        jumpGame4.maxResult(arr, 10);
    }
}
