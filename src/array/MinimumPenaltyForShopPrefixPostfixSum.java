class MinimumPenaltyForShopPrefixPostfixSum {
    public int bestClosingTime(String c) {
        int n = c.length();
        int[] prefix = new int[n+1];
        prefix[n] = 0;
        
        for(int i = n-1; i >= 0; i--){
           if(c.charAt(i) == 'Y'){
               prefix[i] = 1 + prefix[i+1];
           }
            else prefix[i] = prefix[i+1];
        }
        
        int[] suffix = new int[n+1];
        suffix[0] = 0;
        
        for(int i = 1; i <= n; i++){
            if(c.charAt(i-1) == 'N'){
                suffix[i] = 1 + suffix[i-1];
            }
            else suffix[i] = suffix[i-1];
        }
        
        int mini = Integer.MAX_VALUE; 
        int idx = 0;
        for(int i = 0; i <= n; i++){
            int curr = prefix[i] + suffix[i];
            if(mini > curr){
                mini = curr;
                idx = i;
            }
        }
        return idx;
    }
}
