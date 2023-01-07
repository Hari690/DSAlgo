package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 *
 * Example 1:
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 *
 * Example 2:
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 *
 * Example 3:
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 */
public class PossibleBipartition {

    /*
        group[i] = 0 means node i hasn't been visited.
        group[i] = 1 means node i has been grouped to 1.
        group[i] = -1 means node i has been grouped to -1.
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] d : dislikes) {
            map.computeIfAbsent(d[0]-1, k -> new ArrayList<>()).add(d[1]-1);
            map.computeIfAbsent(d[1]-1, k -> new ArrayList<>()).add(d[0]-1);
        }
        int[] group = new int[N];
        for (int i: map.keySet()) {
            if (group[i] == 0 && !dfs(map, group, i, 1)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(Map<Integer, List<Integer>> map, int[] group, int index, int g) {
        group[index] = g;

        for(int v : map.get(index)) {
            if (group[v] == g) {
                return false;
            }
            if (group[v] == 0 && !dfs(map, group, v, -g)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PossibleBipartition possibleBipartition = new PossibleBipartition();
        possibleBipartition.possibleBipartition(4, new int[][]{{1,2},{1,3},{2,4}});
    }
}
