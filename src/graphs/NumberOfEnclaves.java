package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 * Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 *
 * Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation: All 1s are either on the boundary or can reach the boundary.
 *
 *
 */
public class NumberOfEnclaves {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numEnclaves(int[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    int output=dfs(grid, i, j);
                    res+=(output==-1)?0:output;
                }
            }
        }

        return res;
    }

    public int dfs(int[][] grid, int x, int y){

        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return -1;

        // return as soon as we see a 1.
        if(grid[x][y] == 0) return 0;

        grid[x][y] = 0;

        int res = 1;
        // in case of boundary since all 4 directions does not five true we return false.
        for(int[] d : dir){
            // if we encounter a boundary i.e -1 then all connected points also need to be marked as 0 so don't terminate.
            // DO NOT RETURN WHEN MEET WITH BOUNDARY, you need to fill all island or the unfilled island may form a new island, which is wrong
            int result = dfs(grid, x + d[0], y + d[1]);
            if (result==-1) {
                res = -1;
            } else if (res!=-1){
                res += result;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        NumberOfEnclaves numberOfEnclaves = new NumberOfEnclaves();
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(numberOfEnclaves.numEnclaves(grid));
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            this.val = 0;
            this.neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            this.val = _val;
            this.neighbors = new ArrayList<>();
        }
        public Node(int _val, List<Node> neighbors) {
            this.val = _val;
            this.neighbors = neighbors;
        }
    }
}
