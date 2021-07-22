package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given
 * the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key
 * point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a
 * y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and
 * rightmost buildings should be part of the skyline's contour.
 */
public class SkylineProblem {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();     // height list to store all buildings' heights
        for (int[] b : buildings) {
            height.add(new int[]{b[0], - b[2]});    // start of a building, height stored as negtive
            height.add(new int[]{b[1], b[2]});      // end of a building, height stored as positive
        }
        Collections.sort(height, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));     // sort the height list

        // a pq that stores all the encountered buildings' heights in descending order
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int preMax = 0;

        for (int[] h : height) {
            if (h[1] < 0) {     // h[1] < 0, that means it meets a new building, so add it to pq
                pq.offer(-h[1]);
            } else {            // h[1] >=0, that means it has reached the end of the building, so remove it from pq
                pq.remove(h[1]);
            }

            // the current max height in all encountered buildings
            int curMax = pq.peek();
            // if the max height is different from the previous one, that means a critical point is met, add to result list
            if (curMax != preMax) {
                res.add(new int[]{h[0], curMax});
                preMax = curMax;
            }
        }
        return res;
    }
}
