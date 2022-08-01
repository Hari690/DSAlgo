package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in a group.
 * And the group of two or more people wants to meet and minimize the total travel distance.
 * They can meet anywhere means that there might be a home or not.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x – p1.x| + |p2.y – p1.y|.
 *
 * Find the total distance that needs to be traveled to reach the best meeting point (Total distance traveled is minimum).
 * Steps :-
 * 1) Store all horizontal and vertical positions of all group member.
 * 2) Now sort it to find minimum middle position, which will be the best meeting point.
 * 3) Find the distance of all members from best meeting point.
 * For example in above diagram, horizontal positions are {0, 2, 0} and vertical positions are {0, 2, 4}.
 * After sorting both, we get {0, 0, 2} and {0, 2, 4}. Middle point is (0, 2).
 * Note : Even no. of 1’s have two middle points, then also it works.
 * Two middle points means it have two best meeting points always. Both cases will give same distance.
 * So we will consider only one best meeting point to avoid the more overhead, Because our aim is to find the distance only.
 *
 * Input : grid[][] = {{1, 0, 0, 0, 1},
 *                    {0, 0, 0, 0, 0},
 *                    {0, 0, 1, 0, 0}};
 * Output : 6
 * Best meeting point is (0, 2).
 * Total distance traveled is 2 + 2 + 2 = 6
 *
 * Input : grid[3][5] = {{1, 0, 1, 0, 1},
 *                      {0, 1, 0, 0, 0},
 *                      {0, 1, 1, 0, 0}};
 * Output : 11
 */
public class BestMeetingPoint2DArray {
    static int ROW = 3;
    static int COL =5 ;
    static int minTotalDistance(int grid[][])
    {
        if (ROW == 0 || COL == 0)
            return 0;

        List<Integer> vertical = new ArrayList<>();
        List<Integer> horizontal = new ArrayList<>();

        // Find all members home's position
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (grid[i][j] == 1)
                {
                    vertical.add(i);
                    horizontal.add(j);
                }
            }
        }

        // Sort positions so we can find most
        // beneficial point
        Collections.sort(vertical);
        Collections.sort(horizontal);

        // middle position will always beneficial
        // for all group members but it will be
        // sorted which we have already done
        int size = vertical.size() / 2;
        int x = vertical.get(size);
        int y = horizontal.get(size);

        // Now find total distance from best meeting
        // point (x,y) using Manhattan Distance formula
        int distance = 0;
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                if (grid[i][j] == 1)
                    distance += Math.abs(x - i) +
                            Math.abs(y - j);

        return distance;
    }

    // Driver code
    public static void main(String[] args)
    {
        int grid[][] = {{1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0}};
        System.out.println(minTotalDistance(grid));
    }
}
