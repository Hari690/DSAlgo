class MaximumLengthOfChainInterval {
    public int findLongestChain(int[][] pairs) {
        // because we have better chances of forming a sequence looking at items that end early
        Arrays.sort(pairs, (a,b) -> a[1] - b[1]);

        int prev = 0;
        int res = 1;

        for (int i = 1; i < pairs.length; i++) {
            if (pairs[prev][1] < pairs[i][0]) {
                prev = i;
                res++;
            }
        }

        return res;
    }
}
