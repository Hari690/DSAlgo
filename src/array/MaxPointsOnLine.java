package array;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 *
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 */
public class MaxPointsOnLine {

    /*
        Looking at constraints, we can do an O(n^3) solution.
        Create a slope with two points and try to put third point on that slope.
        Instead of slope division formula we can use multiplication to avoid decimal points.
     */
    public int maxPoints(int[][] points) {
        if(points.length==1)
            return 1;
        int max = 2;
        for(int i=0;i<points.length;i++) {
            for(int j=i+1;j<points.length;j++) {
                int total = 2;
                //if((points[j][0]-points[i][0])!=0) {
                // int slope = (points[j][1]-points[i][1])/(points[j][0]-points[i][0]);
                for(int k=0;k<points.length;k++) {
                    if(k!=i && k!=j) {
                        //if((points[k][0]-points[i][0])!=0) {
                        //int kSlope = (points[k][1]-points[i][1])/(points[k][0]-points[i][0]);
                        if(((points[j][1]-points[i][1])*(points[k][0]-points[i][0])==((points[j][0]-points[i][0])*(points[k][1]-points[i][1]))))
                            total++;
                    }
                }
                max = Math.max(max, total);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxPointsOnLine maxPointsOnLine = new MaxPointsOnLine();
        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,42}};
        maxPointsOnLine.maxPoints(points);
    }
}
