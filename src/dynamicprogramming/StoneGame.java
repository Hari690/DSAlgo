package dynamicprogramming;

public class StoneGame {
    private int[][] cache = null;
    public boolean stoneGame(int[] piles) {
        int sum = 0;
        for(int pile : piles) {
            sum+=pile;
        }
        cache = new int[piles.length][piles.length];
        int alice = findBest(piles,0,piles.length-1, true);
        int bob = sum-alice;
        if(alice>bob)
            return true;
        else
            return false;
    }

    private int findBest(int[] piles, int i, int j, boolean skip) {

        if(i>j)
            return 0;

        if(cache[i][j]!=0)
            return cache[i][j];

        int max1 = findBest(piles, i+1, j, !skip);
        int max2 = findBest(piles, i, j-1, !skip);

        int val = Math.max(max1+((skip)?piles[i]:0),max2+((skip)?piles[j]:0));

        cache[i][j] = val;

        return val;
    }
}
