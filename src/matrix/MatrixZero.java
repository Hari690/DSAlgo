package matrix;

import java.util.HashSet;
import java.util.Set;

/*
Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 */
public class MatrixZero {

    class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        new MatrixZero().setZeroes(matrix);
        System.out.println(matrix);
    }

    public void setZeroes(int[][] matrix) {

        Set<Coordinate> matrixZeroes = new HashSet<>();

        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                if(matrix[i][j]==0) {
                    matrixZeroes.add(new Coordinate(i,j));
                }
            }
        }

        for(Coordinate coordinate : matrixZeroes) {
            for(int k=0;k<matrix[0].length;k++) {
                matrix[coordinate.x][k]=0;
            }
            for(int k=0;k<matrix.length;k++) {
                matrix[k][coordinate.y]=0;
            }
        }

    }
}
