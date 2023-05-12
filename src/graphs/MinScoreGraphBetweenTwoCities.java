package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinScoreGraphBetweenTwoCities {

    int min = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        Map<Integer, List<Pair<Integer,Integer>>> adjList = new HashMap<>();

        for(int[] road : roads) {
            adjList.computeIfAbsent(road[0], k->new ArrayList<>()).add(new Pair<>(road[1],road[2]));
            adjList.computeIfAbsent(road[1], k->new ArrayList<>()).add(new Pair<>(road[0],road[2]));
        }

        Set<Integer> visited = new HashSet<>();
        dfs(1, adjList, visited);

        return min;

    }

    private void dfs(int n,Map<Integer, List<Pair<Integer,Integer>>> adjList, Set<Integer> visited) {
        if (visited.contains(n))
           return;
        visited.add(n);
        for (Pair<Integer,Integer> next : adjList.get(n)) {
            min = Math.min(min, next.val);
            dfs(next.key, adjList, visited);
        }
    }

    public static void main(String[] args) {
        int[][] roads = {{4,5,7468},{6,2,7173},{6,3,8365},{2,3,7674},{5,6,7852},{1,2,8547},{2,4,1885},{2,5,5192},{1,3,4065},{1,4,7357}};

        int minScore =  new MinScoreGraphBetweenTwoCities().minScore(6, roads);
        System.out.println(minScore);
    }
}
