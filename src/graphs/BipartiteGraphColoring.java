package graphs;

/**
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is
 * an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
 *
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 */
public class BipartiteGraphColoring {
    /*
        Idea is to always start coloring an uncolored node with one color and then use dfs to color
        adjacent nodes in different color. If it's already colored in different color
        previously return false.
     */
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];

        // colors[i] == 0 means this row (node) is not visited yet, so it makes no difference coloring it 1 or -1.
        // Visually this node is connected with another set of nodes.
        for(int i=0;i<graph.length;i++) {
            if(color[i]==0 && !validColor(i,graph, 1, color))
                return false;
        }

        return true;
    }

    private boolean validColor(int n, int[][] graph, int c, int[] color) {
        if(color[n]!=0)
            return color[n]==c;

        color[n] = c;
        for(int node : graph[n]) {
            if(!validColor(node, graph, -c, color))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        BipartiteGraphColoring bipartiteGraph = new BipartiteGraphColoring();
        int[][] graph = {{1,3},{0,2},{1,3},{0,2}};
        bipartiteGraph.isBipartite(graph);
    }


}
