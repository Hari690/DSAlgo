package heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an integer n. There are n rooms numbered from 0 to n - 1.
 * You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.
 * Meetings are allocated to rooms in the following manner:
 * Each meeting will take place in the unused room with the lowest number.
 * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
 * When a room becomes unused, meetings that have an earlier original start time should be given the room.
 * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
 *
 * A half-closed interval [a, b) is the interval between a and b including a and not including b.
 */
public class MeetingRoom3RoomWithMostMeetings {
    public int mostBooked(int n, int[][] meetings) {
        // sort by start
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        // availableRooms - to keep track of smallest no free room
        var availableRooms = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++)
            availableRooms.add(i);

        // { meeting end, room taken } -> sort by end time and by room number
        var runningMeetings = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // room usage count
        var count = new int[n];
        for (int[] meeting : meetings) {
            // return room to available if meeting has ended
            while (!runningMeetings.isEmpty() && runningMeetings.peek()[0] <= meeting[0])
                availableRooms.add(runningMeetings.poll()[1]);

            // if all rooms are occupied then look at earliest ending meeting and find corresponding room and add it to available rooms
            var startTime = meeting[0];
            if (availableRooms.isEmpty()) { // no available availableRooms, adjust the next meeting start time with delay
                var await = runningMeetings.poll();
                startTime = await[0];
                availableRooms.add(await[1]);
            }

            // schedule the next meeting
            var room = availableRooms.poll();
            count[room]++;
            runningMeetings.add(new int[] { startTime + meeting[1] - meeting[0], room});
        }

        // find the most used room
        var maxIdx = 0;
        for (int r = 0; r < count.length; r++)
            if (count[maxIdx] < count[r])
                maxIdx = r;

        return maxIdx;
    }
}
