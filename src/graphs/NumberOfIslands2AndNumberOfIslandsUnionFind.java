package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 *
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 */
public class NumberOfIslands2AndNumberOfIslandsUnionFind {

    public static void main(String[] args) {

        char[][] grid = new char[0][];

        NumberOfIslands2AndNumberOfIslandsUnionFind.numIslands(grid);

        int[][] positions = {{0,0}, {0,1}, {1,2}, {2,1},{0,2}};

        System.out.println(NumberOfIslands2AndNumberOfIslandsUnionFind.numIslands2(3,3, positions));
    }

    public static int numIslands(char[][] grid) {
        int numIslands = 0;

        for( int i=0; i<grid.length; i++) {
            for( int j=0; j<grid[0].length; j++) {
                if(grid[i][j]=='1') {
                    numIslands += dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    public static int dfs(char[][] grid, int i, int j) {
        if(i>grid.length || j>grid[0].length || i<0 || j<0 || grid[i][j]=='0') {
            return 0;
        }
        grid[i][j]='0';
        dfs(grid, i+1, j);
        dfs(grid, i, j+1);
        dfs(grid, i-1, j);
        dfs(grid, i, j-1);
        return 1;
    }

    static int[][] dirs = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };

    /*
        Union Find - Trick is to check adjacent cells while merging and see if they can
        be made part of same island by merging and assigning same parent for them.
        If they can reduce 1 for that case.
        Called using disjoint sets with path compression( make node to point to parent of chain).
        O(n2) + O(k) , DFS would be O(n2k)
     */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(m <= 0 || n <= 0) {
            return result;
        }

        int count = 0;                      // number of islands

        // roots[c] = p means the parent of node c is p
        int[] roots = new int[m * n];       // one island = one tree

        Arrays.fill(roots, -1);

        for(int[] p : positions) {
            int root = n * p[0] + p[1];     // assume new point is isolated island
            roots[root] = root;             // add new island i.e create it's own group
            count++;

            for(int[] dir : dirs) { // probe 4 directions
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int nb = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) {
                    continue;
                }
                // this will either return the node parent itself or it will return final parent of the chain
                int rootNb = findIsland(roots, nb);
                // O(1) operation set one parent to other parent
                if(root != rootNb) {        // if neighbor is in another island
                    roots[root] = rootNb;   // union two islands i.e chain them together
                    root = rootNb;          // current tree root = joined tree root
                    count--;
                }
            }

            result.add(count);
        }

        return result;
    }

    /*
        O(n2)
        Remember that one island could have a different roots[node] value for each node.
        Because roots[node] is the parent of the node, not the highest root of the island.
        To find the actual root, we have to climb up the tree by calling the findIsland function.
        We could also maintain a rank array to reduce the size of tree in the chaining then we would increment count of
        parent of a group
     */
    public static int findIsland(int[] roots, int id) {
        // index equals value at the index determines if it's final parent of a chain so we walk down until that.
        while(id != roots[id]) {
            id = roots[id];
        }
        return id;
    }

}
