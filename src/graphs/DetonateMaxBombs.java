package graphs;

/**
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate
 * and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 * You may choose to detonate a single bomb. When a bomb is detonated,
 * it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 */
public class DetonateMaxBombs {
    int count = 0; // global variable, otherwise use an array
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            count = 0; // Start a new search, reset count
            dfs(i, new boolean[n], bombs);
            ans = Math.max(ans, count);
        }
        return ans;
    }

    private void dfs(int idx, boolean[] v, int[][] bombs) {
        count++;
        v[idx] = true;
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            if (!v[i] && inRange(bombs[idx], bombs[i])) {
                v[i] = true;
                dfs(i, v, bombs);
            }
        }
    }

    private boolean inRange(int[] a, int[] b) {
        long dx = a[0] - b[0], dy = a[1] - b[1], r = a[2];
        return dx * dx + dy * dy <= r * r;
    }
}
