package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 */
public class CloneGraph {

    class Node {
        int val;
        List<Node> neighbors;

        public Node(int node, List<Node> neighbors) {
            this.val =  val;
            this.neighbors = neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        Node copy = map.get(node);
        if(copy!=null)
            return copy;
        if(node==null)
            return null;
        ArrayList<Node> newNeighbours = new ArrayList<>();

        // If we don't add node before doing dfs then we will get into an infinite loop.
        Node newNode = new Node(node.val, newNeighbours);
        map.put(node, newNode);
        for(Node n : node.neighbors) {
            newNeighbours.add(dfs(n, map));
        }
        return newNode;
    }
}
