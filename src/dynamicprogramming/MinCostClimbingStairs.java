package dynamicprogramming;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 *
 * Once you pay the cost, you can either climb one or two steps.
 * You need to find minimum cost to reach the top of the floor, and you can
 * either start from the step with index 0, or the step with index 1.
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] costs = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        //int[] costs = {10,15,20};
        new MinCostClimbingStairs().minCostClimbingStairs(costs);
    }

    public int minCostClimbingStairs(int[] cost) {

        int l1 = cost[0];
        int l2 = cost[1];

        for( int i=2;i<cost.length;i++) {
            int tmp = l2;
            l2 = Math.min(l1,l2)+cost[i];
            l1 = tmp;
        }

        return Math.min(l1,l2);
    }
}
