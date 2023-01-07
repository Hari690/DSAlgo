package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Can also be done using a minHeap by putting destination and people leaving inside a heap
 * and checking if we need to decrement passengers based on the value in the heap.
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 */
public class CarPoolingStops {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] path = new int[1001];
        for (int[] trip : trips) {
            // entering;  value is number of passengers
            path[trip[1]]=path[trip[1]]+trip[0];
            path[trip[2]]=path[trip[2]]-trip[0];    // leaving
        }

        int total = 0;
        for (int p : path) {
            total+=p;
            if(total>capacity)
                return false;
        }
        return true;
    }

    public boolean carPoolingHeap(int[][] trips, int capacity) {
        Arrays.sort(trips, Comparator.comparing(trip -> trip[1]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(trip -> trip[2]));
        for (int[] trip : trips) {
            while (!pq.isEmpty() && trip[1] >= pq.peek()[2]) // any passengers need to get off?
                capacity += pq.poll()[0]; // more capacity as passengers out.
            capacity -= trip[0]; // less capacity as passengers in.
            if (capacity < 0) return false; // not enough capacity.
            pq.offer(trip); // put into PriorityQueue the infomation at current location.
        }
        return true;
    }

    public static void main(String[] args) {
        CarPoolingStops carPooling = new CarPoolingStops();
        int[][] trips = {{2,1,5},{3,3,7}};
        carPooling.carPooling(trips, 5);
    }
}
