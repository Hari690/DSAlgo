package binarysearch;

import java.util.PriorityQueue;

/**
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned
 * in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 */
public class KWeakestRowsMatrix {
    public int[] kWeakestRows(int[][] mat, int k) {
        //max heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
        {
            if(b[0]==a[0])
                return b[1]-a[1];
            else
                return b[0]-a[0];
        });
        for(int i=0;i<mat.length;i++) {
            int ones = binarySearch(mat[i]);
            int[] arr = {ones, i};
            pq.add(arr);
            if(pq.size()>k)
                pq.remove();
        }
        int[] output = new int[k];
        int i=k-1;
        while(pq.size()>0) {
            output[i--]=pq.remove()[1];
        }
        return output;
    }

    private int binarySearch(int[] arr) {
        int left = 0;
        int right = arr.length-1;

        while(left<=right) {
            int mid = left+(right-left)/2;
            if(arr[mid]==1)
                left = mid+1;
            else
                right = mid-1;
        }
        return left;
    }
}
