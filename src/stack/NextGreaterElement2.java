package stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 */
public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {

        // for repetitions if there are duplicates so we need in that order.
        Map<Integer,Queue<Integer>> map = new HashMap<>();
        // to keep track of next greater element.
        Deque<Integer> stack = new LinkedList<>();

        for(int i=0;i<nums.length*2;i++) {
            int num = nums[i%nums.length];
            while(!stack.isEmpty() && stack.peek()<num) {
                int key = stack.pop();
                Queue<Integer> queue = map.get(key);
                if(queue==null) {
                    queue = new LinkedList<>();
                    map.put(key, queue);
                }
                queue.add(num);
            }
            stack.push(num);
        }

        int i=0;
        int[] result = new int[nums.length];
        for(int num : nums) {
            Queue<Integer> queue = map.get(num);
            result[i++] = (queue==null || queue.isEmpty())?-1:queue.poll();
        }
        return result;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }
        return next;
    }

    public static void main(String[] args) {
        int[] array = {100,1,11,1,120,111,123,1,-1,-100};
        NextGreaterElement2 solution = new NextGreaterElement2();
        solution.nextGreaterElements(array);
    }
}
