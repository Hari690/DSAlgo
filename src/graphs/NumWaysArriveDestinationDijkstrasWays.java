package graphs;

import java.util.*;

/**
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
 */
public class NumWaysArriveDestinationDijkstrasWays {

    int[] dist;
    long[] dp;

    /*
        Here visited set is not used since we need to compute all paths in Dijkstra's to see how many have minimum distance.
        Also since we update distance and add to pq only find a shorter distance there's no
        risk with not using a visited set as such.
        Visited set is for getting to a particular node or if graph is partial.
        We use a dp array with distance for 3 cases.
        1. If we find a smaller distance, we update dp array of parent to dp array of child.
        2. If we find same distance we have come from another path, so update that count to dp array.
        3. If more do nothing.
     */
    public int countPaths(int n, int[][] roads) {

        Map<Integer,List<int[]>> adjMap = new HashMap<>();
        for(int[] road : roads) {
            List<int[]> values = adjMap.get(road[0]);
            if(values==null) {
                values = new ArrayList<>();
                adjMap.put(road[0], values);
            }
            values.add(new int[]{road[1],road[2]});
            values = adjMap.get(road[1]);
            if(values==null) {
                values = new ArrayList<>();
                adjMap.put(road[1], values);
            }
            values.add(new int[]{road[0],road[2]});
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        minHeap.add(new int[]{0,0});
        int[] distances = new int[n];
        long[] dp = new long[n];
        dp[0]=1;
        Arrays.fill(distances, Integer.MAX_VALUE);

        while(!minHeap.isEmpty()) {
            int[] u = minHeap.poll();
            List<int[]> neighbours = adjMap.get(u[0]);
            if(neighbours==null)
                continue;
            for(int[] v : neighbours) {
                int d = u[1]+v[1];
                if(distances[v[0]]>d) {
                    minHeap.add(new int[]{v[0],d});
                    distances[v[0]] = d;
                    dp[v[0]]=dp[u[0]];
                } else if (distances[v[0]]==d) {
                    dp[v[0]]+=dp[u[0]];
                    dp[v[0]]%= 1_000_000_007;
                }
            }
        }
        return (int)dp[n-1]%1_000_000_007;
    }

    public static void main(String[] args) {
        //int[][] arr = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        int[][] arr = {{1,0,10}};
        NumWaysArriveDestinationDijkstrasWays solution = new NumWaysArriveDestinationDijkstrasWays();
        solution.countPaths(2, arr);
    }
}
