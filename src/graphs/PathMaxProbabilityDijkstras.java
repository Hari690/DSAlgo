package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 */
public class PathMaxProbabilityDijkstras {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Double, List<double[]>> graph = new HashMap();
        int j=0;
        for (int[] e: edges) {
            graph.computeIfAbsent((double)e[0], k-> new ArrayList<>()).add(new double[]{e[1],succProb[j]});
            graph.computeIfAbsent((double)e[1], k-> new ArrayList<>()).add(new double[]{e[0],succProb[j++]});
        }

        PriorityQueue<Double[]> maxHeap = new PriorityQueue<>((a, b)->b[1].compareTo(a[1]));
        maxHeap.offer(new Double[]{(double)start,1d});
        Set<Double> visited = new HashSet<>();

        while(!maxHeap.isEmpty()) {
            Double[] no = maxHeap.poll();
            if(no[0]==end)
                return no[1];

            if(visited.contains(no[0]) || !graph.containsKey(no[0]))
                continue;

            visited.add(no[0]);
            for (double[] x: graph.get(no[0])) {
                maxHeap.add(new Double[]{x[0], (no[1]*x[1])});
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        PathMaxProbabilityDijkstras maxProbability = new PathMaxProbabilityDijkstras();

        System.out.println(maxProbability.maxProbability(3, new int[][]{{0,1},{1,2},{0,2}},new double[]{0.5,0.5,0.2},0,2));
    }
}
