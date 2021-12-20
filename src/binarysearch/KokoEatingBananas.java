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
        int right = Arrays.stream(piles).max().getAsInt();
        int left=1;
        int minHours = right;
        while(left<=right) {
            //signifies speed
            int mid = left + (right - left)/2;
            int hours = findHours(piles,mid);
            //decrease speed, more time.
            if(hours<=h) {
                right = mid - 1;
                minHours = Math.min(minHours, mid);
            }
            else
                left = mid + 1;

        }
        return minHours;
    }

    private int findHours(int[] piles, int k) {
        int days = 0;
        for(int pile : piles) {
            days+=pile/k+((pile%k==0)?0:1);
        }
        return days;
    }
}
