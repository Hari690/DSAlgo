package trees;

/**
 *  Catalan Number
 *
 */
public class NumberOfBST {

    public int numTrees(int n) {
        if(n < 2) return 1;
        int[] sol = new int[n+1];
        sol[0] = 1; sol[1] = 1;
        for(int i = 2; i <= n; ++i){
            for(int j = 0; j < i; ++j){
                sol[i] += sol[j]*sol[i-1-j];
            }
        }
        return sol[n];
    }
}
