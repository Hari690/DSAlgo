package heaps;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is
 * the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the
 * array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is
 * the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the
 * array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        double[] result = new double[nums.length-k+1];
        for(int i=0;i<nums.length;i++) {
            maxHeap.offer(nums[i]);             // Add number
            minHeap.offer(maxHeap.poll());      // Balancing step

            if(maxHeap.size() < minHeap.size()){ // maintain size property, max heap always has larger size.
                maxHeap.offer(minHeap.poll());
            }
            if(i>=k-1) {
                if(k%2==0) {
                    result[i-k+1] = ((double)minHeap.peek() + (double)maxHeap.peek())/ 2;
                } else {
                    result[i-k+1] = (double)maxHeap.peek();
                }
                // remove from max, if not done remove from min.
                boolean removed = maxHeap.remove(nums[i-k+1]);
                if(!removed)
                    minHeap.remove(nums[i-k+1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1};
        SlidingWindowMedian solution = new SlidingWindowMedian();
        solution.medianSlidingWindow(nums, 2);
    }
}
