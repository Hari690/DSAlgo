package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    /* Since in the directed graph we can have same source and multiple destinations we need to modify the adj list on the fly.
       So we create new list during iteration and modify it.
       Since we need to terminate the recursion after finding a solution we can use boolean result.
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> neighbours = new HashMap<>();
        List<String> output = new LinkedList<>();
        List<String> finalOutput = new LinkedList<>();
        Collections.sort(tickets, Comparator.comparing(a -> a.get(1)));
        for(List<String> city : tickets) {
            neighbours.putIfAbsent(city.get(0), new ArrayList<>());
            neighbours.get(city.get(0)).add(city.get(1));
        }
        output.add("JFK");
        dfs("JFK", tickets.size()+1, neighbours, output, finalOutput);
        return finalOutput;
    }

    private boolean dfs(String city, int n, Map<String, List<String>> neighbours, List<String> output, List<String> finalOutput) {
        if(output.size()==n) {
            finalOutput.addAll(output);
            return true;
        }
        List<String> neighbourList = neighbours.get(city);
        if(neighbourList!=null) {
            for(String next : neighbourList) {
                output.add(next);
                List<String> newList = new ArrayList<>(neighbourList);
                newList.remove(next);
                neighbours.put(city, newList);
                boolean result = dfs(next, n, neighbours, output, finalOutput);
                if(result)
                    return true;
                newList.add(next);
                neighbours.put(city, newList);
                output.remove(output.size()-1);
            }
        }
        return false;
    }


    private static final String INITIAL_AIRPORT = "JFK";

    public List<String> findItineraryTopSort(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0)
            return new ArrayList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.putIfAbsent(ticket.get(0), new PriorityQueue<>(String::compareTo));
            graph.get(ticket.get(0)).add(ticket.get(1));
        }

        LinkedList<String> result = new LinkedList<>();
        topologicalSort(INITIAL_AIRPORT, graph, result);

        return result;
    }

    private void topologicalSort(String vertex, Map<String, PriorityQueue<String>> graph, LinkedList<String> result) {
        PriorityQueue<String> queue = graph.get(vertex);
        while (queue != null && !queue.isEmpty()) {
            String adj = queue.poll();
            topologicalSort(adj, graph, result);

        }
        result.addFirst(vertex);
    }

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("JFK");
        list1.add("KUL");
        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("NRT");
        List<String> list3 = new ArrayList<>();
        list3.add("NRT");
        list3.add("JFK");
        list.add(list1);
        list.add(list2);
        list.add(list3);
        ReconstructItinerary reconstructItinerary = new ReconstructItinerary();
        reconstructItinerary.findItinerary(list).stream().forEach(System.out::println);
    }
}
