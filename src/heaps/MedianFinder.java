package heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is
 * the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
class MedianFinder {
    PriorityQueue<Integer> min = null;
    PriorityQueue<Integer> max = null;
    boolean even = true;

    public MedianFinder() {
        min = new PriorityQueue();
        max = new PriorityQueue(Collections.reverseOrder());
    }

    // max queue is always larger or equal to min queue

    // Adds a number into the data structure.
    public void addNum(int num) {
        if(even) {
            max.offer(num);
            min.offer(max.poll());
        } else {
            min.offer(num);
            max.offer(min.poll());
        }
        even = !even;
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(even) {
            return (double)(max.peek() + min.peek())/2;
        }else {
            return (double)min.peek();
        }
    }
}