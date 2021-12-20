package oops.connect4;

import oops.connect4.model.Player;
import oops.connect4.model.Stone;

public class Connect4Driver {


    /**
     * Steps
     * 1. Initialise players.
     * 2. Initialise game with board and players.
     * 3. Initialise the WinChecker class which has the strategy for checking if a player has won.
     * 4. start playing.
     * 4.1 choose column and insert. We will insert at lowest position in column
     * 4.2 Flip the player.
     * 4.3 check if player has won. If yes, return.
     * @param args
     */
    public static void main(String[] args) {
        int height =7;
        int width=6;
        Player P1 = new Player("P1", Stone.RED);
        Player P2 = new Player("P2", Stone.BLUE);

        Connect4Game game = new Connect4GameImpl(height,width,new Player[]{P1,P2});
        Player winner = game.play();
        System.out.println(winner);
    }

}
