package oops.connect4;

import oops.connect4.model.Board;
import oops.connect4.model.Position;
import oops.connect4.model.Stone;

public class Connect4WinChecker {

    private static final int REQUIRED_CONSECUTIVE_FIELDS = 4;
    private Board board;

    public Connect4WinChecker(Board board) {
        this.board = board;
    }

    // to compute this in O(1) pre-compute scores of both players in a separate grid and use that to check if any player has won.
    public boolean isWinner(Position lastMove){
        return checkVertical(lastMove) || checkHorizontal(lastMove) || checkDiagonalUp(lastMove) || checkDiagonalDown(lastMove);
    }

    // move through all columns and check starting 0 to height - maxField and count up and check if stone is same.
    // If it is , update count and check in the end if no of stones has reached count.
    private boolean checkVertical(Position lastMove){
        int stoneCount=0;
        Stone stone = board.stoneAt(lastMove);
        for(int col=0;col<board.getWidth();col++){
            for(int row=0;row<(board.getHeight()-REQUIRED_CONSECUTIVE_FIELDS-1);row++){
                for(int i=row;i<row+REQUIRED_CONSECUTIVE_FIELDS;i++){
                    if(board.stoneAt(new Position(i,col))==stone){
                        stoneCount++;
                    }
                }
                if(stoneCount>=REQUIRED_CONSECUTIVE_FIELDS){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontal(Position lastMove){
        int stoneCount=0;
        Stone stone = board.stoneAt(lastMove);
        for(int row=0;row<board.getHeight();row++){
            for(int col=0;col<(board.getWidth()-REQUIRED_CONSECUTIVE_FIELDS-1);col++){
                for(int i=col;i<(col+REQUIRED_CONSECUTIVE_FIELDS);i++){
                    if(board.stoneAt(new Position(row,i))==stone){
                        stoneCount++;
                    }
                }
                if(stoneCount==REQUIRED_CONSECUTIVE_FIELDS){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalUp(Position lastMove){
        int stoneCount=0;
        Stone stone = board.stoneAt(lastMove);
        for(int row=0;row< (board.getHeight()-REQUIRED_CONSECUTIVE_FIELDS-1);row++){
            for(int col=0;col<(board.getWidth()-REQUIRED_CONSECUTIVE_FIELDS-1);col++){
                for(int i=row,j=col;i<(row+4);i++,j++){
                    if(board.stoneAt(new Position(i,j))==stone){
                        stoneCount++;
                    }
                }
                if(stoneCount==REQUIRED_CONSECUTIVE_FIELDS){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalDown(Position lastMove){
        int stoneCount=0;
        Stone stone = board.stoneAt(lastMove);
        for(int row=(board.getHeight()-1);row>(REQUIRED_CONSECUTIVE_FIELDS-1);row++){
            for(int col=0;col<(board.getWidth()-REQUIRED_CONSECUTIVE_FIELDS-1);col++){
                for(int i=row,j=col;i>(row-REQUIRED_CONSECUTIVE_FIELDS);i--,j++){
                    if(board.stoneAt(new Position(i,j))==stone){
                        stoneCount++;
                    }
                }
                if(stoneCount==REQUIRED_CONSECUTIVE_FIELDS){
                    return true;
                }
            }
        }
        return false;
    }


}
