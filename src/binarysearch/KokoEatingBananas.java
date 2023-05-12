package binarysearch;

import java.util.Arrays;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in
 * h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 */
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        long r = Arrays.stream(piles).max().getAsInt();
        long l = 1;
        while(l<=r) {
            long s=l+(r-l)/2;
            if(canFinish(piles, h, s)) {
                r=s-1;
            } else {
                l=s+1;
            }
        }

        return (int)l;
    }

    private boolean canFinish(int[] piles, long h, long s) {
        long t = 0;
        for(int p : piles){
            t+=(p/s)+(((p%s)!=0)?1:0);
            if(t>h)
                return false;
        }
        return true;
    }
}
