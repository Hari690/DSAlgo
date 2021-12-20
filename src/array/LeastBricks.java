package array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks each of the same height
 * (i.e., one unit) but they can be of different widths. The total width of each row is the same.
 *
 * Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, then the
 * brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, in which case the
 * line will obviously cross no bricks.
 *
 * Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a
 * vertical line.
 */
/*
The idea is straightforward, since each brick length is positive,
we can record all the edge index in the wall and figure out which edge index is the most common.
We cut through that edge index, it will cross number of rows - most common edge count rows
 */
public class LeastBricks {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap();

        int count = 0;
        for (List<Integer> row : wall) {
            int sum = 0;
            // since for making cuts last element need not be counted, sum is till row.size()-1.
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                // simulates max no of blocks that did not get cut.
                count = Math.max(count, map.get(sum));
            }
        }

        // to get min no of bricks that did not get crossed,
        //  it's wall size - max no of blocks that did not get cut.
        return wall.size() - count;
    }
}
