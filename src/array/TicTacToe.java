package array;

/**
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark
 * their respective character in the order in which A and B play.
 *
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to
 * play return "Pending".
 *
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
 */
public class TicTacToe {

    // use 1 and -1 to demarcate the players and keep filling the 1-D array to keep track.
    // whenever it's size equals 3 we have an output.
    public String tictactoe(int[][] moves) {

        // n stands for the size of the board, n = 3 for the current game.
        int n = 3;

        // Use rows and cols to record the value on each row and each column.
        // diag1 and diag2 to record value on diagonal or anti-diagonal.
        int[] rows = new int[n], cols = new int[n];
        int diag = 0, anti_diag = 0;

        // Two players having value of 1 and -1, player_1 with value = 1 places first.
        int player = 1;

        for (int[] move : moves){

            // Get the row number and column number for this move.
            int row = move[0], col = move[1];

            // Update the row value and column value.
            rows[row] += player;
            cols[col] += player;

            // If this move is placed on diagonal or anti-diagonal,
            // we shall update the relative value as well.
            if (row == col){
                diag += player;
            }
            if (row + col == n - 1){
                anti_diag += player;
            }

            // Check if this move meets any of the winning conditions.
            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n ||
                Math.abs(diag) == n || Math.abs(anti_diag) == n){
                return player == 1 ? "A" : "B";
            }

            // If no one wins so far, change to the other player alternatively.
            // That is from 1 to -1, from -1 to 1.
            player *= -1;
        }

        // If all moves are completed and there is still no result, we shall check if
        // the grid is full or not. If so, the game ends with draw, otherwise pending.
        return moves.length == n * n ? "Draw" : "Pending";
    }
}
