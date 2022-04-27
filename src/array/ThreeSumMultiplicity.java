package array;

import java.util.Arrays;

/**
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 * As the answer can be very large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (arr[i], arr[j], arr[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * Example 2:
 *
 * Input: arr = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 *
 */
public class ThreeSumMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);
        int count=0;
        for(int i=0;i<arr.length-2;i++) {
            int rem = target-arr[i];
            if(rem<0)
                continue;
            int left=i+1;
            int right=arr.length-1;
            while(left<right) {
                if(arr[left]+arr[right]<rem)
                    left++;
                else if(arr[left]+arr[right]>rem)
                    right--;
                else {
                    left++;
                    right--;
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,2,2};
        ThreeSumMultiplicity threeSumMultiplicity = new ThreeSumMultiplicity();
        threeSumMultiplicity.threeSumMulti(arr, 5);
    }
}
