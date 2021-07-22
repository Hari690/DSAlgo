package array;

/**
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0
 * represents that the ith seat is empty (0-indexed).
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to the closest person.
 */
public class MaxDistanceClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int idx1 = -1;
        int idx2 = -1;
        int maxZeroes = 0;
        int result = 0;

        for( int i=0;i< seats.length;i++) {
            if( seats[i]==1) {
                if(idx1==-1) {
                    idx1 = i;
                    maxZeroes=0;
                }
                idx2=i;
            } else {
                maxZeroes++;
                // sitting in between
                result = Math.max(result, (maxZeroes+1)/2);
            }
        }

        // sitting first and last and between
        result = Math.max(result, Math.max(idx1, seats.length-1-idx2));
        return result;

    }
    public static void main(String[] args) {

    }
}
