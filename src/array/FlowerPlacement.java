package array;

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new
 * flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 */
public class FlowerPlacement {
    /*
        base condition for leftmost and rightmost.
     */
    public boolean canPlaceFlowers(int[] f, int n) {
        for(int i=0;i<f.length && n>0;i++) {
            int left = (i==0)?0:f[i-1];
            int right = (i==f.length-1)?0:f[i+1];

            if(f[i]==0 && left==0 && right==0) {
                i+=1;
                n--;
            }
        }
        if(n==0)
            return true;

        return false;
    }
}
