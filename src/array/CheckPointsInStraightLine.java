package array;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 *
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 */
public class CheckPointsInStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {

        if(coordinates.length==1)
            return true;

        // to avoid division and get infinity do multiplication
        int d1 = coordinates[1][1] - coordinates[0][1];
        int d2 = coordinates[1][0] - coordinates[0][0];

        for(int i=1;i<coordinates.length;i++) {
            int currD1 = coordinates[i][1] - coordinates[i-1][1];
            int currD2 = coordinates[i][0] - coordinates[i-1][0];

            if(d1*currD2!=d2*currD1) {
                return false;
            }
        }

        return true;
    }
}
