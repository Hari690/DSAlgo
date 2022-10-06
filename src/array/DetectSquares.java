package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query
 * point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 *
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 *                                //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 *                                //   - The first, second, and third points
 *                                //   - The first, third, and fourth points
 */
public class DetectSquares {
    // list for unique int[] points
    List<int[]> list;
    // count map for points count
    Map<String, Integer> countMap;

    public DetectSquares() {
        list = new ArrayList<>();
        countMap = new HashMap<>();
    }

    public void add(int[] point) {
        String s = point[0] + "," + point[1];
        if (countMap.containsKey(s)) {
            countMap.put(s, countMap.get(s) + 1);
        } else {
            countMap.put(s, 1);
            list.add(point);
        }
    }

    public int count(int[] point) {
        int currentX = point[0];
        int currentY = point[1];
        int result = 0;
        for (int[] p : list) {
            if (p[0] != currentX && p[1] != currentY && Math.abs(p[0] - currentX) == Math.abs(p[1] - currentY)) {
                String otherPoint1 = currentX + "," + p[1];
                String otherPoint2 = p[0] + "," + currentY;
                result += countMap.getOrDefault(otherPoint1, 0) * countMap.getOrDefault(otherPoint2, 0) * countMap.get(p[0] + "," + p[1]);
            }
        }
        return result;
    }
}
