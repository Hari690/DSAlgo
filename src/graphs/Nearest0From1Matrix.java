package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Start with 0s and find all nearest ones.
 */
public class Nearest0From1Matrix {
    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public int[][] updateMatrix(int[][] mat) {
        int[][] result = new int[mat.length][mat[0].length];
        int ones = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i=0;i<mat.length;i++) {
            for (int j=0;j<mat[0].length;j++) {
                if(mat[i][j]==0)
                    queue.add(new Coordinate(i, j));
                else if(mat[i][j]==1)
                    ones++;
            }
        }
        Set<Coordinate> visited = new HashSet<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                Coordinate coordinate = queue.poll();

                for(int[] dir : directions) {
                    Coordinate c = new Coordinate(coordinate.x + dir[0], coordinate.y + dir[1]);
                    if(c.x>=0 && c.x<mat.length && c.y>=0 && c.y<mat[0].length && !visited.contains(c)) {
                        if(mat[c.x][c.y]==1) {
                            result[c.x][c.y] = distance+1;
                            ones--;
                        }
                        queue.add(c);
                        visited.add(c);
                    }
                }
                if(ones==0)
                    break;
            }
            distance++;
            if(ones==0)
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
        Nearest0From1Matrix nearest0From1Matrix = new Nearest0From1Matrix();
        nearest0From1Matrix.updateMatrix(matrix);
    }

}
