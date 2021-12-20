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
class MedianDataStream {
    // 2 heap approach to find the median
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;
    public MedianDataStream() {
        min = new PriorityQueue();
        max = new PriorityQueue(Collections.reverseOrder());
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if(max.size()<min.size())
            max.offer(min.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(max.size()>min.size()) {
            return (double)max.peek();
        }else {
            return (double)(max.peek() + min.peek())/2;
        }
    }
}