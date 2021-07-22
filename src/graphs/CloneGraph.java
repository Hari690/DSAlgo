package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

    class Node {
        int val;
        List<Node> neighbors;

        public Node(int node, List<Node> neighbors) {
            this.val =  val;
            this.neighbors = neighbors;
        }
    }

    public Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        return clone(node);
    }

    public Node clone(Node node) {
        if (node == null) return null;

        if (map.containsKey(node.val))
            return map.get(node.val);

        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(newNode.val, newNode);
        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(clone(neighbor));
        return newNode;
    }
}
