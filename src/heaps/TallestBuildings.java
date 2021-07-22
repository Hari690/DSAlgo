package heaps;

import java.util.PriorityQueue;

/**
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 */
public class TallestBuildings {
    public int furthestBuilding(int[] H, int B, int L) {
        int len = H.length - 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            int diff = H[i+1] - H[i];
            if (diff > 0) {
                if (L > 0) {
                    pq.add(diff);
                    L--;
                } else if (pq.size() > 0 && diff > pq.peek()) {
                    pq.add(diff);
                    B -= pq.poll();
                } else B -= diff;
                if (B < 0) return i;
            }
        }
        return len;
    }
}
