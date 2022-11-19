package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * For example, Given the following words in dictionary,
 * <p>
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * The correct order is: "wertf".
 */
public class CreateAlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(words, graph);

        String order = topologicalSort(graph);
        return order.length() == graph.size() ? order : "";
    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int length = Math.min(first.length(), second.length());

            for (int j = 0; j < length; j++) {
                char parent = first.charAt(j);
                char child = second.charAt(j);
                if (parent != child) {
                    graph.get(parent).add(child);
                    break;
                }
            }
        }
    }

    // A recursive function used by topologicalSort
    private boolean topologicalSortUtil(char curr,
                                        Map<Character, Boolean> visited,
                                        Stack<Character> stack, Map<Character, Set<Character>> graph) {
        if (visited.containsKey(curr))
            return visited.get(curr);

        visited.put(curr, true);
        for (char adjacentVertex : graph.get(curr)) {
            if (topologicalSortUtil(adjacentVertex, visited, stack, graph)) {
                return true;
            }
        }
        visited.put(curr, false);
        // Push current vertex to stack which stores result
        stack.push(curr);
        return false;
    }

    // prints a Topological Sort of the complete graph
    private String topologicalSort(Map<Character, Set<Character>> graph) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Boolean> visited = new HashMap<>();

        for (char c : graph.keySet()) {
            // if true there is a cycle in the graph
            if(topologicalSortUtil(c, visited, stack, graph))
                return "";
        }

        StringBuilder output = new StringBuilder();
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }
        return output.toString();
    }
}
