package oops.connect4;

import oops.connect4.model.Board;
import oops.connect4.model.Player;
import oops.connect4.model.Position;

import java.util.Scanner;

public class Connect4GameImpl implements Connect4Game{

    private Board board;
    private int currentPlayer;
    private Player[] players;
    private Connect4WinChecker winChecker;
    private Scanner scanner;

    public Connect4GameImpl(int height, int weight, Player[] players) {
        this.scanner = new Scanner(System.in);
        this.board = new Board(height,weight);
        this.players = players;
        this.currentPlayer=0;
        this.winChecker = new Connect4WinChecker(board);
    }

    @Override
    public Player play(){
        Player winner=null;
        while (winner==null){
            winner = makeMove();
            currentPlayer = (currentPlayer+1)%2;
        }
        board.show();
        return winner;
    }

    @Override
    public Player makeMove() {
        board.show();
        int playersColumn = getUserInput();
        Position pos = board.putStoneAt(playersColumn, players[currentPlayer].getStone());
        return winChecker.isWinner(pos) ? players[currentPlayer] : null;
    }

    private int getUserInput() {
        System.out.print(String.format("%s, please select a column (1 - %d):", players[currentPlayer].getName(),board.getWidth()));
        int playersColumn = scanner.nextInt() - 1;
        return playersColumn;
    }

    /**
     * Similar approach for Tic Tac Toe.
     * Use enum or number to set value in the 3*3 grid for player 1&2.
     * Tic-tac-toe move will accept coordinate instead of setting value in grid.
     * For winning strategy use coordinate , check row column, diagonal and reverse diagonal.
     * Write javadoc for the methods.
     * Throw IllegalArgumentException for validation errors.
     * Diagonal condition check only if there's a move done there.
     * Can also condense the 4loops into single and set 4 different boolean variables when any
     * condition is met for code organization.
     * Can use int[] rowSum and int[] colSum to evaluate if a player has won in O(1) by adding the
     * players no to it and then checking absolute value is same as n.
     * In that case set the winner.
     */

}
