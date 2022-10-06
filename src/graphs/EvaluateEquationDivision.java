package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 */
public class EvaluateEquationDivision {
    class Pair {
        String node;
        double value;
        Pair(String node, double value) {
            this.node = node;
            this.value = value;
        }

        public String toString() {
            return node+" "+value;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, List<Pair>> adjList = new HashMap<>();
        for(List<String> equation : equations) {
            adjList.put(equation.get(0), new ArrayList<>());
            adjList.put(equation.get(1), new ArrayList<>());
        }


        for(int i=0;i<equations.size();i++) {
            List<Pair> pairs = adjList.get(equations.get(i).get(0));
            pairs.add(new Pair(equations.get(i).get(1), values[i]));
            pairs = adjList.get(equations.get(i).get(1));
            pairs.add(new Pair(equations.get(i).get(0), 1/values[i]));
        }

        //System.out.println(adjList);
        Set<String> visited = new HashSet<>();
        double[] results = new double[queries.size()];
        int i=0;
        for(List<String> query : queries) {
            results[i++] = dfs(query.get(0), query.get(1), 1, adjList, visited);
            visited = new HashSet<>();
        }
        return results;
    }

    public double dfs(String node, String end, double value, Map<String, List<Pair>> adjList,
                      Set<String> visited) {
        if(visited.contains(node))
            return -1;
        if(adjList.containsKey(end) && node.equals(end))
            return value;
        visited.add(node);
        List<Pair> list = adjList.get(node);
        if(list!=null) {
            for(Pair pair : list) {
                double result = dfs(pair.node, end, value*pair.value, adjList, visited);
                if(result!=-1)
                    return result;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        EvaluateEquationDivision evaluateDivision = new EvaluateEquationDivision();
        List<List<String>> equations = List.of(List.of("a","b"),List.of("b","c"));
        double[] values = {2.0,3.0};
        List<List<String>> queries = List.of(List.of("a","c"),List.of("b","a"),List.of("a","e"),List.of("a","a"),List.of("x","x"));
        evaluateDivision.calcEquation(equations, values, queries);
    }
}
