package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers arr, sort the array by performing a series of pancake flips.
 *
 * In one pancake flip we do the following steps:
 *
 * Choose an integer k where 1 <= k <= arr.length.
 * Reverse the sub-array arr[0...k-1] (0-indexed).
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4]
 * after the pancake flip at k = 3.
 *
 * Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array
 * within 10 * arr.length flips will be judged as correct.
 */
public class PancakeSort {

    // Idea is to find the largest element in each iteration and flip it to beginning of array.
    // Flip that element to the end after that.
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for(int i=arr.length-1;i>0;i--) {
            for(int j=1;j<=i;j++) {
                if((i+1)==arr[j]) {
                    flip(arr, j);
                    result.add(j+1);
                    break;
                }
            }
            flip(arr, i);
            result.add(i+1);
        }
        return result;
    }

    private void flip(int arr[], int right) {
        int left=0;
        while(left<=right) {
            int temp = arr[left];
            arr[left]=arr[right];
            arr[right]=temp;
            left++;right--;
        }
    }
}
