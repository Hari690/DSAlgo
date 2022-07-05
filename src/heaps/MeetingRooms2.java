package heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {
    // Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
    // find the minimum number of conference rooms required.

    // For example,
    // Given [[0, 30],[5, 10],[15, 20]],
    // return 2.

    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] itv : intervals) {
            // compare only with start time.
            if (heap.size()>0 && itv[0] >= heap.peek()) {
                heap.poll();
            }
            // insert only end time.
            heap.offer(itv[1]);
        }
        return heap.size();
    }

    public static void main(String[] args) {
        MeetingRooms2 meetingRooms = new MeetingRooms2();
        int[][] intervals = {{0, 30},{5, 10},{15, 20}};
        System.out.println(meetingRooms.minMeetingRooms(intervals));
    }
}
