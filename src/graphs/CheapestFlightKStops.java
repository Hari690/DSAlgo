package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price
 * from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 */
/*
    DIJKSHTRA using Priority queue.
    similar to BFS except we are using a PQ after building a Adj List.
 */
public class CheapestFlightKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> graph = new ArrayList<>();
        //creating adjacency list for source cities.
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            //source city: [destination city, source to destination cost].
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        //cycle detection.
        Map<Integer,Integer> visited = new HashMap<>();
        //MinHeap: input format: [city, distance, cost], it compares based on cost.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minHeap.add(new int[]{src, 0, 0});
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int city = cur[0];
            int distance = cur[1], cost = cur[2];
            if(!visited.containsKey(city) || visited.get(city)>distance) {
                if (city == dst) {
                    return cost;
                }
                if (distance <= K) {
                    //add adjacent nodes.
                    for (int[] adjNode : graph.get(city)) {
                        minHeap.add(new int[]{adjNode[0], distance + 1, cost + adjNode[1]});
                    }
                }
            }
            visited.put(city, distance);
        }
        return -1;
    }
}
