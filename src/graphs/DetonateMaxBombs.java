package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate
 * and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 * You may choose to detonate a single bomb. When a bomb is detonated,
 * it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 * Input: bombs = [[2,1,3],[6,1,4]]
 * Output: 2
 * Explanation:
 * The above figure shows the positions and ranges of the 2 bombs.
 * If we detonate the left bomb, the right bomb will not be affected.
 * But if we detonate the right bomb, both bombs will be detonated.
 * So the maximum bombs that can be detonated is max(1, 2) = 2.
 *
 * Input: bombs = [[1,1,5],[10,10,5]]
 * Output: 1
 * Explanation:
 * Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
 */
public class DetonateMaxBombs {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        int n = bombs.length;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                long distance = distance(bombs[i], bombs[j]);
                if(distance<=(long)bombs[i][2]*bombs[i][2]) {
                    adjList.computeIfAbsent(i, k-> new HashSet<>()).add(j);
                }
                if(distance<=(long)bombs[j][2]*bombs[j][2]) {
                    adjList.computeIfAbsent(j, k-> new HashSet<>()).add(i);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> visited = new HashSet<>();
            dfs(n, i, visited , bombs, adjList);
            ans = Math.max(ans, visited.size());
        }
        return ans;
    }

    private void dfs(int n, int idx, Set<Integer> visited, int[][] bombs, Map<Integer, Set<Integer>> adjList) {
        visited.add(idx);
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i) && adjList.containsKey(idx) && adjList.get(idx).contains(i)) {
                visited.add(i);
                dfs(n, i, visited, bombs, adjList);
            }
        }
    }

    private long distance(int[] a, int[] b) {
        long dx = a[0] - b[0], dy = a[1] - b[1], r = a[2];
        return dx * dx + dy * dy;
    }

    public static void main(String[] args) {
        DetonateMaxBombs detonateMaxBombs = new DetonateMaxBombs();
        int[][] bombs = {{2,1,3},{6,1,4}};
        detonateMaxBombs.maximumDetonation(bombs);
    }
}
