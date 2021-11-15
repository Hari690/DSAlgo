package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the sum of all odd-length subarrays of arr.
 */
public class SumAllOddLengthSubarrays {
    int sum = 0;
    public int sumOddLengthSubarrays(int[] arr) {
        backtrack(arr,0, new ArrayList<>());
        return sum;
    }

    // By default backtracking generates all subsequences so idea is to skip
    // consecutive indexes while adding to list and break the loop.
    private void backtrack(int[] arr, int index, List<int[]> list) {
        if(list.size()%2==1) {
            sum+=list.stream().map(s->s[1]).reduce(Integer::sum).get();
        }
        for(int i=index;i<arr.length;i++) {
            if(list.size()>0) {
                if(i!=list.get(list.size()-1)[0]+1) {
                    break;
                }
            }
            list.add(new int[]{i,arr[i]});
            backtrack(arr, i+1, list);
            list.remove(list.size()-1);
        }
    }
}
