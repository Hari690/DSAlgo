package array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageWindow {
    private int sum;
    private Queue<Integer> queue;
    private int capacity;

    /** Initialize your data structure here. */
    public MovingAverageWindow(int size) {
        queue = new LinkedList<>();
        sum = 0;
        capacity = size;
    }

    public double next(int val) {
        if (queue.size() == capacity) {
            int sub = queue.poll();
            sum = sum - sub;
        }
        queue.offer(val);
        sum = sum + val;

        return (double) sum / queue.size();
    }
}
