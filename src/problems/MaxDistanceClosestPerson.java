package problems;

import java.util.TreeSet;

/*
You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).
There is at least one empty seat, and at least one person sitting.
Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
Return that maximum distance to the closest person.
 */
class MaxDistanceClosestPerson {

    public static void main(String[] args) {
        int[] seats = {1,0,0,0};
        new MaxDistanceClosestPerson().maxDistToClosest(seats);
    }

    public int maxDistToClosest(int[] seats) {
        int left = -1;
        int maxDistance = 0;
        int n = seats.length;
        for(int right = 0; right < n ; right++) {
            if(seats[right] == 1) {
                if(left == -1) {
                    //handles edge case when it leads with 0.
                    maxDistance = right;
                } else {
                    //when your seat is inbetween 1s, you need to find half point between the two so you divide by 2
                    maxDistance = Math.max(maxDistance, (right- left) /2);
                }

                left = right;
            }
        }
        if (seats[n-1] == 0) {
            //handles edge case when ends with 0.
            maxDistance = Math.max(maxDistance, (n-1-left));
        }
        return maxDistance;
    }
}