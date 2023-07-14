package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an m x n grid grid where:
 *
 * '.' is an empty cell.
 * '#' is a wall.
 * '@' is the starting point.
 * Lowercase letters represent keys.
 * Uppercase letters represent locks.
 * You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.
 * If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
 * For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 * Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
 *
 * Example 1:
 * Input: grid = ["@.a..","###.#","b.A.B"]
 * Output: 8
 * Explanation: Note that the goal is to obtain all the keys not to open all the locks.
 *
 * Example 2:
 * Input: grid = ["@..aA","..B#.","....b"]
 * Output: 6
 *
 * Example 3:
 * Input: grid = ["@Aa"]
 * Output: -1
 */
public class ShortestPathWithKeysMaintainStateBFS {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /*
        the order to pick up the key matters, so need to keep track of different paths to pick up keys
        need to use another class to keep track of:
        the (i,j)
        need to track which key is collected - because [a,f] so one integer is enough, like a mask
        handle the visited point for DIFFERENT PATHS - "x y key"
     */
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int si = -1, sj = -1, k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    si = i;
                    sj = j;
                }
                if (isKey(c)) k++;
            }
        }
        Node start = new Node(si, sj, 0);
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(si + " " +  sj + " " + 0);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (cur.key == (1 << k) - 1) return level;
                for (int[] d : dirs) {
                    int x = cur.i + d[0];
                    int y = cur.j + d[1];
                    int key = cur.key;
                    if (!isValid(grid, x, y, m, n)) continue;
                    char c = grid[x].charAt(y);
                    if (isKey(c)) key |= (1 << (c - 'a'));
                    if (isLock(c) && (key >> (c - 'A') & 1) == 0) continue;
                    if (visited.add(x + " " + y + " " + key)) q.offer(new Node(x, y, key));
                }
            }
            level++;
        }
        return -1;
    }

    private boolean isLock(char c) {
        return c >= 'A' && c <= 'F';
    }

    private boolean isKey(char c) {
        return c >= 'a' && c <= 'f';
    }

    private boolean isValid(String[] grid, int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i].charAt(j) != '#';
    }

    class Node {
        int i, j, key;
        public Node(int i, int j, int key) {
            this.i = i;
            this.j = j;
            this.key = key;
        }
    }

    class Node2 {
        int row;
        int col;
        String key;

        Node2(int row, int col, String key) {
            this.row = row;
            this.col = col;
            this.key = key;
        }
    }

    class Solution {

        public int shortestPathAllKeys(String[] grid) {
            int n = grid.length;
            int m = grid[0].length();

            int[] x_dirs = {0, 0, 1, -1};
            int[] y_dirs = {1, -1, 0, 0};
            Set<String> visited = new HashSet<>();

            int steps = 0;
            Queue<Node2> queue = new LinkedList<>();
            int count = 0;

            char[][] matrix = new char[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = grid[i].charAt(j);
                    if (matrix[i][j] == '@') {
                        queue.add(new Node2(i, j, ""));
                    } else if (matrix[i][j] >= 'a' && matrix[i][j] <= 'f') {
                        count++;
                    }
                }
            }

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int ind = 0; ind < size; ind++) {
                    Node2 node = queue.poll();
                    int row = node.row;
                    int col = node.col;
                    String key = node.key;

                    if (!visited.contains(toString(row, col, key))) {
                        visited.add(toString(row, col, key));
                    } else {
                        continue;
                    }

                    if (key.length() == count) {
                        return steps;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nrow = x_dirs[i] + row;
                        int ncol = y_dirs[i] + col;
                        if (nrow < 0 || nrow >= n || ncol < 0 || ncol >= m || matrix[nrow][ncol] == '#' || visited.contains(toString(nrow, ncol, key))) {
                            continue;
                        }
                        char ch = matrix[nrow][ncol];
                        if (key.indexOf(ch) == -1 && ch >= 'a' && ch <= 'f') {
                            queue.add(new Node2(nrow, ncol, key + ch));
                        } else if (ch >= 'A' && ch <= 'F' && key.indexOf(Character.toLowerCase(ch)) == -1) {
                        } else {
                            queue.add(new Node2(nrow, ncol, key));
                        }
                    }
                }
                steps++;
            }
            return -1;
        }

        public String toString(int row, int col, String key) {
            return row + "-" + col + "-" + key;
        }
    }
}
