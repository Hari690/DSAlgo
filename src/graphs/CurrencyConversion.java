package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i]
 * represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no
 * contradiction.
 */
public class CurrencyConversion {
    class Node {
        private String key;
        private double value;
        Node(String key, double value) {
            this.key = key;
            this.value = value;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String,List<Node>> map = new HashMap<>();

        // create adjacency list.
        for(int i=0;i<equations.size();i++) {
            List<Node> values1 = map.get(equations.get(i).get(0));
            if(values1==null)
                values1 = new ArrayList<>();
            values1.add(new Node(equations.get(i).get(1),values[i]));
            map.put(equations.get(i).get(0), values1);
            List<Node> values2 = map.get(equations.get(i).get(1));
            if(values2==null)
                values2 = new ArrayList<>();
            values2.add(new Node(equations.get(i).get(0),1/values[i]));
            map.put(equations.get(i).get(1), values2);
        }

        double[] results = new double[queries.size()];
        Set<String> visited = new HashSet<>();

        int i=0;
        for(List<String> query : queries) {
            visited.add(query.get(0));
            results[i++] = dfs(query.get(0), map, visited, query.get(1), 1.0);
            visited = new HashSet<>();
        }
        return results;
    }

    private double dfs(String val, Map<String, List<Node>> map, Set<String> visited, String output, double rate) {
        if(map.get(val)==null)
            return -1;

        if(val.equals(output))
            return rate;

        double answer = -1;
        for(Node node : map.get(val)) {
            if(!visited.contains(node.key)) {
                visited.add(node.key);
                answer = dfs(node.key, map, visited, output, rate*node.value);
                if(answer!=-1)
                    return answer;
            }
        }
        return answer;
    }
}
