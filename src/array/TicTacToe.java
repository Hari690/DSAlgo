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
    // whenever it's size equals 3 we have a winner.
    public String tictactoe(int[][] moves) {

        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag1=0,diag2=0;

        int player = 1;
        int i;
        for(i=0;i<moves.length;i++) {
            int[] move = moves[i];
            int row=move[0];
            int col=move[1];

            if(row==col)
                diag1+=player;
            if(row+col==2)
                diag2+=player;

            rows[row]+=player;
            cols[col]+=player;

            if(Math.abs(rows[row])==3 || Math.abs(cols[col])==3 ||
                    Math.abs(diag1)==3 || Math.abs(diag2)==3) {
                return (player==1)?"A":"B";
            }

            player*=-1;
        }

        return (i==9)?"Draw":"Pending";
    }
}
