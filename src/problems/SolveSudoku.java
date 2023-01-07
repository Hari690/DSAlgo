package problems;

/* A Backtracking program in
Java to solve problems.Sudoku problem */
class SolveSudoku {
    public boolean solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return true;
        return solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
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

        char[][] board = new char[][] {
                { '3', '0', '6', '5', '0', '8', '4', '0', '0' },
                { '5', '2', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '8', '7', '0', '0', '0', '0', '3', '1' },
                { '0', '0', '3', '0', '1', '0', '0', '8', '0' },
                { '9', '0', '0', '8', '6', '3', '0', '0', '5' },
                { '0', '5', '0', '0', '9', '0', '6', '0', '0' },
                { '1', '3', '0', '0', '0', '0', '2', '5', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '7', '4' },
                { '0', '0', '5', '2', '0', '6', '3', '0', '0' }
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

// This code is contributed
// by MohanDas
