package heaps;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        // Comparator based on difference in distance between distance of both points from origin.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((int[] p1,int[] p2) ->{
            // Difference between distance of both points to origin.
            return (p2[0]*p2[0] + p2[1]*p2[1])-(p1[0]*p1[0] + p1[1]*p1[1]);
        });

        // Trim the max heap.
        for (int[] point : points) {
            maxHeap.offer(point);
            if ( maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        for(int i=0;i<k;i++) {
            result[i] = maxHeap.poll();
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] points = {{1,3},{-2,2}};
        KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();
        System.out.println(kClosestPointsToOrigin.kClosest(points,1));
    }

}
