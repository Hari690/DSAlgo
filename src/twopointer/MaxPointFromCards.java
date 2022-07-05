package twopointer;

/**
 * There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array
 * cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 */
/*
    Fixed sized window.
    Notice we can generate all combinations by making the window size (n-k) and getting sum of k.
 */
public class MaxPointFromCards {
    public int maxScore(int[] arr, int k) {
        int n = arr.length;
        int l = 0, r = n-k;
        int sum = sum(arr,n-k);
        int maxSum = sum;

        while(r<n) {
            sum+=arr[l]-arr[r];
            maxSum=Math.max(sum,maxSum);
            l++;
            r++;
        }

        return maxSum;

    }

    private int sum(int[] arr, int j) {
        int sum=0;
        for(int k=j;k<arr.length;k++)
            sum+=arr[k];
        return sum;
    }
}
