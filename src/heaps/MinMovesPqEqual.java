package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 *
 * In one move, you can increment n - 1 elements of the array by 1.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 * Explanation: Only three moves are needed (remember each move increments two elements):
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * Example 2:
 *
 * Input: nums = [1,1,1]
 * Output: 0
 */
public class MinMovesPqEqual {
    public int minMoves(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)->b-a);

        for(int num : nums) {
            maxHeap.add(num);
        }

        int steps = 0;
        while(!check(maxHeap) && !maxHeap.isEmpty()) {
            int max = maxHeap.poll();
            List<Integer> list = new ArrayList<>();
            while(!maxHeap.isEmpty()) {
                list.add(maxHeap.poll()+1);
            }
            maxHeap.addAll(list);
            maxHeap.add(max);
            steps++;
        }
        return steps;
    }

    private boolean check(PriorityQueue<Integer> queue) {
        for(int val : queue) {
            if(queue.peek()!=val)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        MinMovesPqEqual solution = new MinMovesPqEqual();
        solution.minMoves(nums);
    }
}
