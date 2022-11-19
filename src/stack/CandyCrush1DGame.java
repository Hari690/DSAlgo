package stack;

import java.util.Stack;

/**
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 * If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 * After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 *
 * Input: "aaabbbc"
 * Output: "c"
 * Explanation:
 * 1. Remove 3 'a': "aaabbbbc" => "bbbbc"
 * 2. Remove 4 'b': "bbbbc" => "c"
 */
public class CandyCrush1DGame {

    /*
        We keep accumlating characters and occurences in two separate stacks.
        Intuition - When character changes at the top of stack then we check top of stack and no of occurences of that
        character and pop if it's greater than 3.
     */
    public String solution(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> occurence = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (stack.isEmpty() || c != stack.peek()) {
                // when we have enough candies to crush
                if (!stack.isEmpty() && occurence.peek() >= 3) {
                    stack.pop();
                    occurence.pop();
                } else {
                    // when we have to insert a new candy
                    stack.push(c);
                    occurence.push(1);
                    i++;
                }
            } else {
                // if top char in stack is same then for continuous popping we keep incrementing occurence until we can pop together.
                // This is so when a different char comes we can pop the stacks together.
                int count = occurence.pop();
                occurence.push(count + 1);
                i++;
            }
        }

        if (occurence.peek() >= 3) {
            stack.pop();
            occurence.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            int count = occurence.pop();
            while (count > 0) {
                sb.append(c);
                count--;
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        CandyCrush1DGame solution = new CandyCrush1DGame();
        System.out.println(solution.solution("aaabbbc"));
        System.out.println(solution.solution("aabbbacd"));
        System.out.println(solution.solution("aabbccddeeedcba"));
        System.out.println(solution.solution("aaabbbacd"));
    }
}
