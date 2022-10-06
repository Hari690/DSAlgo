package heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 * You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.
 * Return the maximum number of events you can attend.
 * Example 1:
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 */
public class MaxNoOfEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0])); // sort events increasing by start time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int ans = 0, i = 0, n = events.length;
        for (int d = 1; d <= 100000; d++) {
            while (i < n && events[i][0] == d) { // Add new events that can attend on day `d`
                minHeap.add(events[i++][1]);
            }
            while (!minHeap.isEmpty() && minHeap.peek() < d) { // Remove events that are already closed
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) { // Use day `d` to attend to the event that closes earlier
                ans++;
                minHeap.poll();
            }
        }
        return ans;
    }
}
