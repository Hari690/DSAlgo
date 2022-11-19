package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 * Example 1:
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 * Example 2:
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 */
public class BusRoutesMinBusesBFS {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        /*Create a hashMap such that we can easily access inverted mappings(or we can say inverted relations) of that of the input.
		ie, in input we had:

        Bus Number(depicted as row in 2D input called routes[][]) -> Bus Stops(depicted as cols in 2D input called routes[][])
        now using hashMap we attempt to establish:

		Bus Stop(Integer) -> Bus Number(ArrayList<Integer>), and this relation means that on a bus stop, which Buses can we expect

        All this is done because the cost of reaching all the bus stops for a given bus number is same irrespective intermediate nodes hopped */

        HashMap<Integer, ArrayList<Integer>> map = new HashMap();

        for(int i = 0; i < routes.length; i++){
            for(int j=0; j< routes[i].length; j++){
                int busStopNo = routes[i][j];
                ArrayList<Integer> busNo = map.getOrDefault(busStopNo, new ArrayList<>());
                busNo.add(i);
                map.put(busStopNo, busNo);
            }
        }


        //we need a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        //we need a hashSet to keep track of Bus Stops visited
        HashSet<Integer> busStopVisited = new HashSet<>();

        //basically store the bus number to keep track of all bus stops visited for a Bus Number..kinda bundle up the work of busStopVisited(hashSet made just above) and reduce time complexity of rechecking all bus stops for a given Bus.
        HashSet<Integer> busVisited = new HashSet<>();

        int cost = 0;

        //add source to the queue and make it visited
        queue.addLast(source);
        busStopVisited.add(source);

        //start BFS
        while(queue.size()>0){
            int size = queue.size();
            while(size-->0){
                int rem = queue.removeFirst();
                // if the removed bus stop is equal to our target bus stop, then return the cost(cost is incermented when a different bus number is taken to reach destination)
                if(rem == target){
                    return cost;
                }
                //else
                //use the hashmap to get the list of buses we can get form the bus stop on which we currently stand
                ArrayList<Integer> buses = map.get(rem);
                //think as if we are trying to catch and explore a new bus from the bus stop on which we are
                for(int bus: buses){
                    //already visited then don't do anything, just continue to explore new buses
                    if(busVisited.contains(bus)==true){
                        continue;
                    }
                    //new bus found->  now catch this bus and see where can you reach from this bus with a hope that this bus helps us get to our target bus stop when in the next BFS iteration we remove the busStops of this bus and test the condition if(rem == target) return cost;
                    int[] arr = routes[bus];
                    for(int busStop: arr){
                        if(busStopVisited.contains(busStop)==true){
                            continue;
                        }
                        queue.addLast(busStop);
                        busStopVisited.add(busStop);
                    }
                    busVisited.add(bus);
                }
            }
            // we have taken a new bus so increase the cost
            cost++;
        }
        return -1;
    }
}
