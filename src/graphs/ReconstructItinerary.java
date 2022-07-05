package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight
 * . Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid
 * itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 */
public class ReconstructItinerary {

    /* Key is to create a Map with String as key and PriorityQueue as value to do DFS properly.
     */
    Map<String, PriorityQueue<String>> neighbours = new HashMap<>();
    List<String> output = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {

        for(List<String> city : tickets) {
            neighbours.putIfAbsent(city.get(0), new PriorityQueue<>());
            neighbours.get(city.get(0)).add(city.get(1));
        }

        dfs("JFK");
        return output;
    }

    private void dfs(String city) {

        PriorityQueue<String> neighbour = neighbours.get(city);

        while(!neighbour.isEmpty()) {
            dfs(neighbour.poll());
        }

        output.add(0,city);
    }
}
