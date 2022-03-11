package array;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 */
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] path = new int[1001];
        for (int[] trip : trips) {
            // entering;  value is number of passengers
            path[trip[1]]=path[trip[1]]+trip[0];
            path[trip[2]]=path[trip[2]]-trip[0];    // leaving
        }

        int total = 0;
        for (int p : path) {
            total+=p;
            if(total>capacity)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        int[][] trips = {{2,1,5},{3,3,7}};
        carPooling.carPooling(trips, 5);
    }
}
