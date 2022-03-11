package dynamicprogramming;

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values
 * from 1 to n.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 5
 */
public class NumberOfBST {

    /*
        numTrees(3) = numTrees(0)*numTrees(2) + numTrees(1)*numTrees(1) + numTrees(2)*numTrees(0) = 1*2 + 1*1 + 2*1 = 5
     */
    public int numTrees(int n) {
        int[] cache = new int[n+1];
        return find(n, cache);
    }

    private int find(int n, int[] cache) {
        if(n==0)
            return 1;

        if(n<=2)
            return n;

        if(cache[n]!=0)
            return cache[n];

        for(int i=0;i<n;i++) {
            cache[n]+= find(n-i-1, cache) * find(i, cache);
        }
        return cache[n];
    }
}
