package heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 */
public class KthSmallestMatrix {

    /*
        Merge sort by adding first element of all rows followed by finding small elements in the row we take.

        Another option is to do binary search with the value of items.
        For each mid value check how many smaller elements can be found.
        Based on that continue iteration.
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for(int j=0;j<n;j++)
            pq.add(new Tuple(0,j,matrix[0][j]));

        for(int i=0;i<k-1;i++){
            Tuple tuple = pq.poll();
            if(tuple.x==n-1)
                continue;
            pq.offer(new Tuple(tuple.x+1,tuple.y,matrix[tuple.x+1][tuple.y]));
        }
        return pq.peek().val;
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
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        KthSmallestMatrix solution = new KthSmallestMatrix();
        solution.kthSmallest(matrix, 8);
    }
}
