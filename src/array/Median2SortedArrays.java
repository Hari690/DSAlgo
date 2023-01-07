package array;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class Median2SortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] sumarray = new int[nums1.length+nums2.length];

        int i=0,j=0;
        while(i<nums1.length || j<nums2.length) {
            if((i<nums1.length && j<nums2.length && nums1[i]<=nums2[j]) || (j==nums2.length)){
                sumarray[i+j]=nums1[i++];
            } else {
                sumarray[i+j]=nums2[j++];
            }
        }

        if( sumarray.length%2==0) {
            return ((double)(sumarray[sumarray.length/2-1]+sumarray[sumarray.length/2]))/2;
        } else {
            return sumarray[sumarray.length/2];
        }
    }

    /*
        Initialize variables l and r to 0 and m - 1 respectively. These variables will be used to track the start and end indices of the first array during the binary search.
        Initialize variables L and R to 0 and n - 1 respectively. These variables will be used to track the start and end indices of the second array during the binary search.
        While l <= r, do the following:
        Calculate the midpoint of the first array by taking the average of l and r. Call this value mid1.
        Calculate the midpoint of the second array by taking the average of L and R. Call this value mid2.
        If mid1 is greater than mid2, it means that the median must be in the first half of the first array or the second half of the second array. Update r to be mid1 - 1 and L to be mid2 + 1.
        If mid1 is less than mid2, it means that the median must be in the second half of the first array or the first half of the second array. Update l to be mid1 + 1 and R to be mid2 - 1.
        If mid1 is equal to mid2, it means that the median is either mid1 or mid2. If the total number of elements in the two arrays is odd, the median is mid1. If the total number of elements is even, the median is the average of mid1 and mid2. Return the appropriate value.
     */
    public double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        if(len % 2 == 0){
            double left = findKthHelper(nums1, 0, nums2, 0, len/2);
            double right = findKthHelper(nums1, 0, nums2, 0, len/2 + 1);
            return (left + right) /2;
        }else{
            return findKthHelper(nums1, 0, nums2, 0, len/2 + 1);
        }
    }
    private int findKthHelper(int[] A, int aStart, int[] B, int bStart, int k){
        if(aStart >= A.length){
            return B[bStart + k - 1];
        }
        if(bStart >= B.length){
            return A[aStart + k - 1];
        }
        if(k == 1){
            return Math.min(A[aStart], B[bStart]);
        }
        int aMid = aStart + k/2 - 1;
        int bMid = bStart + k/2 - 1;
        int aVal = aMid >= A.length ? Integer.MAX_VALUE : A[aMid];
        int bVal = bMid >= B.length ? Integer.MAX_VALUE : B[bMid];
        if(aVal <= bVal){
            return findKthHelper(A, aMid + 1, B, bStart, k - k/2);
        }else{
            return findKthHelper(A, aStart, B, bMid + 1, k - k/2);
        }
    }
}
