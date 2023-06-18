package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 *
 * Example 1:
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 *
 * Example 2:
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 */
public class EqualRowColumnPairs {
    public int equalPairs(int[][] grid) {
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<grid.length;i++) {
            String hash = getHash(grid[i]);
            map.put(hash, map.getOrDefault(hash, 0)+1);
        }

        int result = 0;
        int[] curr = new int[grid[0].length];
        for(int j=0;j<grid[0].length;j++) {
            for(int i=0;i<grid.length;i++) {
                curr[i]=grid[i][j];
            }
            String hash = getHash(curr);
            if(map.containsKey(hash)) {
                result+=map.get(hash);
            }
        }

        return result;
    }

    private String getHash(int[] key) {
        return Arrays.stream(key).mapToObj(Integer::toString).collect(Collectors.joining(","));
    }
}
