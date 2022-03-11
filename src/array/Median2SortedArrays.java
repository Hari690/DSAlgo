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
}
