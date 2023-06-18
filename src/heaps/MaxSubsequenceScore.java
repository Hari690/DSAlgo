package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxSubsequenceScore {

    class Pair {
        int a;
        int b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    /*
        Since index is same in both array in formula create Pair from both arrays.
        For second array the largest element matters for multiplication so sort based on second array.
        Now start from largest and keep adding sum array element to heap.
        Now iterate the pairs and take a decision for each element which element to remove from sum to make use of this.
        p.b will always be minimum so no need to add it to the heap.

     */
    public long maxScore(int[] a, int[] b, int k) {
        int n = a.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; ++i) pairs[i] = new Pair(a[i], b[i]);
        Arrays.sort(pairs, (x, y) -> y.b - x.b);
        Queue<Integer> q = new PriorityQueue<>();
        long res = 0, sum = 0;
        for (var p : pairs) {
            q.add(p.a);
            sum += p.a;
            if (q.size() > k) sum -= q.poll();
            if (q.size() == k) res = Math.max(res, sum * p.b);
        }
        return res;
    }

    /*
        Bruteforce solution - TLE
     */
    public long maxScore2(int[] nums1, int[] nums2, int k) {
        boolean[] check = new boolean[nums1.length];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        return backtrack(0, check, nums1, nums2, k, 0, 0, minHeap);
    }

    private int backtrack(int i,boolean[] check, int[] nums1, int[] nums2, int k, int curr, int sum,  PriorityQueue<Integer> minHeap) {
        if(i==nums1.length) {
            if(curr==k)
                return sum * minHeap.peek();
            return 0;
        }

        if(curr==k) {
            //System.out.println(curr+" "+minHeap.size());
            return sum * minHeap.peek();
        }

        if(check[i])
            return backtrack(i+1,check, nums1, nums2,k,curr,sum, minHeap);

        minHeap.add(nums2[i]);
        check[i]=true;
        int val1 = backtrack(i+1,check, nums1, nums2,k,curr+1,sum+nums1[i], minHeap);
        minHeap.remove(nums2[i]);
        check[i]=false;
        int val2 = backtrack(i+1,check, nums1, nums2,k,curr,sum, minHeap);
        return Math.max(val1,val2);
    }

    public static void main(String[] args) {
        MaxSubsequenceScore maxSubsequenceScore = new MaxSubsequenceScore();
        int[] nums1 = {1,3,3,2};
        int[] nums2 = {2,1,3,4};
        System.out.println(maxSubsequenceScore.maxScore(nums1, nums2, 3));
    }
}
