package graphs;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is
 * connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and
 * isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int total = 0;

        for(int j=0;j<isConnected.length;j++) {
            if(!visited[j])
                total+=dfs(isConnected, visited, j);
        }

        return total;
    }

    private int dfs(int[][] isConnected, boolean[] visited, int i) {
        int[] vals = isConnected[i];
        for(int j=0;j<vals.length;j++) {
            if(vals[j]==1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
        return 1;
    }
}
