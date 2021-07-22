package dynamicprogramming;

public class Knapsack01 {
    /**
     * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
     * how to pick items to pick items from this set to maximize sum of value of items such that
     * sum of weights is less than or equal to maximum allowed weight.
     * Solves 0/1 knapsack in bottom up dynamic programming.
     */
    public int bottomUpDP(int val[], int wt[], int W){
        int K[][] = new int[val.length+1][W+1];
        for(int i=0; i <= val.length; i++){
            for(int j=0; j <= W; j++){
                if(i == 0 || j == 0){
                    K[i][j] = 0;
                    continue;
                }
                if(j - wt[i-1] >= 0){
                    K[i][j] = Math.max(K[i-1][j], K[i-1][j-wt[i-1]] + val[i-1]);
                }else{
                    // without selecting that item.
                    K[i][j] = K[i-1][j];
                }
            }
        }
        return K[val.length][W];
    }
}
