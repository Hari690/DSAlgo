package queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
 * You start on square 1 of the board. In each move, starting from square curr, do the following:
 * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
 * This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
 * If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
 * The game ends when you reach the square n2.
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
 *
 * Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
 *
 * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 * Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.
 *
 * Example 1:
 * Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * In the beginning, you start at square 1 (at row 5, column 0).
 * You decide to move to square 2 and must take the ladder to square 15.
 * You then decide to move to square 17 and must take the snake to square 13.
 * You then decide to move to square 14 and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * This is the lowest possible number of moves to reach the last square, so return 4.
 * Example 2:
 *
 * Input: board = [[-1,-1],[-1,3]]
 * Output: 1
 */
public class SnakeAndLadder {
    public int snakesAndLadders(int[][] board) {
        int k=board.length*board[0].length;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<board.length;i++) {
            // board is zigzag and pattern depends on no of rows
            if((board.length-i)%2==0) {
                for(int j=0;j<board[0].length;j++) {
                    if(board[i][j]!=-1) {
                        map.put(k, board[i][j]);
                    }
                    k--;
                }
            } else {
                for(int j=board[0].length-1;j>=0;j--) {
                    if(board[i][j]!=-1) {
                        map.put(k, board[i][j]);
                    }
                    k--;
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited.add(1);
        int steps = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int no = queue.poll();
                for(int j=1;j<=6;j++) {
                    int next = no+j;
                    if(map.containsKey(next))
                        next = map.get(next);
                    if(!visited.contains(next)) {
                        visited.add(next);
                        if(next==board.length*board[0].length)
                            return steps;
                        queue.add(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] board = {{-1,-1,19,10,-1},{2,-1,-1,6,-1},{-1,17,-1,19,-1},{25,-1,20,-1,-1},{-1,-1,-1,-1,15}};
        //int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};

        SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
        snakeAndLadder.snakesAndLadders(board);
    }
}
