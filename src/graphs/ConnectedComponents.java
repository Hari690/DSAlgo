package graphs;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {
    int noOfVertices;
    List<ArrayList<Integer>> adjListArray;

    // constructor
    ConnectedComponents(int noOfVertices)
    {
        this.noOfVertices = noOfVertices;
        // define the size of array as
        // number of vertices
        adjListArray = new ArrayList<>();

        // Create a new list for each vertex
        // such that adjacent nodes can be stored

        for (int i = 0; i < noOfVertices; i++) {
            adjListArray.add(i, new ArrayList<>());
        }
    }

    void DFSUtil(int v, boolean[] visited)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adjListArray.get(v)) {
            if (!visited[x])
                DFSUtil(x, visited);
        }
    }
    void connectedComponents()
    {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[noOfVertices];
        for (int v = 0; v < noOfVertices; ++v) {
            if (!visited[v]) {
                // print all reachable vertices
                // from v
                DFSUtil(v, visited);
            }
            System.out.println();
        }
    }

    // Adds an edge to an undirected graph
    void addEdge(int src, int dest)
    {
        // Add an edge from src to dest.
        adjListArray.get(src).add(dest);

        // Since graph is undirected, add an edge from dest
        // to src also
        adjListArray.get(dest).add(src);
    }

    // Driver code
    public static void main(String[] args)
    {
        // Create a graph given in the above diagram
        ConnectedComponents g = new ConnectedComponents(
            5); // 5 vertices numbered from 0 to 4

        g.addEdge(1, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        System.out.println(
            "Following are connected components");
        g.connectedComponents();
    }
}
