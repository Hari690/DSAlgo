package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 * Example 1:
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 *
 * Example 2:
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 */
public class BinaryTreeWithFactors {

    /*
        For each no check if it has factors before it. If it does multiple their results to get result for current no.
     */
    public int numFactoredBinaryTrees(int[] arr) {
        long total=0;

        // This is to ensure we can start from beginning and set one node as root and check for it's children before it.
        Arrays.sort(arr);
        Map<Integer,Long> map = new HashMap<>();
        int MOD=1000000007;

        for(int i=0;i<arr.length;i++) {
            long current = 1;
            for(int j=0;j<i;j++) {
                if(arr[i]%arr[j]==0) {
                    int fact1= arr[i]/arr[j];
                    current+=(map.getOrDefault(fact1, 0L)*map.getOrDefault(arr[j], 0L))%MOD;
                    System.out.println(current);
                }
            }
            total=(total+current)%MOD;
            map.put(arr[i],current);
        }

        return (int)total;
    }
}
