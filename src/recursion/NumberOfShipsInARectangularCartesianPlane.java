package recursion;

/**
 * On the sea represented by a cartesian plane, each ship is located at an integer point, and each integer point may contain at most 1 ship.
 * You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true if and only if there is at
 * least one ship in the rectangle represented by the two points, including on the boundary.
 * Given two points, which are the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle.
 * It is guaranteed that there are at most 10 ships in that rectangle.
 * Submissions making more than 400 calls to hasShips will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge
 * will result in disqualification.
 * Input:
 * ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
 * Output: 3
 * Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
 */
public class NumberOfShipsInARectangularCartesianPlane {

    /*
        Apply divide and conquer here. Divide the current searching region into 4 equal smaller regions. Do this recursively with the following base case:
        1. if the current search region is not valid or it has no ships in it, return 0;
        2. if the current search region is a single point, return 1.
     */
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        return divideAndConquer(sea, topRight, bottomLeft);
    }
    private int divideAndConquer(Sea sea, int[] topRight, int[] bottomLeft) {
        if(topRight[0] < bottomLeft[0] || topRight[1] < bottomLeft[1] || !sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if(topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            return 1;
        }

        int midX = bottomLeft[0] + (topRight[0] - bottomLeft[0]) / 2;
        int midY = bottomLeft[1] + (topRight[1] - bottomLeft[1]) / 2;

        int cnt = 0;
        cnt += divideAndConquer(sea, new int[]{midX, midY}, bottomLeft);
        cnt += divideAndConquer(sea, new int[]{topRight[0], midY}, new int[]{midX + 1, bottomLeft[1]});
        cnt += divideAndConquer(sea, new int[]{midX, topRight[1]}, new int[]{bottomLeft[0], midY + 1});
        cnt += divideAndConquer(sea, topRight, new int[]{midX + 1, midY + 1});
        return cnt;
    }

    interface Sea {
        boolean hasShips(int[] right, int[] left);
    }
}
