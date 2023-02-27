package problems;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * Input: board = [["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],
 * ["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],
 * ["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],
 * ["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],
 * ["3","4","5","2","8","6","1","7","9"]]
 *
 */
class  SolveSudoku {
    /*
        Time complexity - 9 ^ m (m represents the number of blanks to be filled in)

        O(n ^ m) where n is the number of possibilities for each square (i.e., 9 in classic Sudoku) and m is the number of spaces that are blank.
        This can be seen by working backwards from only a single blank. If there is only one blank, then you have n possibilities that you must work through in the worst case. If there are two blanks, then you must work through n possibilities for the first blank and n possibilities for the second blank for each of the possibilities for the first blank.
        If there are three blanks, then you must work through n possibilities for the first blank. Each of those possibilities will yield a puzzle with two blanks that has n^2 possibilities.
        This algorithm performs a depth-first search through the possible solutions. Each level of the graph represents the choices for a single square. The depth of the graph is the number of squares that need to be filled. With a branching factor of n and a depth of m, finding a solution in the graph has a worst-case performance of O(n ^ m).
     */
    public boolean solveSudoku(char[][] board) {
        // input validation
        if(board == null || board.length == 0)
            return true;
        return solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++) {//trial. Try 1 through 9
                        if(board[i][j] == '.' && isValid(board, i, j, c)) {
                            board[i][j] = c; //Put c for this cell
                            if(solve(board))
                                return true; //If it's the solution return true
                            board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    return false;
                }
            }
        }
        // this is hit only after filling entire grid
        return true;
//        int[][] rowMatrix = new int[9][9];
//        int[][] colMatrix = new int[9][9];
//        int[][] gridMatrix = new int[9][9];
//
//        for(int i = 0; i < board.length; i++){
//            for(int j = 0; j < board[0].length; j++){
//                if(board[i][j] == '.') {
//                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
//                        if(board[i][j] == '.' && isValidSet(i, j, c, rowMatrix, colMatrix, gridMatrix)) {
//                            board[i][j] = c; //Put c for this cell
//                            if(solve(board))
//                                return true; //If it's the solution return true
//                            else
//                                board[i][j] = '.'; //Otherwise go back
//                        }
//                    }
//                    return false;
//                }
//            }
//        }
//        // this is hit only after filling entire grid
//        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == c)
                return false; //check row
            if(board[i][col] == c)
                return false; //check column
        }

        int subgridRowStart = (row/3) * 3;
        int subgridColStart = (col/3) * 3;
        for(int j=subgridRowStart;j<subgridRowStart + 3;j++) {
            for (int k = subgridColStart; k < subgridColStart + 3; k++) {
                if(board[j][k]==c)
                    return false;
            }
        }
        return true;
    }

    private boolean isValidSet(int row, int col, char number, int[][] rowMatrix, int[][] colMatrix, int[][] gridMatrix) {
        if (number != '.') {
            int gridNo = (row/3)*3 +col/3;
            if(rowMatrix[row][number-'1']!=0 || colMatrix[col][number-'1']!=0 || gridMatrix[gridNo][number-'1']!=0)
                return false;
            rowMatrix[row][number-'1'] = 1;
            colMatrix[col][number-'1'] = 1;
            gridMatrix[gridNo][number-'1'] = 1;
        }
        return true;
    }

    private <T> String getKey(T... values) {
        return Arrays.stream(values).map(String::valueOf).collect(Collectors.joining("#"));
    }

    public static void print(
            char[][] board, int N)
    {
        // we got the answer, just print it
        for (char r = 0; r < N; r++) {
            for (char d = 0; d < N; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int)Math.sqrt(N) == 0) {
                System.out.print("");
            }
        }
    }

    // Driver Code
    public static void main(String args[])
    {

        char[][] board = new char[][] { {'5','3','.','.','7','.','.','.','.'},
                                        {'6','.','.','1','9','5','.','.','.'},
                                        {'.','9','8','.','.','.','.','6','.'},
                                        {'8','.','.','.','6','.','.','.','3'},
                                        {'4','.','.','8','.','3','.','.','1'},
                                        {'7','.','.','.','2','.','.','.','6'},
                                        {'.','6','.','.','.','.','2','8','.'},
                                        {'.','.','.','4','1','9','.','.','5'},
                                        {'.','.','.','.','8','.','.','7','9'}
        };
        SolveSudoku solveSudoku = new SolveSudoku();
        if (solveSudoku.solveSudoku(board)) {
            // print solution
            print(board, 9);
        }
        else {
            System.out.println("No solution");
        }
    }
}