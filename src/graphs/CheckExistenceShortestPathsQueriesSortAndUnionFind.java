package graphs;

import java.util.Arrays;
import java.util.Comparator;

/**
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi.
 * Note that there may be multiple edges between two nodes.
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there
 * is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
 * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true
 * if there is a path for queries[j] is true, and false otherwise.
 *
 * Example 1:
 * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * Output: [false,true]
 * Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
 * For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
 * For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
 *
 * Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * Output: [true,false]
 * Exaplanation: The above figure shows the given graph.
 */
class CheckExistenceShortestPathsQueriesSortAndUnionFind {

    /*
    This great question utilize the power of Union-Find which enables "constant" time query. The tricks are actually pretty straightforward:
    sort both queries by increasing weight
    sort edges by increasing weight
    for each query, union all edges whose weight is less than this query
    check if two nodes in query belongs to the same group
    This idea is pretty important (yet not unique). We may utilize similar tricks in these problems:
     */
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;

        int m = queries.length;

        // storing {u, v, weight, original idx} by increasing weight
        int[][] sortedQueries = new int[m][4];
        for (int i = 0; i < m; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[2]));


        // sort edgeList by increasing weight
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        int idx = 0;

        boolean[] res = new boolean[m];

        for (int i = 0; i < m; i++) {
            int[] q = sortedQueries[i];
            int w = q[2];

            // union all edges with weight less than current query
            while (idx < edgeList.length && edgeList[idx][2] < w) {
                int[] e = edgeList[idx++];
                int u = e[0], v = e[1];
                union(u, v, parents);
            }

            int uQuery = q[0], vQuery = q[1], id = q[3];
            res[id] = (find(uQuery, parents) == find(vQuery, parents));
        }

        return res;
    }

    private void union(int u, int v, int[] parents) {
        int uParent = find(u, parents);
        int vParent = find(v, parents);
        parents[uParent] = vParent;
    }

    private int find(int u, int[] parents) {
        while (u != parents[u]) {
            parents[u] = parents[parents[u]];
            u = parents[u];
        }
        return u;
    }
}
