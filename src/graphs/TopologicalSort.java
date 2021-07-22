package graphs;

import java.util.*;
import java.lang.*;

public class TopologicalSort {

    private int m_v;
    private List<Integer>[] m_adj;

    TopologicalSort(int v) {
        m_v = v;
        m_adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            m_adj[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        TopologicalSort G = new TopologicalSort(5);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(0, 4);
        G.addEdge(1, 2);
        G.addEdge(4, 2);
        G.addEdge(3, 4);
        G.Top_Sort();
    }

    public void addEdge(int u, int v) {
        m_adj[u].add(v);
    }

    private void TS_rec(int s, boolean[] visited, Stack<Integer> S) {
        visited[s] = true;
        for (int u : m_adj[s]) {
            if (!visited[u]) {
                TS_rec(u, visited, S);
            }
        }
        // after visiting push to a stack.
        S.push(s);
    }

    public void Top_Sort() {
        boolean[] visited = new boolean[m_v];
        Stack<Integer> S = new Stack<>();
        for (int i = 0; i < m_v; ++i) {
            if (!visited[i]) {
                TS_rec(i, visited, S);
            }
        }

        while (!S.empty()) {
            System.out.println(S.pop());
        }
    }

}
