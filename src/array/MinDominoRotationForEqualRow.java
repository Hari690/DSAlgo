package array;

import java.util.Arrays;
import java.util.List;

/**
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
 *
 * If it cannot be done, return -1.
 * Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 * Example 2:
 *
 * Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 */
public class MinDominoRotationForEqualRow {
    public int minDominoRotations(int[] tops, int[] bottoms) {

        int min = Integer.MAX_VALUE;
        List<Integer> list = Arrays.asList(tops[0], bottoms[0]);

        for (int no : list) {
            int top = 0, bottom = 0;
            int i = 0;
            for (; i < tops.length; i++) {
                if (tops[i] != no && bottoms[i] != no)
                    break;
                if (tops[i] != no)
                    top++;
                if (bottoms[i] != no)
                    bottom++;
            }
            if (i != tops.length)
                continue;
            min = Math.min(min, Math.min(top, bottom));
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
}
