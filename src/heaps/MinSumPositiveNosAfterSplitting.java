package heaps;

import java.util.PriorityQueue;

/**
 * You are given a positive integer num consisting of exactly four digits. Split num into two new integers new1 and new2 by using the digits found in num. Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.
 * For example, given num = 2932, you have the following digits: two 2's, one 9 and one 3. Some of the possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
 * Return the minimum possible sum of new1 and new2.
 *
 * Example 1:
 * Input: num = 2932
 * Output: 52
 * Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
 * The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
 *
 * Example 2:
 * Input: num = 4009
 * Output: 13
 * Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc.
 * The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 */
public class MinSumPositiveNosAfterSplitting {
    public int minimumSum(int num) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        while(num!=0) {
            heap.add(num%10);
            num=num/10;
        }

        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        while(!heap.isEmpty()) {
            s1.append(heap.poll());
            if(!heap.isEmpty())
                s2.append(heap.poll());
        }
        return Integer.parseInt(s1.toString())+Integer.parseInt(s2.toString());
    }
}
