package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * Example 2:
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * Example 3:
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class FindKPairsWithSmallestSums {
    /*
        Think of merge sort and we sort based on sum of values of a pair.
        So add first row or first column of all elements to a PQ.
        Then keep finding smallest and add next element of it to the PQ.
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        for(int j=0;j<nums2.length;j++)
            pq.add(new Tuple(0,j,nums1[0]+nums2[j]));

        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<k&&i<((long)nums1.length*(long)nums2.length);i++){
            Tuple tuple = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[tuple.x]);
            list.add(nums2[tuple.y]);
            result.add(list);
            if(tuple.x==nums1.length-1)
                continue;
            pq.offer(new Tuple(tuple.x+1,tuple.y,nums1[tuple.x+1]+nums2[tuple.y]));
        }
        return result;
    }

    class Tuple{
        int x;
        int y;
        int val;
        Tuple(int x, int y, int val) {
            this.x=x;
            this.y=y;
            this.val=val;
        }
    }

    public static void main(String[] args) {
        FindKPairsWithSmallestSums solution = new FindKPairsWithSmallestSums();
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        solution.kSmallestPairs(nums1,nums2,3);
    }
}
