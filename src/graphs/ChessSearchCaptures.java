package graphs;

/**
 * On an 8 x 8 chessboard, there is exactly one white rook 'R' and some number of white bishops 'B', black pawns 'p', and empty squares '.'.
 *
 * When the rook moves, it chooses one of four cardinal directions (north, east, south, or west), then moves in that direction until it
 * chooses to stop, reaches the edge of the board, captures a black pawn, or is blocked by a white bishop. A rook is considered attacking
 * a pawn if the rook can capture the pawn on the rook's turn. The number of available captures for the white rook is the number of pawns
 * that the rook is attacking.
 *
 * Return the number of available captures for the white rook.
 */
public class ChessSearchCaptures {
    public int numRookCaptures(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 'R') {
                    return Count(board, i, j);
                }
            }
        }
        return 0;
    }

    public int Count(char[][] board, int row, int col) {
        int res = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d: directions) {
            for (int i = row, j = col; i >= 0 && i < board.length && j >= 0 && j < board[0].length; i = i + d[0], j = j + d[1]) {
                if (board[i][j] == 'p') {
                    res++;
                    break;
                } else if (board[i][j] == 'B') {
                    break;
                }
            }
        }
        return res;
    }
}
