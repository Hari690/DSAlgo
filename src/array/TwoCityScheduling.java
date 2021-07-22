package array;

import java.util.Arrays;

/**
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith
 * person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 *
 * Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 * You send everyone to city A. However, you need to send half (N) to city B. So send the cheapest ones to city B.
 * The cheapest ones are the least costly. So, if we take one person that we sent to A, we can get a refund for that one person, but we still have the cost of sending them to B.
 * Refund the A cost subtracts from the cost, so -costs[i][0] + costs[i][1] gives the cost of sending the same person to B. We need to chose the N people to send to B which minimises the overall cost.
 * So, we sort by cost which can be negative and send the first N of this refund vector to B.
 */
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        int total = 0;
        // Travel to city A.
        for( int cost[]: costs) {
            total+= cost[0];
        }
        // Find refunds if you had travelled by B
        int[] refund = new int[costs.length];
        for( int i=0;i<costs.length;i++) {
            refund[i]= costs[i][1]-costs[i][0];
        }
        // sort the refunds and pick most negative values.
        Arrays.sort(refund);
        // Half A and other half B
        for(int i=0;i<costs.length/2;i++) {
            total=total+refund[i];
        }
        return total;
    }
}
