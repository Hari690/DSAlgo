package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * Contact me on wechat to get Amazon、Google requent Interview questions . (wechat id : jiuzhang0607)
 *
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example
 * Example 1:
 * Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 * Output: true.
 * Example 2:
 * Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 * Output: false.
 */
public class GraphValidTree {

    public static List<List<Integer>> createGraph(int V, int[][] edges){
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<V;i++)list.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        return list;
    }
    public boolean hasCycle(int node, boolean[] vis, int par, List<List<Integer>> adjList){
        vis[node] = true;
        for (int neigh : adjList.get(node)) {
            if (!vis[neigh]) {
                if (hasCycle(neigh, vis, node, adjList)) return true;
            } else {
                if (neigh != par) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjList = createGraph(n, edges);
        int cnt = 0 ;
        boolean[] vis = new boolean[n];
        int par = -1;
        for(int i=0;i<n;i++){
            if(!vis[i]){
                cnt++;
                if(hasCycle(i,vis,par,adjList)){
                    return false;
                }
            }
        }
        return cnt==n;
    }
}
